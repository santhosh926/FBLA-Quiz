package com.example.ghautham.fblaquiz;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FinalActivity extends AppCompatActivity {
    TextView scoreDisplay, textView;
    String score;
    String username;
    Button button;
    Button leaderBoard;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);
        leaderBoard = findViewById(R.id.leaderboardButton);

        db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("name", LoginActivity.username);
        user.put("score",MainActivity.score);

        db.collection("users").add(user);


        scoreDisplay = findViewById(R.id.score);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.tryAgain);
        score = getIntent().getStringExtra("SCORE_KEY");
        username = getIntent().getStringExtra("NAME_KEY");
        scoreDisplay.setText("Your score: "+score);
        textView.setText("Congrats " + username + "! You finished the test.");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

    }
}