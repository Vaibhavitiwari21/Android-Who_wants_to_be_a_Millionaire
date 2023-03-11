package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    private int prize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        // Get the score from the previous activity
        Intent intent = getIntent();
        prize = intent.getIntExtra("prize", 0);

        TextView scoreTextView = findViewById(R.id.gameOver);
        scoreTextView.setText("Your Total Prize Money: $" + prize);
        Button playAgainButton = findViewById(R.id.playAgain);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOver.this, Price_Money.class);
                startActivity(intent);
                finish();
            }
        });

    }
}