package com.mervemutlu.haliekspres.helper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    //Volley post
    public static NameValuePair getLogNameValuePair() {
        JSONObject jsonobj = new JSONObject();
        try {
            jsonobj.put("usr", GlobalVars.userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getNameValuePair("log", jsonobj.toString());
    }

    public static NameValuePair getTokenNameValuePair() {
        return getNameValuePair("token", GlobalVars.token);
    }

    public static NameValuePair getNameValuePair(String header, Object object) {
        return new BasicNameValuePair(header, object.toString());
    }
}
