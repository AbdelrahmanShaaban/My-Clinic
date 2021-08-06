package com.example.myclinic;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Doctor {
    String name;
    String email;
    String age;
    String address;
    String phone;
    String specialization;
     String Date;
     String Time;
     String Note;
    public  Doctor(){}


    public Doctor(String name ,String email, String address, String phone,String age ,String specialization) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.specialization = specialization;
        this.age = age;
    }
    public Doctor(String name ,String email, String address, String phone,String age ,String specialization , String Date ,String Time ,  String Note) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.specialization = specialization;
        this.age = age;
        this.Date =Date ;
        this.Time=Time ;
        this.Note=Note ;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
