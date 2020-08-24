

package com.sp.corona.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.sp.corona.Constants;
import com.sp.corona.R;
import com.sp.corona.model.casetime.Statewise;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateWiseSheet extends BaseBottomSheet {

    public static final String TAG = "STATE_WISE_SHEET";
    @BindView(R.id.txt_all_total)
    TextView txtAllTotal;
    @BindView(R.id.txt_all_active)
    TextView txtAllActive;
    @BindView(R.id.txt_all_cured)
    TextView txtAllCured;
    @BindView(R.id.txt_all_deaths)
    TextView txtAllDeaths;
    @BindView(R.id.txt_title_state)
    AppCompatTextView txtTitleState;
    @BindView(R.id.txt_last_updated)
    AppCompatTextView txtLastUpdated;

    private Statewise statewise;

    public StateWiseSheet() {
    }

    @Nullable
    @Override
    public View onCreateContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_state_wise_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onContentViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            stringExtra = bundle.getString(Constants.STRING_EXTRA);
            statewise = gson.fromJson(stringExtra, Statewise.class);
            populateData();
        } else {
            dismissAllowingStateLoss();
        }
    }

    private void populateData() {
        txtTitleState.setText(statewise.getState());
        txtLastUpdated.setText(StringUtils.joinWith(" : ", "Last updated", statewise.getLastupdatedtime()));
        txtAllTotal.setText(statewise.getConfirmed());
        txtAllActive.setText(statewise.getActive());
        txtAllCured.setText(statewise.getRecovered());
        txtAllDeaths.setText(statewise.getDeaths());
    }
}
