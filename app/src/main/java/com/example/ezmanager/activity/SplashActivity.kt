package com.example.ezmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.ezmanager.HomeActivity
import com.example.ezmanager.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var Image:ImageView = findViewById(R.id.splash_logo1)
        var Image2:ImageView = findViewById(R.id.splash_logo2)
        var text:TextView = findViewById(R.id.splashText)
        var topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        var bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        var zoominAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        //var zoomoutAnim = AnimationUtils.loadAnimation(this,R.anim.zoom_out)

        Image.startAnimation(topAnim)
        Image2.startAnimation(zoominAnim)
        text.startAnimation(bottomAnim)

        supportActionBar?.hide(); // hide app bar on main page

        Handler().postDelayed({
            val intent = Intent (this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)   // start the home_activity
            finish()              // back button won't work
        }, 3000)

    }
}