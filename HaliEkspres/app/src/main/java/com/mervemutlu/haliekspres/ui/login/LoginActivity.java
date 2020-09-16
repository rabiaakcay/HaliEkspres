package com.mervemutlu.haliekspres.ui.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mervemutlu.haliekspres.API.VolleyManager;
import com.mervemutlu.haliekspres.Helper.FragmentHelper;
import com.mervemutlu.haliekspres.MainActivity;
import com.mervemutlu.haliekspres.Models.User;
import com.mervemutlu.haliekspres.R;
import com.mervemutlu.haliekspres.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity  extends AppCompatActivity implements VolleyManager.AsyncResponse {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_login);
//        ActivityLoginBinding activityMainBinding =
//                DataBindingUtil.setContentView(
//                        this, R.layout.activity_login);

//        User user = new User("Merve", "Mutlu");
//
//        activityMainBinding.setUser(user);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User loginUser) {
                Login(loginUser);
            }
        });


//        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Login();
//            }
//        });
    }

    private void Login(User user){
        try {
            JSONObject jsonobj = new JSONObject();

            jsonobj.put("usr", user.getUserName());
            jsonobj.put("pwd", user.getPassword());

            progressDialog = FragmentHelper.progressDialog(LoginActivity.this);
            //[log={"usr":username,"pwd":password}]
            new VolleyManager("log", jsonobj, LoginActivity.this).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        //return '{"giris":"yes","log":"usr:' . $token['usr'] . ',pwd:' . $token['pwd'] . ',log:' . $TOKEN . '"}';
        try {
            progressDialog.dismiss();
            JSONObject jsonObject=new JSONObject(output);
            if (jsonObject.getBoolean("login")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else
                FragmentHelper.AlertShow(LoginActivity.this,"Giriş bilgileri hatalı.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

