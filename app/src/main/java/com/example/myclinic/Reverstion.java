package com.example.myclinic;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class Reverstion extends AppCompatActivity {
    DatabaseReference myRef;
    DatabaseReference myRef1;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    DoctorAdapter doctorAdapter;
    ArrayList<Booking> bookdata;
    String patientEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverstion);
        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference("bookings");
        myRef = database.getReference("Accounts");
        myRef.keepSynced(true);
        recyclerView = (RecyclerView) findViewById(R.id.rvDoctor);
        recyclerView.setHasFixedSize(true);
        bookdata = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(bookdata, this, new DoctorAdapter.OndoctorClickListener() {
            @Override
            public void onImageClick(int position) {
                delete(position);
                removeitem(position);
            }


        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(doctorAdapter);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("doc_email", ds.child("doc_email").getValue().toString());
                    Log.d("doc_email from info", Doctorinfo.DoctorEmail);
                    if (ds.child("doc_email").getValue().toString().equals(Doctorinfo.DoctorEmail)) {
                        Log.d("There are rquests", "Add");
                        getPatientInfo(ds);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    private void getPatientInfo(final DataSnapshot dataSnapshotBooking) {
        patientEmail = dataSnapshotBooking.child("patient_email").getValue().toString();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("E_mail").getValue().toString().equals(patientEmail)) {
                        addToAdapter(dataSnapshotBooking, ds);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    private void addToAdapter(DataSnapshot dataSnapshotBooking, DataSnapshot dataSnapshotAccount) {
        bookdata.add(new Booking(
                dataSnapshotAccount.child("name").getValue().toString(),
                dataSnapshotAccount.child("E_mail").getValue().toString(),
                dataSnapshotAccount.child("phone").getValue().toString(),
                dataSnapshotAccount.child("age").getValue().toString(),
                dataSnapshotBooking.child("date").getValue().toString(),
                dataSnapshotBooking.child("time").getValue().toString(),
                dataSnapshotBooking.child("note").getValue().toString(),
                dataSnapshotBooking.child("id").getValue().toString()));
                doctorAdapter.notifyDataSetChanged();
    }
    public void removeitem(int position) {
        bookdata.remove(position);
        doctorAdapter.notifyItemRemoved(position);
    }
    public void delete(int position ) {
         final Booking booking = bookdata.get(position);
        final AlertDialog.Builder builder=new AlertDialog.Builder(Reverstion.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete ?");
        builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.child("id").getValue().toString().equals(booking.getId())){
                                ds.getRef().removeValue();
                            }
                        }
                        Toast.makeText(Reverstion.this , "is deleted",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }
}







