package com.sp.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class PrecautionActivity extends AppCompatActivity {

    String precautions2[] ={"Clean your hands often","Avoid close contact","Wear a mask","Clean and disinfect"};
    String precaution1[] ={"Wash your hands often with soap and water for at least 20 seconds especially after you have been in a public place, or after blowing your nose, coughing, or sneezing.","If possible, maintain 6 feet between the person who is sick and other household members."," Wear a cloth face cover in public settings and when around people who don’t live in your household, especially when other social distancing measures are difficult to maintain.","Clean your house regularly.If possible , disinfect the house."};

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_precaution);
        recyclerView =findViewById(R.id.recyclerView399);
        recyclerView.setAdapter(new CustomAdapter3(this,precautions2,precaution1));
        super.onCreate(savedInstanceState);
    }
}
