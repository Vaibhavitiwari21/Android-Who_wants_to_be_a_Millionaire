package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class Question_One extends AppCompatActivity {
    private Button submitQ1;
    private int totalMoney = 0;
    private List<Integer> radioButtonIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_one);
        submitQ1 = findViewById(R.id.btn_Q1);

        RadioGroup radioGroup = findViewById(R.id.R1Q1);
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
                Intent Quit = new Intent(Question_One.this, Winner.class);
                Quit.putExtra("prize", totalMoney);
                startActivity(Quit);

            }
        });

        submitQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected answer
                RadioGroup radioGroup = findViewById(R.id.R1Q1);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String answer = radioButton.getText().toString();
                // Check the answer and update the score
                if (answer.equals("Vegan")) {
                    // Add $100 to the player's total money
                    totalMoney += 500;
                    // Change the background color of the selected RadioButton to green
                    radioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    // Display a congratulations message with the updated score
                    String message = "Congratulations! You have earned $" + totalMoney;
                    Toast.makeText(Question_One.this, message, Toast.LENGTH_SHORT).show();

                    // Delay for 2 seconds and then start the second activity
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent answerOne = new Intent(Question_One.this, Question_Two.class);
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
                    Toast.makeText(Question_One.this, message, Toast.LENGTH_SHORT).show();
                    // Delay for 2 seconds and then start the over activity
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent answerOne = new Intent(Question_One.this, GameOver.class);
                            answerOne.putExtra("prize", totalMoney);
                            startActivity(answerOne);
                        }
                    }, 2000); // 2000 milliseconds = 2 seconds
                }
                // Update the score TextView with the new totalMoney value
                TextView scoreTextView = findViewById(R.id.scoreTextView);
                scoreTextView.setText("Your Total Prize Money: $" + totalMoney);

            }
        });
    }
}

