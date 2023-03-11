package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Winner extends AppCompatActivity {

    private int totalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        // Get the score from the previous activity
        Intent intent = getIntent();
        totalMoney = intent.getIntExtra("prize", 0);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your Total Prize Money: $" + totalMoney);

        Button playAgainButton = findViewById(R.id.playAgain);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Winner.this, Price_Money.class);
                startActivity(intent);
                finish();
            }
        });
    }
}