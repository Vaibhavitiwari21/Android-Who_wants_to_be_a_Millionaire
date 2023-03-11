package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    // Define a constant integer SPLASH_LENGTH to represent the duration of the splash screen in milliseconds.
    private final int SPLASH_LENGTH = 3000;
    // Override the onCreate method to set up the MainActivity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the onCreate method of the superclass.
        super.onCreate(savedInstanceState);
        // Set the content view of the MainActivity to the activity_main layout file.
        setContentView(R.layout.activity_main);

        // Create a new Handler object.
        new Handler().postDelayed(new Runnable() {
            // Override the run method to specify the actions to be performed after the SPLASH_LENGTH delay.
            @Override
            public void run() {
                // Create a new Intent to start the LoginActivity.
                Intent mainIntent = new Intent(MainActivity.this, LetsPlay.class);
                // Start the LoginActivity using the created Intent.
                MainActivity.this.startActivity(mainIntent);
                // Finish the MainActivity so that the user cannot go back to it using the back button.
                MainActivity.this.finish();
            }
        }, SPLASH_LENGTH);
    }
}