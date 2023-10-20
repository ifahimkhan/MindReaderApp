package com.android.bot.magicpaper

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.android.bot.magicpaper.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img.apply {
            visibility = View.VISIBLE
            startAnimation(AnimationUtils.loadAnimation(this@SplashScreen, R.anim.fade_in))
        }
        binding.textView4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink))

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, QuestionActivity::class.java))
            finish()
        }, 3000)
    }
}
