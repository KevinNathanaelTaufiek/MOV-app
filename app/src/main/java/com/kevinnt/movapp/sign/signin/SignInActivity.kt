package com.kevinnt.movapp.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.kevinnt.movapp.home.HomeActivity
import com.kevinnt.movapp.R
import com.kevinnt.movapp.sign.signup.SignUpActivity
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.lang.Exception


class SignInActivity : AppCompatActivity() {

    lateinit var iusername:String
    lateinit var ipassword:String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        preferences.setValue("onboarding","1")
        if(preferences.getValue("status").equals("1")){
            finishAffinity()

            var intent = Intent(this@SignInActivity,HomeActivity::class.java)
            startActivity(intent)
        }

        btn_prim.setOnClickListener {
            iusername = et_username.text.toString()
            ipassword = et_password.text.toString()

            if(iusername.equals("")){
                et_username.error = "username tidak boleh kosong"
                et_username.requestFocus()
            }else if (ipassword.equals("")){
                et_password.error = "password tidak boleh kosong"
                et_password.requestFocus()
            }
            else{
                try {
                    pushLogin(iusername,ipassword)
                } catch (e:Exception){
                    Toast.makeText(this, "Mohon cek kembali username dan password anda", Toast.LENGTH_SHORT).show();
                }
            }
        }

        btn_sec.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun pushLogin(iusername: String, ipassword: String) {
        mDatabase.child(iusername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user == null){
                    Toast.makeText(this@SignInActivity,"User tidak ditemukan",Toast.LENGTH_LONG).show()
                }
                else{
                    if(user.password.equals(ipassword)){

                        preferences.setValue("nama",user.nama.toString())
                        preferences.setValue("email",user.email.toString())
                        preferences.setValue("saldo",user.saldo.toString())
                        preferences.setValue("url",user.url.toString())
                        preferences.setValue("user",user.username.toString())
                        preferences.setValue("status","1")

                        var intent = Intent(this@SignInActivity,HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@SignInActivity,"Password tidak cocok",Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity,error.message,Toast.LENGTH_LONG).show()
            }
        })
    }

}