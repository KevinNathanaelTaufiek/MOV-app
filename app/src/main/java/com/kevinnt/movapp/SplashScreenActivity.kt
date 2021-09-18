package com.kevinnt.movapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.kevinnt.movapp.onboarding.OnboardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity,OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()

        },5000)
    }
}