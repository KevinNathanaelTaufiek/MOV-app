package com.kevinnt.movapp.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.kevinnt.movapp.R
import com.kevinnt.movapp.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference : DatabaseReference
    lateinit var  database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseDatabase = FirebaseDatabase.getInstance()
        database = FirebaseDatabase.getInstance().getReference()
        databaseReference = firebaseDatabase.getReference("User")

        btn_prim.setOnClickListener {
            if(et_username.text.toString().equals("")){
                et_username.error = "Username tidak boleh kosong!"
                et_username.requestFocus()
            } else if(et_password.text.toString().equals("")){
                et_password.error = "Password tidak boleh kosong!"
                et_password.requestFocus()
            } else if(et_nama.text.toString().equals("")){
                et_nama.error = "Nama tidak boleh kosong!"
                et_nama.requestFocus()
            } else if(et_email.text.toString().equals("")){
                et_email.error = "Email tidak boleh kosong!"
                et_email.requestFocus()
            } else{
                saveUser(et_username.text.toString(),et_password.text.toString(),et_nama.text.toString(),et_email.text.toString())
            }
        }
    }

    private fun saveUser(xUsername: String, xEmail: String, xNama: String, xPassword: String) {
        var user = User()

        user.username = xUsername
        user.nama = xNama
        user.email = xEmail
        user.password = xPassword

        checkUser(xUsername,user)
    }

    private fun checkUser(xUsername: String, data: User) {
        databaseReference.child(xUsername).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)

                if (user==null){
                    databaseReference.child(xUsername).setValue(data)
                    var intent = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(intent)
                } else{
                    Toast.makeText(this@SignUpActivity,"Username Sudah Ada!",Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }


}