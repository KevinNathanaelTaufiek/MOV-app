package com.kevinnt.movapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevinnt.movapp.R
import com.kevinnt.movapp.sign.signin.SignInActivity
import com.kevinnt.movapp.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preferences = Preferences(this)

        if(preferences.getValue("onboarding").equals("1")){
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_prim.setOnClickListener{
            var intent = Intent(this@OnboardingOneActivity,OnboardingTwoActivity::class.java)
            startActivity(intent)
        }

        btn_sec.setOnClickListener{
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)

        }
    }
}