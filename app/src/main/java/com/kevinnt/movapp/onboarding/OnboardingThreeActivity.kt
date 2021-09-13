package com.kevinnt.movapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevinnt.movapp.R
import com.kevinnt.movapp.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btn_prim.setOnClickListener {
            finishAffinity()
            var i = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)
            startActivity(i)
        }
    }
}