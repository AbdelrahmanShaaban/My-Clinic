package com.example.myclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    ArrayList<Doctor> data ;
    Context cont ;
    public OnBookClickListener onBookClickListener;

    public interface OnBookClickListener{
        void onButtonClick(Doctor doctor);
    }
    public interface OnRateClickListener{
        void onButtonClick(Doctor doctor);
    }
    public MyAdapter(ArrayList<Doctor> data, Context cont, OnBookClickListener onBookClickListener ) {
        this.onBookClickListener = onBookClickListener;
        this.data = data;
        this.cont = cont;

    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview,viewGroup,false);
        return new myViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        final int currentItem = i;
        myViewHolder.name.setText("Name: " + data.get(i).getName().toString());
        myViewHolder.age.setText("age: " + data.get(i).getAge());
        myViewHolder.phone.setText("phone: " + data.get(i).getPhone().toString());
        myViewHolder.address.setText("address: " + data.get(i).getAddress().toString());
        myViewHolder.specialization.setText("specialization: " + data.get(i).getSpecialization().toString());
        myViewHolder.email.setText("email: " + data.get(i).getEmail().toString());
        myViewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookClickListener.onButtonClick(data.get(currentItem));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView name ,age , phone , address , email , specialization ;
        public Button book , Rate;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewnamepat);
            email = itemView.findViewById(R.id.textViewemapat);
            age = itemView.findViewById(R.id.textViewagepat);
            phone = itemView.findViewById(R.id.textViewdate);
            address = itemView.findViewById(R.id.textViewtime);
            specialization = itemView.findViewById(R.id.textViewphonepatient);
            book = itemView.findViewById(R.id.book_button);



        }



    }

}
