package com.restaurand.erisco.restaurand.helper;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {

    public static JSONObject JsonFromUrl(String urlString){

        JSONObject jsonRoot = new JSONObject();

        try{
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();

            byte data[] = new byte[1024];
            int downloadBytes;
            InputStream input = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            while((downloadBytes = input.read(data))!= -1){
                sb.append(new String(data,0,downloadBytes));
            }

            jsonRoot = new JSONObject(sb.toString());

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return jsonRoot;

    }


}
