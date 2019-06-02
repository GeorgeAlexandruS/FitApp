package com.example.fittrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GoalsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseDatabase databaseFit = FirebaseDatabase.getInstance();

        try {
            DatabaseReference bmiTargetRef = databaseFit.getReference("BMI target");
            bmiTargetRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    TextView bmiField = findViewById(R.id.target_bmi);
                    bmiField.setText(value);
                    Log.d("ok", "Target BMI is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("not ok", "Failed to read current BMI.", error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DatabaseReference weightTargetRef = databaseFit.getReference("Weight target");
            weightTargetRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    TextView weightTargetField = findViewById(R.id.target_weight);
                    weightTargetField.setText(value);
                    Log.d("ok", "Target weight is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("not ok", "Failed to target BMIe.", error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DatabaseReference daysTargetRef = databaseFit.getReference("Days target");
            daysTargetRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    TextView daysTargetField = findViewById(R.id.target_date);
                    daysTargetField.setText(value);
                    Log.d("ok", "Target days is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("not ok", "Failed to get days.", error.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                String targetWeight = extras.getString("weightTarget");
                TextView weightField = findViewById(R.id.target_weight);
                weightField.setText(targetWeight);

                String targetBMI = extras.getString("bmiTarget");
                TextView bmiField = findViewById(R.id.target_bmi);
                bmiField.setText(targetBMI);

                Toast.makeText(this, "Your target weight is: " + targetWeight + " and your target BMI is " + targetBMI, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Button goalsButton;
            goalsButton = findViewById(R.id.updateGoals);
            goalsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivityUpdateGoals();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected void openActivityUpdateGoals() {


        Intent intent = new Intent(this, UpdateGoalsActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.body_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_goals) {
            Intent intent = new Intent(getApplicationContext(), GoalsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_bodyprogress) {
            Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
