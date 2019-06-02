package com.example.fittrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateGoalsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_goals);


        Button confirmGoals;
        confirmGoals = findViewById(R.id.confirmGoals);
        confirmGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToActivityGoals();
            }
        });

    }

    protected void returnToActivityGoals() {
        Intent i = new Intent(UpdateGoalsActivity.this, GoalsActivity.class);

        String weight = null;
        String bmi = null;
        String days = null;
        try {
            EditText messageWeight = findViewById(R.id.textTargetWeight);
            weight = messageWeight.getText().toString();
            i.putExtra("weightTarget", weight);

            EditText messageBMI = findViewById(R.id.textTargetBMI);
            bmi = messageBMI.getText().toString();
            i.putExtra("bmiTarget", bmi);

            EditText messageDays = findViewById(R.id.textTargetDays);
            days = messageDays.getText().toString();
            i.putExtra("daysTarget", days);
        } catch (Exception e) {
            e.printStackTrace();
        }


//write to firebase

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference weightRef = database.getReference("Weight target");
            DatabaseReference bmiRef = database.getReference("BMI target");
            DatabaseReference daysRef = database.getReference("Days target");


            weightRef.setValue(weight);
            bmiRef.setValue(bmi);
            daysRef.setValue(days);
        } catch (Exception e) {
            e.printStackTrace();
        }


        startActivity(i);

    }


}
