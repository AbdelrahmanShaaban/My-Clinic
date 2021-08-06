package com.example.myclinic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

public class Homeuser extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentuser;
    DatabaseReference myRef;
    FirebaseDatabase database;
    TextView name , email , age , phone , address ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeuser);
        name=(TextView)findViewById(R.id.namepat);
        email=(TextView)findViewById(R.id.emaipat);
        age=(TextView)findViewById(R.id.agepat);
        phone=(TextView)findViewById(R.id.phonepat);
        address=(TextView)findViewById(R.id.addresspat);

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds :dataSnapshot.getChildren())
                {
                    if (Patient_info.patientEmail.equals(ds.child("E_mail").getValue().toString())) {
                        String names = ds.child("name").getValue().toString();
                        String emails = ds.child("E_mail").getValue().toString();
                        String phones = ds.child("phone").getValue().toString();
                        String ages = ds.child("age").getValue().toString();
                        String addresss = ds.child("address").getValue().toString();
                        name.setText("Name:  " + names);
                        email.setText("E_mail:  " + emails);
                        age.setText("age:  " + ages);
                        phone.setText("phone:  " + phones);
                        address.setText("address:  " + addresss);
                    }





                }



            }

            @Override
            public void onCancelled(DatabaseError error) {


            }
        });
    }
}
