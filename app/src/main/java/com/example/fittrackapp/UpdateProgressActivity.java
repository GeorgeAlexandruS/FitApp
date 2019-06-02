package com.example.fittrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_progress);

        Button confirmProgress;
        confirmProgress = findViewById(R.id.confirmDashboard);
        confirmProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToActivityProgress();
            }
        });

    }

    protected void returnToActivityProgress() {
        Intent i = new Intent(UpdateProgressActivity.this, ProgressActivity.class);

        String weight = null;
        try {
            EditText messageWeight = findViewById(R.id.textName);
            weight = messageWeight.getText().toString();
            i.putExtra("weightUpdate", weight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String bmi = null;
        try {
            EditText messageBMI = findViewById(R.id.textFitness);
            bmi = messageBMI.getText().toString();
            i.putExtra("bmiUpdate", bmi);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //write to firebase

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference weightRef = database.getReference("Weight current");
            DatabaseReference bmiRef = database.getReference("BMI current");
            weightRef.setValue(weight);
            bmiRef.setValue(bmi);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
