

package com.sp.corona.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sp.corona.Constants;
import com.sp.corona.model.casetime.Statewise;
import com.sp.corona.model.item.StateItem;
import com.sp.corona.util.PrefUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;

public class StateWiseReportModel extends AndroidViewModel implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Gson gson = new Gson();
    private MutableLiveData<List<StateItem>> data = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public StateWiseReportModel(@NonNull Application application) {
        super(application);
        fetchDataFromPreferences();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public LiveData<List<StateItem>> getData() {
        return data;
    }

    public void fetchDataFromPreferences() {
        final String rawStateWiseDate = PrefUtil.getString(getApplication(), Constants.PREFERENCE_STATE_WISE);
        final Type type = new TypeToken<List<Statewise>>() {
        }.getType();

        if (!rawStateWiseDate.isEmpty()) {
            final List<Statewise> statewiseList = gson.fromJson(rawStateWiseDate, type);
            if (!statewiseList.isEmpty()) {
                Observable.fromIterable(statewiseList)
                        .map(StateItem::new)
                        .toList()
                        .doOnSuccess(countryItems -> data.setValue(countryItems))
                        .doOnError(throwable -> error.setValue(throwable.getMessage()))
                        .subscribe();
            }
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Constants.PREFERENCE_STATE_WISE)) {
            fetchDataFromPreferences();
        }
    }
}
