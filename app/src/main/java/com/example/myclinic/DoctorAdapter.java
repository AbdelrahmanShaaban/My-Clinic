package com.example.myclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.myViewHolder> {

    ArrayList<Booking> bookdata ;
    Context cont ;
    public OndoctorClickListener ondoctorClickListener;


    public interface OndoctorClickListener{
        void onImageClick(  int position);
    }

    public DoctorAdapter(ArrayList<Booking> bookdata, Context cont , OndoctorClickListener ondoctorClickListener) {

        this.bookdata = bookdata;
        this.cont = cont;
        this.ondoctorClickListener=ondoctorClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewdoctor,viewGroup,false);
        return new myViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i  ) {

        final int position = i ;

        myViewHolder.namepatient.setText(bookdata.get(i).getPatientname());
        myViewHolder.emailpatient.setText(bookdata.get(i).getPatientemail());
        myViewHolder.phonepatient.setText(bookdata.get(i).getPatientphone());
        myViewHolder.agepatient.setText(bookdata.get(i).getPatientage());
        myViewHolder.date.setText(bookdata.get(i).getDate());
        myViewHolder.time.setText(bookdata.get(i).getTime());
        myViewHolder.notes.setText(bookdata.get(i).getNote());
        myViewHolder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ondoctorClickListener.onImageClick( position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookdata.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView namepatient ,agepatient , phonepatient , emailpatient  , time , date , notes   ;
        public ImageView deleteitem ;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            namepatient = itemView.findViewById(R.id.textViewnamedoctor);
            emailpatient = itemView.findViewById(R.id.textViewemaildoctor);
            agepatient = itemView.findViewById(R.id.textViewagedoctor);
            phonepatient = itemView.findViewById(R.id.textViewphodoctor);
            time = itemView.findViewById(R.id.textViewtimes);
            date=itemView.findViewById(R.id.textViewdates);
            notes=itemView.findViewById(R.id.textViewnotess);
            deleteitem=itemView.findViewById(R.id.imageView2);


        }



    }

}
