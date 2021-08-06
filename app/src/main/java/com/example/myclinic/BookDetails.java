package com.example.myclinic;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {
    DatabaseReference myRef1;
    FirebaseDatabase database;
    EditText note ;
    TextView date , time  ;
    Button confirm , choosedate ,choosetime;
    String docEmail;

    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference("bookings");
        note = (EditText) findViewById(R.id.noteedittxt);
        date = (TextView) findViewById(R.id.datetxt);
        confirm=(Button)findViewById(R.id.confirmbtn);
        choosedate=(Button)findViewById(R.id.datebtn);
        choosetime= (Button)findViewById(R.id.timebtn);
        activity = this;
        Intent i = getIntent();
       docEmail = i.getStringExtra("email");
       time = (TextView)findViewById(R.id.timetxt);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = myRef1.push().getKey();
                Map<String , String> map=new HashMap<>();
                map.put("id" , id);
                map.put("doc_email" , docEmail);
                map.put("patient_email", Patient_info.patientEmail);
                map.put("date",date.getText().toString() );
                map.put("time",time.getText().toString() );
                map.put("note", note.getText().toString());
                myRef1.push().setValue(map);
                Toast.makeText(BookDetails.this,"is confirmed",Toast.LENGTH_LONG).show();

            }
        });
        choosedate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = new Date();
        dialogFragment.show(getSupportFragmentManager(),"date picker");
    }
});
        choosetime.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = new Time();
        dialogFragment.show(getSupportFragmentManager(), "time picker");
    }
});
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentdate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(currentdate);

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time.setText( hourOfDay+":"+minute );
    }
}
