package com.android.bot.magicpaper

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private val imageArray = ArrayList<Int>()
    private val booleanItemClicked = ArrayList<Boolean>()

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val blink = AnimationUtils.loadAnimation(this, R.anim.blink)
        val revealAnswer = findViewById<TextView>(R.id.next)
        revealAnswer.startAnimation(blink)

        recyclerView = findViewById(R.id.listitem)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        Toast.makeText(this, "Shuffling.!!", Toast.LENGTH_SHORT).show()
        initializeData()

        mAdapter = Adapter(applicationContext, imageArray, booleanItemClicked)
        recyclerView.adapter = mAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                displayedPosition = linearLayoutManager.findFirstVisibleItemPosition()
            }
        })
    }

    fun nextIntent(view: View?) {
        val show = Adapter.pastIndex != 0
        val dialog = AlertDialog.Builder(this)
        dialog.setPositiveButton("Continue") { _, _ ->
            startActivity(
                Intent(
                    applicationContext, AnswerActivity::class.java
                ).putExtra("random", getRandomValue()).putExtra("images", images)
                    .putExtra("position", displayedPosition)
                    .putExtra("show", show)
            )
        }
        dialog.setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
        dialog.setMessage("Did you get your Symbol?")
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun initializeData() {
        val r = Random()
        val images = intArrayOf(
            R.mipmap.image0,
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3,
            R.mipmap.image4,
            R.mipmap.image5,
            R.mipmap.image6,
            R.mipmap.image7,
            R.mipmap.image8,
            R.mipmap.image9,
            R.mipmap.image10,
            R.drawable.flag
        )

        for (listIndex in 0..100) {
            val index = listIndex % 12
            if (listIndex < 9) {
                imageArray.add(listIndex, images[r.nextInt(12)])
            } else if (listIndex % 9 == 0) {
                imageArray.add(listIndex, images[random])
            } else {
                imageArray.add(listIndex, images[index])
            }
            booleanItemClicked.add(false)
        }
    }

    companion object {
        private const val NUM_LIST_ITEMS = 101
        private var displayedPosition = 0
        private var random = Random().nextInt(12)
        private var images = intArrayOf(
            R.mipmap.image0,
            R.mipmap.image1,
            R.mipmap.image2,
            R.mipmap.image3,
            R.mipmap.image4,
            R.mipmap.image5,
            R.mipmap.image6,
            R.mipmap.image7,
            R.mipmap.image8,
            R.mipmap.image9,
            R.mipmap.image10,
            R.drawable.flag
        )

        private fun getRandomValue(): Int {
            return Random().nextInt(12)
        }
    }
}
