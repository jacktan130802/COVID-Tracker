package com.sp.corona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.corona.R;


public class CustomAdapter4  extends RecyclerView.Adapter<CustomAdapter4.MyViewHolder4> {
    String symptom[] , detail[];
    Context context;

    public CustomAdapter4(Context ct, String[] symptoms,String[] details){
        context=ct;
        symptom=symptoms;
        detail=details;

    }

    @NonNull
    @Override
    public CustomAdapter4.MyViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_symptoms,parent,false);//check whether need attach to root
        return new CustomAdapter4.MyViewHolder4(view);




    }



    @Override
    public void onBindViewHolder(@NonNull CustomAdapter4.MyViewHolder4 holder, int position) {

        holder.details.setText(detail[position]);
        holder.symptoms.setText(symptom[position]);


    }

    @Override
    public int getItemCount() {
        return symptom.length;
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        TextView symptoms;
        TextView details;


        public MyViewHolder4(@NonNull View itemView) {

            super(itemView);

            symptoms=itemView.findViewById(R.id.txtSymptoms);
            details=itemView.findViewById(R.id.txtSymptomsDetail);
        }
    }
}