package com.example.fittrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dashboard);

        Button confirmProgress;
        confirmProgress = findViewById(R.id.confirmDashboard);
        confirmProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToActivityDashboard();
            }
        });

    }

    protected void returnToActivityDashboard() {
        Intent i = new Intent(UpdateDashboardActivity.this, DashboardActivity.class);

        String food = null;
        String fitness = null;
        try {
            EditText messageFood = findViewById(R.id.textName);
            food = messageFood.getText().toString();
            i.putExtra("foodUpdate", food);

            EditText messageFitness = findViewById(R.id.textFitness);
            fitness = messageFitness.getText().toString();
            i.putExtra("fitnessUpdate", fitness);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //write to firebase

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference foodRef = database.getReference("Food current");
            DatabaseReference fitnessRef = database.getReference("Fitness current");
            foodRef.setValue(food);
            fitnessRef.setValue(fitness);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
