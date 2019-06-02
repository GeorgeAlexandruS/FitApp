package com.example.fittrackapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String currentWeight = extras.getString("weightUpdate");
            TextView weightField = findViewById(R.id.weight);
            weightField.setText(currentWeight);

            String targetWeight = extras.getString("weightCurrent");
            TextView weightCurrentField = findViewById(R.id.target_weight);
            weightCurrentField.setText(targetWeight);

            String currentBMI = extras.getString("bmiUpdate");
            TextView bmiField = findViewById(R.id.bmi);
            bmiField.setText(currentBMI);

            String targetBMI = extras.getString("bmiCurrent");
            TextView bmiCurrentField = findViewById(R.id.target_bmi);
            bmiCurrentField.setText(targetBMI);

            ProgressBar progressBarWeight = findViewById(R.id.weightProgress);
            progressBarWeight.setProgress(Integer.parseInt(currentWeight));
            progressBarWeight.setMax(Integer.parseInt(currentWeight));

            ProgressBar progressBarBMI = findViewById(R.id.bmiProgress);
            progressBarBMI.setProgress(Integer.parseInt(currentBMI));
            progressBarBMI.setMax(Integer.parseInt(currentBMI));

            Toast.makeText(this, "Your weight is: " + currentWeight + "and your BMI is " + currentBMI, Toast.LENGTH_LONG).show();
        }


        Button updateButton;
        updateButton = findViewById(R.id.updateBodyProgress);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityUpdateProgress();
            }
        });

    }
//Toast.makeText(this, "There was an issue ", Toast.LENGTH_LONG).show();

    protected void openActivityUpdateProgress() {


        Intent intent = new Intent(this, UpdateProgressActivity.class);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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