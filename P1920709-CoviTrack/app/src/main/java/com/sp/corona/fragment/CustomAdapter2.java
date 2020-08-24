package com.sp.corona.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.corona.R;


public class CustomAdapter2  extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder2> {
    String symptom[] , detail[];
    Context context;

    public CustomAdapter2(Context ct, String[] symptoms,String[] details){
        context=ct;
        symptom=symptoms;
        detail=details;

    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_symptoms,parent,false);//check whether need attach to root
        return new MyViewHolder2(view);




    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        holder.symptoms.setText(detail[position]);
        holder.details.setText(symptom[position]);


    }

    @Override
    public int getItemCount() {
        return symptom.length;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView symptoms;
        TextView details;


        public MyViewHolder2(@NonNull View itemView) {

            super(itemView);

            symptoms=itemView.findViewById(R.id.txtSymptoms);
            details=itemView.findViewById(R.id.txtSymptomsDetail);
        }
    }
}