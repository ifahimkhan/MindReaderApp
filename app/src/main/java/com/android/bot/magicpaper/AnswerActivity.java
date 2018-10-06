package com.android.bot.magicpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class AnswerActivity extends AppCompatActivity {

    ImageView imageView;
    Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            int position = getIntent().getIntExtra("position", 0);
            boolean show = getIntent().getBooleanExtra("show", false);
            if (position < 5) {
                Toast.makeText(this, " Are you confused? ", Toast.LENGTH_SHORT).show();
            } else if (!show) {
                Toast.makeText(this, " Are you confused? ", Toast.LENGTH_SHORT).show();

            } else {
                int random = getIntent().getIntExtra("random", 0);
                int[] myimg = getIntent().getIntArrayExtra("images");
                Toast.makeText(this, " Is this your ANSWER ", Toast.LENGTH_LONG).show();

                imageView = (ImageView) findViewById(R.id.answer);
                imageView.setImageResource(myimg[random]);

                fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(fadeIn);

            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void finish(MenuItem item) {
        finishAffinity();
    }
}
