package com.example.myclinic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import life.sabujak.roundedbutton.RoundedButton;

public class signin extends AppCompatActivity {
    EditText e , p ;
    private FirebaseAuth mAuth;
    DatabaseReference myRef , myRef1;
    FirebaseDatabase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        e= (EditText)findViewById(R.id.editText);
        p= (EditText)findViewById(R.id.editText2);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");
        myRef1 = database.getReference("bookings");

    }

    public void signin(View view) {
        mAuth.signInWithEmailAndPassword(e.getText().toString(), p.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signin.this,"login success",Toast.LENGTH_LONG).show();
                            Query mquery = myRef.getRef().orderByChild("E_mail").equalTo(e.getText().toString());
                            mquery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds :dataSnapshot.getChildren())
                                {
                                    if(ds.child("Type").getValue().toString().equals("Doctor"))
                                    {
                                        Doctorinfo.DoctorName=ds.child("name").getValue().toString();
                                        Doctorinfo.DoctorEmail=ds.child("E_mail").getValue().toString();
                                        Doctorinfo.DoctorPhone=ds.child("phone").getValue().toString();
                                        Doctorinfo.DoctorAge=ds.child("age").getValue().toString();
                                        Doctorinfo.DoctorSpecialization=ds.child("specialization").getValue().toString();

                                        Intent intent=new Intent(signin.this,mydoctor.class);
                                        startActivity(intent);
                                    }
                                    else if(ds.child("Type").getValue().toString().equals("user")) {
                                        Patient_info.patientName =ds.child("name").getValue().toString();
                                        Patient_info.patientEmail =ds.child("E_mail").getValue().toString();
                                        Patient_info.patientPhone =ds.child("phone").getValue().toString();
                                        Patient_info.getPatientAge =ds.child("age").getValue().toString();
                                        Intent intent=new Intent(signin.this,patient.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        } else {

                            Toast.makeText(signin.this,"login failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void up(View view) {
        Intent i =new Intent(this,signup.class);
        startActivity(i);
    }

}
