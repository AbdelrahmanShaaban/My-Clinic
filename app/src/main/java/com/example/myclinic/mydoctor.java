package com.example.myclinic;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;

public class mydoctor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FirebaseAuth mAuth;
    FirebaseUser currentuser;
    DatabaseReference myRef;
    FirebaseDatabase database;
    EditText name , email , age , phone , address , speacial;
    Button update ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydoctor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        name=(EditText) findViewById(R.id.editText9);
        email=(EditText) findViewById(R.id.editText10);
        age=(EditText) findViewById(R.id.editText11);
        phone=(EditText) findViewById(R.id.editText12);
        address=(EditText) findViewById(R.id.editText13);
        speacial=(EditText) findViewById(R.id.editText14);
        update=(Button)findViewById(R.id.button5);



        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds :dataSnapshot.getChildren())
                {
                    if(ds.child("E_mail").getValue().toString().equals(Doctorinfo.DoctorEmail)) {

                        name.setText(ds.child("name").getValue().toString());
                        email.setText(ds.child("E_mail").getValue().toString());
                        age.setText(ds.child("age").getValue().toString());
                        phone.setText(ds.child("phone").getValue().toString());
                        address.setText(ds.child("address").getValue().toString());
                        speacial.setText(ds.child("specialization").getValue().toString());

                    }

                    }



                }

            @Override
            public void onCancelled(DatabaseError error) {


            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("Accounts");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds :dataSnapshot.getChildren())
                        {
                            if(ds.child("E_mail").getValue().toString().equals(Doctorinfo.DoctorEmail)) {

                                Map<String , Object> map=new HashMap<>();
                                map.put("name" , name.getText().toString());
                                map.put("E_mail", email.getText().toString());
                                map.put("age", age.getText().toString());
                                map.put("phone", phone.getText().toString());
                                map.put("address", address.getText().toString());
                                map.put("specialization",speacial.getText().toString());
                                myRef.setValue(map);
                                Toast.makeText(mydoctor.this,"data is updated",Toast.LENGTH_LONG).show();

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
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
        getMenuInflater().inflate(R.menu.mydoctor, menu);
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


        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this,Reverstion.class);
            startActivity(i);


        } else if (id == R.id.nav_slideshow) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent i =new Intent(mydoctor.this,signin.class);
            startActivity(i);

        } else if (id == R.id.nav_tools) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
