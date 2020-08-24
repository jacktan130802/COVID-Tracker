package com.sp.corona.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.corona.R;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
String symptom[] , detail[];
    Context context;

    public CustomAdapter(Context ct, String[] symptoms,String[] details){
        context=ct;
        symptom=symptoms;
        detail=details;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_symptoms,parent,false);//check whether need attach to root
        return new MyViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.symptoms.setText(symptom[position]);
        holder.details.setText(detail[position]);

    }

    @Override
    public int getItemCount() {
        return symptom.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView symptoms;
        TextView details;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            symptoms=itemView.findViewById(R.id.txtSymptoms);
            details=itemView.findViewById(R.id.txtSymptomsDetail);
        }
    }



}