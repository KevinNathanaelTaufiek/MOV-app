package com.kevinnt.movapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevinnt.movapp.R
import com.kevinnt.movapp.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        btn_prim.setOnClickListener {
            var intent = Intent(this@OnboardingTwoActivity,OnboardingThreeActivity::class.java)
            startActivity(intent)
        }

        btn_sec.setOnClickListener{
            startActivity(Intent(this@OnboardingTwoActivity, SignInActivity::class.java))
            finishAffinity()
        }
    }
}