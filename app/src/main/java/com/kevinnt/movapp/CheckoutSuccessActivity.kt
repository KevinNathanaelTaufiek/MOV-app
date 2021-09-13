package com.kevinnt.movapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevinnt.movapp.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_sec.setOnClickListener {
            finishAffinity()

            //TODO: Button lihat tiket
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}