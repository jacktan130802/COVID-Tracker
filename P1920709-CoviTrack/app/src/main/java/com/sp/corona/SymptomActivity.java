package com.sp.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SymptomActivity extends AppCompatActivity {
    String precautions[] ={"Remember to regularly take your own temperature. Two times a day. When temperature exceeds 37.5, consult a doctor.","Start to pay more attention to your body when there is constant coughing. If you feel any abnormalities with the coughing, consult a doctor.","If you have a bad headache suddenly, drink mmore water and take an MC or rest.If it continues to persist, consult a doctor.","Take note of your health if you have shortness of breath","Take note of your health if you have Sore Throat"};
    String symptoms[]= {"Fever","Cough","Headache","Shortness of Breath","Sore Throat"};
    RecyclerView recyclerView2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_symptoms);
        recyclerView2 =findViewById(R.id.recyclerView2000);
        recyclerView2.setAdapter(new CustomAdapter3(this,symptoms,precautions));
        super.onCreate(savedInstanceState);
    }
}
