package com.example.whowantstobeamillionaire;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ParseException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Register extends AppCompatActivity {// Variables to store references to the EditText views for user input
    private EditText firstName;
    private EditText familyName;
    private EditText dateOfBirth;
    // Button to initiate the game
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assign the EditText views to their corresponding variables
        firstName = findViewById(R.id.firstname);
        familyName = findViewById(R.id.lastname);
        dateOfBirth = findViewById(R.id.dob);

        // Assign the Play button to its corresponding variable
        play = findViewById(R.id.play);
        // Add an OnClickListener to the play button to handle user input
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the validateInput method to verify the user's input
                if (validateInput()) {
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent registered = new Intent(Register.this, Price_Money.class);
                    startActivity(registered);
                }
            }
        });
    }

    /**
     * This method validates the user input fields and returns a boolean indicating the success of validation.
     *
     * @return A boolean indicating the success of input validation
     */
    private boolean validateInput() {
        String name = firstName.getText().toString().trim();
        String lastName = familyName.getText().toString().trim();
        String dobString = dateOfBirth.getText().toString().trim();

        // Check if the first name field is not empty and within the character limit (3-30 characters)
        if (TextUtils.isEmpty((CharSequence) name) || name.length() < 3 || name.length() > 30) {
            firstName.setError("Enter a valid first name (3-30 characters)");
            return false;
        }

        // Check if the last name field is not empty
        if (TextUtils.isEmpty(lastName)) {
            familyName.setError("Enter a valid last name");
            return false;
        }

        if (TextUtils.isEmpty(dobString) || (!dobString.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/[0-9]{4}$"))) {
            dateOfBirth.setError("Enter a valid date of birth. Use MM/DD/YYYY");
            return false;
        }

        // Convert the date of birth string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }

        // Check if the user is 18 years or older
        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        int yearOfBirth = cal.get(Calendar.YEAR);
        int monthOfBirth = cal.get(Calendar.MONTH);
        int dayOfBirth = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTimeInMillis(System.currentTimeMillis());
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int age = currentYear - yearOfBirth;
        if (currentMonth < monthOfBirth || (currentMonth == monthOfBirth && currentDay < dayOfBirth)) {
            age--;
        }
        if (age < 18) {
            dateOfBirth.setError("You must be 18 years or older to register");
            return false;
        }

        return true;
    }
}