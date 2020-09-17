package com.mervemutlu.haliekspres;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mervemutlu.haliekspres.api.VolleyManager;
import com.mervemutlu.haliekspres.helper.FragmentHelper;
import com.mervemutlu.haliekspres.helper.Utils;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mervemutlu.haliekspres.helper.GlobalVars.userName;
import static com.mervemutlu.haliekspres.helper.GlobalVars.token;

public class LoginActivity  extends AppCompatActivity implements VolleyManager.AsyncResponse {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login(){
        try {
            JSONObject jsonobj = new JSONObject();

            jsonobj.put("usr", ((EditText)findViewById(R.id.edt_username)).getText());
            jsonobj.put("pwd", ((EditText)findViewById(R.id.edt_password)).getText());

            progressDialog = FragmentHelper.progressDialog(LoginActivity.this);

            //[log={"usr":username,"pwd":password}]

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(Utils.getNameValuePair("log", jsonobj));

            new VolleyManager(nameValuePairs, LoginActivity.this).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        //return '{"giris":"yes","log":"usr:' . $token['usr'] . ',pwd:' . $token['pwd'] . ',log:' . $TOKEN . '"}';
        //return {"giris":"no"}
        try {
            progressDialog.dismiss();
            JSONObject jsonObject=new JSONObject(output);
            if (jsonObject.getString("giris").equals("yes")) {
                JSONObject logJsonObject = jsonObject.getJSONObject("log");
                userName = logJsonObject.getString("usr");
                token = logJsonObject.getString("token");

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else
                FragmentHelper.AlertShow(LoginActivity.this,getString(R.string.login_error));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

