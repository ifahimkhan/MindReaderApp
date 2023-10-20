package com.android.bot.magicpaper

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.bot.magicpaper.databinding.ActivityAnswerBinding

class AnswerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnswerBinding
    private var fadeIn: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val position = intent.getIntExtra("position", 0)
        val show = intent.getBooleanExtra("show", false)

        if (position < 5 || !show) {
            showToast("Are you confused?", Toast.LENGTH_SHORT)
        } else {
            val random = intent.getIntExtra("random", 0)
            val myImages = intent.getIntArrayExtra("images")

            showToast("Is this your answer?", Toast.LENGTH_LONG)

            binding.answer.setImageResource(myImages?.get(random) ?: 0)

            fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.answer.visibility = View.VISIBLE
            binding.answer.startAnimation(fadeIn)
        }
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun finish(item:View) {
        finishAffinity()
    }*/

    private fun showToast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }
}
