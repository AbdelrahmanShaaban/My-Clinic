package com.example.myclinic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class  signup extends AppCompatActivity {
    EditText name, email, password, age, phone , address;
    RadioButton doctor, user;
    RadioGroup radioGroup;
    Spinner spin ;
    private FirebaseAuth mAuth;
    String type;
    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText4);
        age = (EditText) findViewById(R.id.editText5);
        phone = (EditText) findViewById(R.id.editText7);
        address = (EditText) findViewById(R.id.editText8);
        doctor = (RadioButton) findViewById(R.id.radioButton);
        user = (RadioButton) findViewById(R.id.radioButton2);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        spin=(Spinner)findViewById(R.id.spinner);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");

    }

    public void select(View view) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String s = "";
        if (selectedId == doctor.getId()) {
            spin.setEnabled(true);
            type = "Doctor";
        } else if (selectedId == user.getId()) {
            spin.setEnabled(false);
            type = "user";
        }
    }

    public void signup(View view) {
        if (name.getText().toString().equals(""))
        {
            name.setError("please fill the field");
        }
        if (email.getText().toString().equals(""))
        {
            email.setError("please fill the field");
        }
        if (password.getText().toString().equals(""))
        {
            password.setError("please fill the field");
        }
        if (age.getText().toString().equals(""))
        {
            age.setError("please fill the field");
        }
        if (phone.getText().toString().equals(""))
        {
            phone.setError("please fill the field");
        }
        if (address.getText().toString().equals(""))
        {
            address.setError("please fill the field");
        }
        if (!doctor.isChecked() && !user.isChecked()) {
            doctor.setError("not checked");
            user.setError("not checked");
        }

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(signup.this,"E_mail is created",Toast.LENGTH_LONG).show();
                            Map<String , String> map=new HashMap<>();
                            map.put("name" , name.getText().toString());
                            map.put("E_mail", email.getText().toString());
                            map.put("age", age.getText().toString());
                            map.put("phone", phone.getText().toString());
                            map.put("address", address.getText().toString());

                            if(type.equals("Doctor")) {

                                map.put("specialization",spin.getSelectedItem().toString());
                                map.put("Type", type);
                            }
                            if(type.equals("user")) {

                                map.put("Type", type);
                            }
                            myRef.push().setValue(map);


                        } else {

                            Toast.makeText(signup.this,"E_mail is not created",Toast.LENGTH_LONG).show();

                        }
                        // ...
                    }
                });


    }
}