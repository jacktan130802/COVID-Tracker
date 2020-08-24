
////statewise
package com.sp.corona.fragment;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//
//import com.aurora.corona.Constants;
//import com.aurora.corona.R;
//import com.aurora.corona.model.casetime.Statewise;
//import com.aurora.corona.util.PrefUtil;
//import com.aurora.corona.util.Util;
//import com.google.gson.Gson;
//
//import org.apache.commons.lang3.StringUtils;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class DashboardFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
//
//    @BindView(R.id.txt_recovered)
//    TextView txtRecovered;
//    @BindView(R.id.txt_new_cases)
//    TextView txtNewCases;
//    @BindView(R.id.txt_deaths)
//    TextView txtDeaths;
//    @BindView(R.id.txt_all_total)
//    TextView txtAllTotal;
//    @BindView(R.id.txt_all_active)
//    TextView txtAllActive;
//    @BindView(R.id.txt_all_cured)
//    TextView txtAllCured;
//    @BindView(R.id.txt_all_deaths)
//    TextView txtAllDeaths;
//    @BindView(R.id.layout_bottom)
//    ConstraintLayout layoutBottom;
//    @BindView(R.id.txt_today_last_updated)
//    AppCompatTextView txtTodayLastUpdated;
//    @BindView(R.id.txt_all_last_updated)
//    AppCompatTextView txtAllLastUpdated;
//
//    private Gson gson = new Gson();
//    private SharedPreferences sharedPreferences;
//
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//allow change???when clicl next or smth
//        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        sharedPreferences = Util.getPrefs(requireContext());
//        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        updateDailyStatus();
//        updateOverallDetails();
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        switch (key) {
//            case Constants.PREFERENCE_KEY_VALUES:
//                updateDailyStatus();
//                break;
//            case Constants.PREFERENCE_OVERALL_DATA:
//                updateOverallDetails();
//                break;
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        try {
//            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
//        } catch (Exception ignored) {
//
//        }
//        super.onDestroy();
//    }
//
//    private void updateDailyStatus() {
//        final String rawTimeSeries = PrefUtil.getString(requireContext(), Constants.PREFERENCE_KEY_VALUES);
//        final Statewise key_values = gson.fromJson(rawTimeSeries, Statewise.class);
//
//        if (key_values != null) {
//            txtRecovered.setText(key_values.getDeltarecovered());//Main Page recovereed part.
//            txtNewCases.setText(key_values.getDeltaconfirmed());
//            txtDeaths.setText(key_values.getDeltadeaths());
//            txtTodayLastUpdated.setText(StringUtils.joinWith(" : ", "Last updated", key_values.getLastupdatedtime()));
//        }
//    }
//
//    private void updateOverallDetails() {
//        final String rawOverAll = PrefUtil.getString(requireContext(), Constants.PREFERENCE_OVERALL_DATA);
//        final Statewise statewise = gson.fromJson(rawOverAll, Statewise.class);
//
//        if (statewise != null) {
//            txtAllTotal.setText(statewise.getConfirmed());//these methods are fixed yet not declared??
//            //is smewhat stored inside.Got certain ???
//            txtAllActive.setText(statewise.getActive());
//            txtAllCured.setText(statewise.getRecovered());
//            txtAllDeaths.setText(statewise.getDeaths());
//            txtAllLastUpdated.setText(StringUtils.joinWith(" : ", "Last updated", statewise.getLastupdatedtime()));
//        }
//    }
//}




import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sp.corona.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;

public class DashboardFragment extends Fragment{
    @BindView(R.id.txt_recovered)
    TextView txtRecovered;
    @BindView(R.id.txt_new_cases)
    TextView txtNewCases;
    @BindView(R.id.txt_deaths)
    TextView txtDeaths;

    AppCompatTextView txtTodayLastUpdated;
    AppCompatTextView txtAllLastUpdated;
    private TextView tvTotalConfirmed, tvTotalDeaths, tvTotalRecovered,txtactive;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // call view
        tvTotalConfirmed = root.findViewById(R.id.txt_all_total);
        tvTotalDeaths = root.findViewById(R.id.txt_all_deaths);
        tvTotalRecovered = root.findViewById(R.id.txt_all_cured);
        txtRecovered=root.findViewById(R.id.txt_recovered);
        txtNewCases=root.findViewById(R.id.txt_new_cases);
        txtDeaths=root.findViewById(R.id.txt_deaths);
        txtactive=root.findViewById(R.id.txt_all_active);
        txtAllLastUpdated=root.findViewById(R.id.txt_all_last_updated);
        progressBar = root.findViewById(R.id.progressBar2);
        txtTodayLastUpdated=root.findViewById((R.id.txt_today_last_updated));


        // call Volley
        getData();

        return root;
}

    private String getDate(long milliSecond){
        // Mon, 23 Mar 2020 02:01:04 PM fomrat...
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    txtactive.setText(jsonObject.getString("active"));
                    txtTodayLastUpdated.setText("Last updated:  "+getDate(jsonObject.getLong("updated")));
                    txtRecovered.setText(jsonObject.getString("todayRecovered"));//Main Page recovereed part.
                    txtNewCases.setText(jsonObject.getString("todayCases"));
                    txtDeaths.setText(jsonObject.getString("todayDeaths"));
                    txtAllLastUpdated.setText("Last updated:  "+getDate(jsonObject.getLong("updated")));

//                    txtTodayLastUpdated.setText("Last updated:  "+getDate(jsonObject.getLong("updated")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }
}
