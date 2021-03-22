package com.example.gmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.CollationElementIterator;
import java.util.HashMap;

public class ProfileLog extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String name, password, email, Err;
    TextView nameTV, emailTV, passwordTV, err/*,nameTV1,emailTV1*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_log);


        // displaying user data
        nameTV = (TextView) findViewById(R.id.home_name);
        emailTV = (TextView) findViewById(R.id.home_email);


       //  nameTV = (TextView) findViewById(R.id.home_name);
     //  nameTV1 = (TextView) findViewById(R.id.nameme);
     //  emailTV1 = (TextView) findViewById(R.id.emailme);
     //   emailTV = (TextView) findViewById(R.id.home_email);
        passwordTV = (TextView) findViewById(R.id.home_password);
        err = (TextView) findViewById(R.id.err);

        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        Err = getIntent().getStringExtra("err");

        nameTV.setText("Welcome "+name);
     //   nameTV1.setText(""+name);
        passwordTV.setText("Your password is "+password);
        emailTV.setText("Your email is "+email);
       // emailTV1.setText(""+email);
        err.setText(Err);


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }

        @Override
        public void onBackPressed () {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.profile_log, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.action_contact) {

            } else if (id == R.id.action_update) {

            } else if (id == R.id.action_logout) {

            } else if (id == R.id.action_settings) {
                Intent intent;
                intent = new Intent(ProfileLog.this, Contact.class);
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            } else if (id == R.id.see_donor) {

            } else if (id == R.id.see_request) {

            } else if (id == R.id.action_home) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
