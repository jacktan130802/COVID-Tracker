
//need to find which one is the setter.
package com.sp.corona.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sp.corona.Constants;
import com.sp.corona.model.casetime.CaseReport;
import com.sp.corona.task.NetworkTask;
import com.sp.corona.util.Log;
import com.sp.corona.util.PrefUtil;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CaseReportModel extends AndroidViewModel {

    private Gson gson = new Gson();
    private MutableLiveData<Boolean> data = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public CaseReportModel(@NonNull Application application) {
        super(application);
        fetchOnlineData();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getData() {
        return data;
    }

    public void fetchOnlineData() {
        disposable.add(Observable.fromCallable(() -> new NetworkTask()
                .get("https://api.covid19india.org/data.json"))
                .subscribeOn(Schedulers.io())
                .map(rawJSON -> gson.fromJson(rawJSON, CaseReport.class))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveDataToPreferences, throwable -> Log.e(throwable.getMessage())));
    }

    private void saveDataToPreferences(CaseReport caseReport) {
        //stored somewhere here

        try {
            PrefUtil.putString(getApplication(), Constants.PREFERENCE_CASE_TIME_SERIES, gson.toJson(caseReport.getCases_time_series()));
            PrefUtil.putString(getApplication(), Constants.PREFERENCE_STATE_WISE, gson.toJson(caseReport.getData()));
            PrefUtil.putString(getApplication(), Constants.PREFERENCE_OVERALL_DATA, gson.toJson(caseReport.getStatewise().get(0)));
            PrefUtil.putString(getApplication(), Constants.PREFERENCE_TESTED, gson.toJson(caseReport.getTested()));
//            is like the put extra thingy.-->1 is value and one is to info to store
            PrefUtil.putString(getApplication(), Constants.PREFERENCE_KEY_VALUES, gson.toJson(caseReport.getStatewise().get(0)));

            data.setValue(true);
        } catch (Exception e) {
            data.setValue(false);
            error.setValue(e.getMessage());
        }
    }
}
