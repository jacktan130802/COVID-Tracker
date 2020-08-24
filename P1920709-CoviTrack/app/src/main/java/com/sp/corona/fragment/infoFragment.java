package com.sp.corona.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.corona.KnowMore;
import com.sp.corona.PrecautionActivity;
import com.sp.corona.R;
import com.sp.corona.SymptomActivity;

import butterknife.BindView;
import butterknife.ButterKnife;



public class infoFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.recyclerView100)
    RecyclerView recyclersymptoms;
    @BindView(R.id.recyclerViewPrecautions)
    RecyclerView recyclerprecaution;
    @BindView(R.id.txtViewPrecautions)
    TextView precautionstxt;
    @BindView(R.id.txtViewSymptoms)
    TextView symptomstxt;
    @BindView(R.id.btnKnowMore)
    Button  knowmore;
    String precautions[] ={"Remember to regularly take your own temperature. Two times a day. When temperature exceeds 37.5, consult a doctor.","Start to pay more attention to your body when there is constant coughing. If you feel any abnormalities with the coughing, consult a doctor.","If you have a bad headache suddenly, drink mmore water and take an MC or rest.If it continues to persist, consult a doctor.","Take note of your health if you have shortness of breath","Take note of your health if you have Sore Throat"};


    String symptoms[]= {"Fever","Cough","Headache","Shortness of Breath","Sore Throat"};

    String precautions2[] ={"Clean your hands often","Avoid close contact","Wear a mask","Clean and disinfect"};
    String precaution1[] ={"Wash your hands often with soap and water for at least 20 seconds especially after you have been in a public place, or after blowing your nose, coughing, or sneezing.","If possible, maintain 6 feet between the person who is sick and other household members."," Wear a cloth face cover in public settings and when around people who don’t live in your household, especially when other social distancing measures are difficult to maintain.","Clean your house regularly.If possible , disinfect the house."};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.amain, container, false);
        ButterKnife.bind(this, root);
        precautionstxt.setOnClickListener(this);
        symptomstxt.setOnClickListener(this);
        knowmore.setOnClickListener(this);
//        CustomAdapter adapter = new CustomAdapter(this,symptoms,precautions);
        recyclersymptoms.setAdapter(new CustomAdapter(getActivity(),symptoms,precautions));
        recyclerprecaution.setAdapter(new CustomAdapter2(getActivity(),precaution1,precautions2));
//        recyclersymptoms.setLayoutManager(new );
        return root;
    }



    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.txtViewSymptoms){
            Intent intent= new Intent(getActivity(), SymptomActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.txtViewPrecautions){
            Intent intent = new Intent(getActivity(), PrecautionActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnKnowMore){
            Intent intent = new Intent(getActivity(), KnowMore.class);
            startActivity(intent);
        }

    }

}
