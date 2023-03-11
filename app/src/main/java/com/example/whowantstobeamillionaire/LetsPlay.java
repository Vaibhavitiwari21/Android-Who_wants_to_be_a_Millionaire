package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LetsPlay extends AppCompatActivity {

    private Button next; // Button to start the next activity to start the game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_play);

        next = findViewById(R.id.next);
        // Set an OnClickListener on the register button to navigate to the RegisterForm activity
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LetsPlay.this, Register.class);
                startActivity(register);
            }
        });
    }
}