package com.sp.corona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.corona.R;


public class CustomAdapter3  extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder3> {
    String symptom[] , detail[];
    Context context;

    public CustomAdapter3(Context ct, String[] symptoms,String[] details){
        context=ct;
        symptom=symptoms;
        detail=details;

    }

    @NonNull
    @Override
    public CustomAdapter3.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_symptoms,parent,false);//check whether need attach to root
        return new CustomAdapter3.MyViewHolder3(view);




    }



    @Override
    public void onBindViewHolder(@NonNull CustomAdapter3.MyViewHolder3 holder, int position) {

        holder.details.setText(detail[position]);
        holder.symptoms.setText(symptom[position]);


    }

    @Override
    public int getItemCount() {
        return symptom.length;
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        TextView symptoms;
        TextView details;


        public MyViewHolder3(@NonNull View itemView) {

            super(itemView);

            symptoms=itemView.findViewById(R.id.txtSymptoms);
            details=itemView.findViewById(R.id.txtSymptomsDetail);
        }
    }
}