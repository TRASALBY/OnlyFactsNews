package com.example.onlyfacts.dbconnthread;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DBConnect extends Thread{

    String urlAD = "http://220.77.42.226:2475/testdb.php";
    public String returnMessage;
    Handler connHandler;

    public DBConnect(Handler handler){
        connHandler = handler;
    }

    public void run(){
        StringBuilder jsonhtml = new StringBuilder();
        try{
            URL url = new URL(urlAD);
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
            returnMessage = "Exception: " + e.getMessage();
        }
        returnMessage = jsonhtml.toString();

        Message message = connHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("data", returnMessage);
        message.setData(bundle);

        connHandler.sendMessage(message);
    }

}
