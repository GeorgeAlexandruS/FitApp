package com.example.fittrackapp;

import android.arch.core.util.Function;
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
        confirmProgress = findViewById(R.id.confirmBodyStatus);
        confirmProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToActivityProgress();
            }
        });

    }

    protected void returnToActivityProgress() {
        Intent i = new Intent(UpdateProgressActivity.this, ProgressActivity.class);

        EditText messageWeight = findViewById(R.id.textNewWeight);
        String weight = messageWeight.getText().toString();
        i.putExtra("weightUpdate", weight);

        EditText messageBMI = findViewById(R.id.textNewBMI);
        String bmi = messageBMI.getText().toString();
        i.putExtra("bmiUpdate", bmi);


        //write to firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference weightRef = database.getReference("Weight current");
        DatabaseReference bmiRef = database.getReference("BMI current");

        weightRef.setValue(weight);
        bmiRef.setValue(bmi);


        startActivity(i);

    }


}
