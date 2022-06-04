package com.example.onlyfacts.dbconnthread;


import com.example.onlyfacts.NewsDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

public class DBJsonToString {

    String data;
    String txt = "";
    String nId = "", nTitle ="", nField="", nImglink="", nBody="", nTime="", nReliability="", nSourcelink="";
    List<NewsDataSet> datalist;

    public DBJsonToString(String data) throws JSONException {
        this.data = data;
        Gson gson = new Gson();

        datalist = gson.fromJson(data, new TypeToken<List<NewsDataSet>>(){}.getType());

        /*
        JSONArray array = new JSONArray(data);

        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            txt += obj.getString("id") + " ";
            if(i == 0){
                continue;
            }
            if(i%5 == 0){
                txt += "\n";
            }
        }
         */

        for (int i = 0; i < datalist.size(); i++){
            NewsDataSet obj = datalist.get(i);
            txt += obj.getId();

            txt += "\n";
        }
    }

    public String getTxt() {
        return txt;
    }

    public List<NewsDataSet> getDatalist() {
        return datalist;
    }
}
