package com.kevinnt.movapp.sign.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.kevinnt.movapp.home.HomeActivity
import com.kevinnt.movapp.R
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up_photo.*
import java.util.*
import java.util.jar.Manifest

class SignUpPhotoActivity : AppCompatActivity(), PermissionListener {

    var request_img = 1
    var status_add = false
    lateinit var filePath : Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)
//        Log.d("test", "onCreate: masuk")

        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()
        preferences = Preferences(this)

        tv_welcome.text = "Selamat Datang\n" + intent.getStringExtra("nama")

        iv_add.setOnClickListener {
            if(status_add){
                status_add = false
                btn_save.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.ic_baseline_add_circle_24)
                iv_profile.setImageResource(R.drawable.profile_img)
            } else{
//                Dexter.withActivity(this).withPermission(android.Manifest.permission.CAMERA).withListener(this).check()
                ImagePicker.with(this)
                    .cameraOnly()	//User can only capture image using Camera
                    .start()

            }
        }


        btn_prim.setOnClickListener {
            finishAffinity()

            var intent = Intent(this@SignUpPhotoActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_save.setOnClickListener {
            if(filePath != null) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Upload...")
                progressDialog.show()

                var ref = storageReference.child("image/"+UUID.randomUUID().toString())
                ref.putFile(filePath).addOnSuccessListener{
                    progressDialog.dismiss()
                    Toast.makeText(this,"Upload berhasil",Toast.LENGTH_LONG).show()

                    ref.downloadUrl.addOnSuccessListener {
                        preferences.setValue("url",it.toString())
                    }
                    finishAffinity()
                    var intent = Intent(this@SignUpPhotoActivity, HomeActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener{
                        progressDialog.dismiss()
                        Toast.makeText(this, "Upload Gagal!",Toast.LENGTH_LONG).show()
                }.addOnProgressListener {
                    snapshot: UploadTask.TaskSnapshot ->  var progress = 100.0 * snapshot.bytesTransferred/snapshot.totalByteCount
                    progressDialog.setMessage("Upload " + progress.toInt()+"%")
                }

            } else{
                Toast.makeText(this,"File Path NULL",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent,request_img)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,"Anda tidak dapat mengupload foto",Toast.LENGTH_LONG).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Anda tidak dapat mengupload foto",Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("test", "onActivityResult: masuk")
        if(data?.data!! != null){
            status_add = true

            filePath = data?.data!!
//            Toast.makeText(this, filePath.toString(), Toast.LENGTH_SHORT).show()
//            Log.d("test", "onActivityResult: "+ filePath.toString())

            Glide.with(this).load(filePath).apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            btn_save.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)

        }
        else if(resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Something Wrong, Try Later", Toast.LENGTH_SHORT).show()
        }

    }

}