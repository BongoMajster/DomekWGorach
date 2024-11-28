package com.example.domekwgorach;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView likeCountTextView;
    private int likeCount;

    private static final String PREFS_NAME = "LikesPrefs";
    private static final String LIKE_COUNT_KEY = "likeCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeCountTextView = findViewById(R.id.likeCountTextView);
        Button likeButton = findViewById(R.id.likeButton);
        Button dislikeButton = findViewById(R.id.dislikeButton);

        loadLikeCount();

        likeCountTextView.setText(likeCount + " polubień");

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementLikes();
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementLikes();
            }
        });
    }

    private void loadLikeCount() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        likeCount = prefs.getInt(LIKE_COUNT_KEY, 0); // Domyślnie 0
    }

    private void saveLikeCount() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(LIKE_COUNT_KEY, likeCount);
        editor.apply();
    }

    private void incrementLikes() {
        likeCount++;
        likeCountTextView.setText(likeCount + " polubień");
        saveLikeCount();
    }

    private void decrementLikes() {
        if (likeCount > 0) {
            likeCount--;
        }
        likeCountTextView.setText(likeCount + " polubień");
        saveLikeCount();
    }
}