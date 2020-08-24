
package com.sp.corona.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sp.corona.Constants;
import com.sp.corona.model.casetime.Cases_time_series;
import com.sp.corona.util.PrefUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DailyReportModel extends AndroidViewModel implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Gson gson = new Gson();
    private MutableLiveData<List<Cases_time_series>> data = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public DailyReportModel(@NonNull Application application) {
        super(application);
        fetchDataFromPreferences();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public LiveData<List<Cases_time_series>> getData() {
        return data;
    }

    public void fetchDataFromPreferences() {
        final String rawCaseTimeSeries = PrefUtil.getString(getApplication(), Constants.PREFERENCE_CASE_TIME_SERIES);
        final Type type = new TypeToken<List<Cases_time_series>>() {
        }.getType();
        if (!rawCaseTimeSeries.isEmpty()) {
            final List<Cases_time_series> casesTimeSeriesList = gson.fromJson(rawCaseTimeSeries, type);
            if (!casesTimeSeriesList.isEmpty())
                data.setValue(casesTimeSeriesList);
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Constants.PREFERENCE_CASE_TIME_SERIES)) {
            fetchDataFromPreferences();
        }
    }
}
