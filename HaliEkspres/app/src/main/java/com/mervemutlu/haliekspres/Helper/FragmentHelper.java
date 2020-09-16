package com.mervemutlu.haliekspres.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.mervemutlu.haliekspres.R;

public class FragmentHelper {

    public static void ChangeFragment(Fragment fragment, FragmentManager fragmentManager, int hostFragmentId, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(!addToBackStack)
            fragmentManager.popBackStack();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(hostFragmentId, fragment);
        if(addToBackStack)
            fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();

    }

    public static void RemoveFragment(Fragment fragment, FragmentManager fragmentManager){
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().remove(fragment).commit();
    }

    public static ProgressDialog progressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("İşlem gerçekleştiriliyor. Lütfen bekleyiniz..");
        progressDialog.show();

        return progressDialog;
    }

    public static void AlertShow(Context context, String mesaj)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mesaj);
        builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
