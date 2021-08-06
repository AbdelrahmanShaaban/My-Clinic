package com.example.myclinic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class patient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

     FirebaseAuth mAuth;
    FirebaseUser currentuser;
    DatabaseReference myRef;
    FirebaseDatabase database;
    RecyclerView recyclerView ;
    MyAdapter adapter ;
    ArrayList<Doctor> data ;
    Spinner spin ;
    Boolean userIsInteracting = false;



    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");

        myRef.keepSynced(true);
        recyclerView = (RecyclerView)findViewById(R.id.rv) ;
        recyclerView.setHasFixedSize(true);
        data = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(data, this, new MyAdapter.OnBookClickListener(){
            @Override
            public void onButtonClick(Doctor doctor) {
                Intent i = new Intent(patient.this,BookDetails.class);
              i.putExtra("email",doctor.getEmail());
              startActivity(i);
            }
        });


        recyclerView.setAdapter(adapter);
        spin = (Spinner)findViewById(R.id.spinner2);




        spin.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(!userIsInteracting){return;}

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        data.clear();

                        for (DataSnapshot ds :dataSnapshot.getChildren())
                        {
                            if(ds.child("Type").getValue().toString().equals("Doctor") ){


                            if(ds.child("specialization").getValue().toString().equals(spin.getSelectedItem().toString()))
                            {

                                data.add(new Doctor(ds.child("name").getValue().toString(),
                                        ds.child("E_mail").getValue().toString(),
                                        ds.child("address").getValue().toString(),
                                        ds.child("phone").getValue().toString(),
                                        ds.child("age").getValue().toString(),
                                        ds.child("specialization").getValue().toString()));

                                adapter.notifyDataSetChanged();

                            }

                            }

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
        getMenuInflater().inflate(R.menu.patient, menu);
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

        if (id == R.id.nav_home) {
            Intent i = new Intent(this,Homeuser.class);
            startActivity(i);

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this,Reverse.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent i = new Intent(this,signin.class);
            startActivity(i);

        } else if (id == R.id.nav_tools) {


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    }
