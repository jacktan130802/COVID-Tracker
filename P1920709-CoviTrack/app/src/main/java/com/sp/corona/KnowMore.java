package com.sp.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class KnowMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_more);

    }
    public void learnmore(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gov.sg/features/covid-19"));
        startActivity(intent);

    }
}
