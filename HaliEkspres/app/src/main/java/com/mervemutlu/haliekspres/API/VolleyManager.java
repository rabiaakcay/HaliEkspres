package com.mervemutlu.haliekspres.API;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VolleyManager extends AsyncTask<Void, Void, String>{
    private String BASE_URL = "https://haliekspres.com/api/mobil/service.php";
    public AsyncResponse delegate = null;
    String header;
    String responseServer;
    JSONObject jsonobj;

    public VolleyManager(String header, JSONObject jsonObject,AsyncResponse delegate){
        this.header = header;
        this.jsonobj=jsonObject;
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(BASE_URL);

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair(header, jsonobj.toString()));

            Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

            // Use UrlEncodedFormEntity to send in proper format which we need
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            InputStream inputStream = response.getEntity().getContent();
            VolleyManager.InputStreamToString str = new VolleyManager.InputStreamToString();
            responseServer = str.getStringFromInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseServer;
    }


    public static class InputStreamToString {
        // convert InputStream to String
        private static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}