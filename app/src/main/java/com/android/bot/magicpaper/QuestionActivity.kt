package com.android.bot.magicpaper

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.bot.magicpaper.MainActivity
import com.android.bot.magicpaper.databinding.ActivityQuestionBinding
import com.android.bot.magicpaper.databinding.ActivitySplashScreenBinding

class QuestionActivity : AppCompatActivity() {
    private lateinit var cardViews: List<CardView>
    private lateinit var fadeAnimations: List<Animation>
    private var doubleBackToExitPressedOnce = false
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardViews = listOf(
            findViewById(R.id.txt2),
            findViewById(R.id.cardtxt3),
            findViewById(R.id.cardtxt4)
        )
        fadeAnimations = List(3) { AnimationUtils.loadAnimation(this, R.anim.left_to_right) }

        cardViews.forEachIndexed { index, cardView ->
            cardView.startAnimation(fadeAnimations[index].apply { startOffset = index * 500L })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    fun DoneQ(item: MenuItem?) {
        AlertDialog.Builder(this)
            .setMessage("Did you choose your number?")
            .setPositiveButton("continue") { _, _ ->
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            .show()
    }
}
