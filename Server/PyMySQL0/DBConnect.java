package com.example.dbtestapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnect {
    GetMysql task;
    String urlAD = "http://10.0.2.2/DBconnect/testdb.php";
    String dataString = "come in and gone";

    protected String getdata(){
        DBconn();
        return dataString;
    }

    private void DBconn(){
        task = new GetMysql();
        task.execute(urlAD);
    }

    protected class GetMysql extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.d("a","as");
        }

        @Override
        protected String doInBackground(String... args){
            StringBuilder jsonhtml = new StringBuilder();
            try{
                URL url = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(5000);
                    conn.setUseCaches(false);
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                        for(;;){
                            String line = br.readLine();
                            if(line == null){
                                break;
                            }
                            jsonhtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            }catch (Exception e){
                return new String("Exception: " + e.getMessage());
            }
            return jsonhtml.toString();
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            dataString = result;
        }

    }



}
