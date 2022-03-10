package com.cahrusat.sqlitedemodiv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            Runnable {
                     var intent =Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        ,3000)
    }
}