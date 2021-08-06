package com.example.myclinic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class Reverse extends AppCompatActivity {
    DatabaseReference myRef;
    DatabaseReference myRef1;

    FirebaseDatabase database;
    RecyclerView recyclerView ;
    patientadapter patientadapter ;
    ArrayList<Doctor> docdata ;
    String DoctorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);
        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference("bookings");
        myRef=database.getReference("Accounts");
        myRef.keepSynced(true);
        recyclerView = (RecyclerView)findViewById(R.id.rvdocc) ;
        recyclerView.setHasFixedSize(true);
        docdata = new ArrayList<>();
        patientadapter = new patientadapter(docdata,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientadapter);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds :dataSnapshot.getChildren())
                {
                    Log.d("doc_email",ds.child("patient_email").getValue().toString());
                    Log.d("doc_email from info",Patient_info.patientEmail);
                    if(ds.child("patient_email").getValue().toString().equals(Patient_info.patientEmail))
                    {

                        getbookingInfo(ds);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    private void getbookingInfo(final DataSnapshot dataSnapshotBooking){

        DoctorEmail = dataSnapshotBooking.child("doc_email").getValue().toString();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds :dataSnapshot.getChildren())
                {
                    if(ds.child("E_mail").getValue().toString().equals(DoctorEmail))
                    {
                        addToAdapter(dataSnapshotBooking,ds);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    private void addToAdapter(DataSnapshot dataSnapshotBooking, DataSnapshot dataSnapshotAccount){
        docdata.add(new Doctor(dataSnapshotAccount.child("name").getValue().toString()
                ,dataSnapshotAccount.child("E_mail").getValue().toString(),
                dataSnapshotAccount.child("address").getValue().toString(),
                dataSnapshotAccount.child("phone").getValue().toString(),
                dataSnapshotAccount.child("age").getValue().toString(),
                dataSnapshotAccount.child("specialization").getValue().toString(),
                dataSnapshotBooking.child("date").getValue().toString(),
                dataSnapshotBooking.child("time").getValue().toString(),
                dataSnapshotBooking.child("note").getValue().toString()));
        patientadapter.notifyDataSetChanged();
    }

}
