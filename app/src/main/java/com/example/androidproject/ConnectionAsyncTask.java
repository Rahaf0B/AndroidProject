package com.example.androidproject;


import android.app.Activity;
import android.os.AsyncTask;
import java.util.List;
public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    Activity activity;

    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity; }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override protected String doInBackground(String... params) {
        String data = HttpManager.getData(params[0]);

        return data; }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<Distination> distinations = DistinationJsonParser.getObjectFromJson(s);



        if (activity.toString().split("\\.")[3].split("\\@")[0].equals("MainPageOfTheApp")) {

            ((MainPageOfTheApp) activity).getTheData(distinations);
        }
    } }