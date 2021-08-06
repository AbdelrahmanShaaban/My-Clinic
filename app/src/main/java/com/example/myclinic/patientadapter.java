package com.example.myclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class patientadapter extends RecyclerView.Adapter<patientadapter.myViewHolder> {
    ArrayList<Doctor>docdata ;
    Context cont ;
    public patientadapter(ArrayList<Doctor> docdata, Context cont) {

        this.docdata = docdata;
        this.cont = cont;
    }

    @NonNull
    @Override
    public patientadapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewpatient,viewGroup,false);
        return new patientadapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull patientadapter.myViewHolder myViewHolder, int i) {
        myViewHolder.doctoremail.setText(docdata.get(i).getEmail());
        myViewHolder.doctorname.setText(docdata.get(i).getName());
        myViewHolder.doctorage.setText(docdata.get(i).getAge());
        myViewHolder.doctorphone.setText(docdata.get(i).getPhone());
        myViewHolder.doctoraddress.setText(docdata.get(i).getAddress());
        myViewHolder.doctorspecial.setText(docdata.get(i).getSpecialization());
        myViewHolder.dates.setText(docdata.get(i).getDate());
        myViewHolder.times.setText(docdata.get(i).getTime());
        myViewHolder.notes.setText(docdata.get(i).getNote());

    }

    @Override
    public int getItemCount() {
        return docdata.size();
    }

public class myViewHolder extends RecyclerView.ViewHolder {
    public TextView doctoremail ,doctorname , doctorage , doctorphone  , doctoraddress , doctorspecial , notes , dates , times  ;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        doctoremail=itemView.findViewById(R.id.docemaill);
        doctorname=itemView.findViewById(R.id.doctornam);
        doctorage=itemView.findViewById(R.id.docages);
        doctorphone=itemView.findViewById(R.id.docphones);
        doctoraddress=itemView.findViewById(R.id.addressss);
        doctorspecial=itemView.findViewById(R.id.special);
        notes=itemView.findViewById(R.id.notesss);
        dates=itemView.findViewById(R.id.datesss);
        times=itemView.findViewById(R.id.timesss);



    }


}}
