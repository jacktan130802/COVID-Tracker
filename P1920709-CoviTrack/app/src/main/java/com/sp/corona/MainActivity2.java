package com.sp.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pdialog;
    private ListView lv;
    private static String url ="https://api.covid19india.org/data.json";
    ArrayList<HashMap<String,String>> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        contactlist = new ArrayList<>();
        lv=findViewById(R.id.list);
        new GetContacts().execute();
    }
    private class GetContacts extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pdialog = new ProgressDialog(MainActivity2.this);
            pdialog.setMessage("Please wait...");
            pdialog.setCancelable(false);
            pdialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler2 sh = new HttpHandler2();
            String jsonSTR = sh.makeServiceCall(url);
            Log.e(TAG,"Response from url :" +jsonSTR);
            if(jsonSTR!= null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonSTR);
                    JSONArray contacts = jsonObject.getJSONArray("cases_time_series");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject employee = contacts.getJSONObject(i);
                        String dailyconfirmed = employee.getString("dailyconfirmed");
                        String dailydeceased = employee.getString("dailydeceased");
                        String date = employee.getString("date");
                        HashMap<String, String> contact = new HashMap<>();
                        contact.put("dailyconfirmed", dailyconfirmed);
                        contact.put("dailydeceased", dailydeceased);
                        contact.put("date", date);
                        contactlist.add(contact);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pdialog.isShowing())
                pdialog.dismiss();
            ListAdapter adapter = new SimpleAdapter( MainActivity2.this , contactlist , R.layout.list_item,new String[]{"dailyconfirmed","dailydeceased","date"},
                    new int[]{R.id.email,R.id.name,R.id.mobile});
            lv.setAdapter(adapter);

        }
    }

}
