package com.example.myclinic;

public class Booking {
    private String id ;
    private String patientname ;
    private String patientemail;
    private String patientphone;
    private String patientage;
    private String Date;
    private String Time;
    private String Note;


    public Booking( String patientname, String patientemail, String patientphone, String patientage, String date, String time, String note ,String id) {
        this.patientname = patientname;
        this.patientemail = patientemail;
        this.patientphone = patientphone;
        this.patientage = patientage;
        this.Date = date;
        Time = time;
        Note = note;
        this.id = id ;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientemail() {
        return patientemail;
    }

    public void setPatientemail(String patientemail) {
        this.patientemail = patientemail;
    }

    public String getPatientphone() {
        return patientphone;
    }

    public void setPatientphone(String patientphone) {
        this.patientphone = patientphone;
    }

    public String getPatientage() {
        return patientage;
    }

    public void setPatientage(String patientage) {
        this.patientage = patientage;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
