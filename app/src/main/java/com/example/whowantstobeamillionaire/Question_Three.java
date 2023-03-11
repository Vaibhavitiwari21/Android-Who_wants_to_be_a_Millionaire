package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Question_Three extends AppCompatActivity {

    private Button submitQ3;
    private int totalMoney;
    private List<Integer> radioButtonIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_three);

        // Get the score from the previous activity
        Intent intent = getIntent();
        totalMoney = intent.getIntExtra("prize", 0);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your Total Prize Money: $" + totalMoney);

        submitQ3 = findViewById(R.id.btn_Q1);
        // Get the selected answer
        RadioGroup radioGroup = findViewById(R.id.R3Q3);
        radioButtonIds = new ArrayList<>();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View child = radioGroup.getChildAt(i);
            if (child instanceof RadioButton) {
                radioButtonIds.add(child.getId());
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    // Change the background color of the selected RadioButton to yellow
                    radioButton.setBackgroundColor(0xFFFFFF00);
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int id : radioButtonIds) {
                    RadioButton radioButton = findViewById(id);
                    if (radioButton != null) {
                        if (id == checkedId) {
                            // Change the background color of the selected RadioButton to yellow
                            radioButton.setBackgroundColor(0xFFFFFF00);
                        } else {
                            // Reset the background color of the unselected RadioButton to the default color
                            radioButton.setBackgroundResource(android.R.drawable.btn_default);
                        }
                    }
                }
            }
        });

        // If player wants to quit the game
        Button quitButton = findViewById(R.id.btn_Q2);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Quit = new Intent(Question_Three.this, Winner.class);
                Quit.putExtra("prize", totalMoney);
                startActivity(Quit);

            }
        });

        submitQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected answer
                RadioGroup radioGroup = findViewById(R.id.R3Q3);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String answer = radioButton.getText().toString();
                // Check the answer and update the score
                if (answer.equals("Python")) {// Add $100 to the player's total money
                    totalMoney += 1000;
                    // Change the background color of the selected RadioButton to green
                    radioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    // Display a congratulations message with the updated score
                    String message = "Congratulations! You have earned $" + totalMoney;
                    Toast.makeText(Question_Three.this, message, Toast.LENGTH_SHORT).show();

                    // Delay for 2 seconds and then start the second activity
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent answerOne = new Intent(Question_Three.this, Question_Four.class);
                            answerOne.putExtra("prize", totalMoney);
                            startActivity(answerOne);
                        }
                    }, 2000); // 2000 milliseconds = 2 seconds

                } else {
                    // Change the background color of the selected RadioButton to red
                    radioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    totalMoney = 0;
                    // Display a Defeat message with the updated score
                    String message = "Unfortunately its a wrong answer! You have earned $" + totalMoney;
                    Toast.makeText(Question_Three.this, message, Toast.LENGTH_SHORT).show();
                    // Delay for 2 seconds and then start the over activity
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent answerOne = new Intent(Question_Three.this, GameOver.class);
                            answerOne.putExtra("prize", totalMoney);
                            startActivity(answerOne);
                        }
                    }, 2000); // 2000 milliseconds = 2 seconds
                }
                // Update the score TextView with the new totalMoney value
                scoreTextView.setText("Your Total Prize Money: $" + totalMoney);

            }
        });
    }
}