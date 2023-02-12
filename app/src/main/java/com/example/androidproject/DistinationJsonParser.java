package com.example.androidproject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class DistinationJsonParser {

    public static  List<Distination> distinationsdata;
    public static List<Distination> getObjectFromJson(String json) {
        List<Distination> distinations;


        try {
            JSONArray jsonArray = new JSONArray(json);
            distinations = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Distination distination = new Distination();
                distination.setCity(jsonObject.getString("city"));
                distination.setCountry(jsonObject.getString("country"));
                distination.setContinent(jsonObject.getString("continent"));
                distination.setLongitude((float) jsonObject.getDouble("longitude"));
                distination.setLatitude((float) jsonObject.getDouble("latitude"));
                distination.setCost(jsonObject.getInt("cost"));
                distination.setImg(jsonObject.getString("img"));
                distination.setDescription(jsonObject.getString("description"));
                distinations.add(distination);
                distinationsdata=distinations;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null; }

        return distinations; }
}
