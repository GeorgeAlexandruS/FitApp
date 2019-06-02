package com.example.fittrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        EditText messageWeight = findViewById(R.id.textTargetWeight);
        String weight = messageWeight.getText().toString();
        i.putExtra("weightTarget", weight);

        EditText messageBMI = findViewById(R.id.textTargetBMI);
        String bmi = messageBMI.getText().toString();
        i.putExtra("bmiTarget", bmi);


//write to firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference weightRef = database.getReference("Weight target");
        DatabaseReference bmiRef = database.getReference("BMI target");

        weightRef.setValue(weight);
        bmiRef.setValue(bmi);


        startActivity(i);

    }


}
