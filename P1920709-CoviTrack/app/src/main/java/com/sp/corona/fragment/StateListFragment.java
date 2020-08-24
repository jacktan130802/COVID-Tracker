
//Case time series
package com.sp.corona.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sp.corona.CountryWiseDataActivity;

import com.sp.corona.Constants;
import com.sp.corona.CountryWiseModel;
import com.sp.corona.MainActivity4;
import com.sp.corona.R;
import com.sp.corona.RecyclerDataObserver;
import com.sp.corona.fragment.Adapters.CountryWiseAdapter;
import com.sp.corona.model.item.StateItem;
import com.sp.corona.sheet.StateWiseSheet;
import com.sp.corona.viewmodel.StateWiseReportModel;
import com.google.gson.Gson;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateListFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @BindView(R.id.txt_title2)
    AppCompatTextView txtTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
//    @BindView(R.id.activity_country_wise_recyclerview)
//    RecyclerView recycler2;
    @BindView(R.id.TabHost)
    TabHost host;
    @BindView(R.id.activity)
    Button newPage;
    @BindView(R.id.activity2)
    Button newPage2;
    private Gson gson = new Gson();
    private StateWiseReportModel model;
    private RecyclerDataObserver dataObserver;
    private FastAdapter<StateItem> fastAdapter;
    private ItemAdapter<StateItem> itemAdapter;
    private RecyclerView rv_country_wise;
    private CountryWiseAdapter countryWiseAdapter;
    private ArrayList<CountryWiseModel> countryWiseModelArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText et_search;
    private MainActivity4 activity = new MainActivity4();


    private String str_country, str_confirmed, str_confirmed_new, str_active, str_active_new, str_recovered, str_recovered_new,
            str_death, str_death_new, str_tests;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.main, container, false);

        ButterKnife.bind(this, root);

        newPage.setOnClickListener(this);
        newPage2.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.activity) {
            Intent intent = new Intent(getActivity(), CountryWiseDataActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.activity2) {

            host.setCurrentTab(1);//display detail form.-->2nd tab
        }

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecycler();

        model = new ViewModelProvider(this).get(StateWiseReportModel.class);
        model.getData().observe(getViewLifecycleOwner(), countryItems -> {
            itemAdapter.add(countryItems);

            host.setup();

            //Tab1

            TabHost.TabSpec spec = host.newTabSpec("List");
            spec.setContent(R.id.details_tab);
            spec.setIndicator("Countries");
            host.addTab(spec);

            //Tab 2
            spec = host.newTabSpec("Others");
            spec.setContent(R.id.restaurant_tab);
            spec.setIndicator("States");

            host.addTab(spec);
            host.setCurrentTab(0);



        });
    }

    private void setupRecycler() {
        fastAdapter = new FastAdapter<>();
        itemAdapter = new ItemAdapter<>();

        fastAdapter.addAdapter(0, itemAdapter);

        fastAdapter.setOnClickListener((view, itemIAdapter, stateItem, position) -> {
            StateWiseSheet stateWiseSheet = new StateWiseSheet();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.STRING_EXTRA, gson.toJson(stateItem.getStatewise()));
            stateWiseSheet.setArguments(bundle);
            stateWiseSheet.show(getChildFragmentManager(), StateWiseSheet.TAG);
            return false;
        });

        //dataObserver = new RecyclerDataObserver(recyclerView, emptyLayout, progressLayout);
        //fastAdapter.registerAdapterDataObserver(dataObserver);

        recycler.setAdapter(fastAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
    }



}



