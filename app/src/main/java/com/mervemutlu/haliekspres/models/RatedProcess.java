package com.mervemutlu.haliekspres.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.mervemutlu.haliekspres.R;
import com.mervemutlu.haliekspres.helper.FragmentHelper;
import com.mervemutlu.haliekspres.ui.rated_process.RatedProcessDetailsFragment;

import java.util.List;

import static com.mervemutlu.haliekspres.helper.GlobalVars.fragmentManager;

public class RatedProcess extends BaseObservable {
    public int KALEM_SAYI;
    public String FIRMA;
    public int SIPARIS_ID;
    public int FIRMA_ID;
    public int SIPARIS_NO;
    public int MUSTERI_ID;
    public String SIPARIS_DURUM;
    public String SIPARIS_ALISTARIHI;
    public String SIPARIS_TESLIMATTARIHI;
    public String SIPARIS_IL;
    public String SIPARIS_ILCE;
    public String SIPARIS_MAH;
    public String SIPARIS_ADRES;
    public String SIPARIS_SITE;
    public String SIPARIS_SOKAK;
    public String SIPARIS_APARTMAN;
    public String SIPARIS_KAPI;
    public String SIPARIS_BLOK;
    public String SIPARIS_KAT;
    public String SIPARIS_DAIRE;
    public double SIPARIS_TOPLAM;
    public double SIPARIS_INDIRIM_TL;
    public double SIPARIS_GENELTOPLAM;
    public String SIPARIS_CDATE;
    public String SIPARIS_UDATE;
    public String SIPARIS_ODEME_DURUM;
    public int PERSONEL_ID;
    public int TERMINAL_ID;
    public String SIPARIS_MESAJ;
    public double SIPARIS_INDIRIM_YUZDE;
    public String SIPARIS_IADE;
    public String YERINDE_YIKAMA;
    public int SIPARIS_FISDURUM;
    public int SIPARIS_PARFUM;
    public String SON_ISLEMDATE;
    public int SIRA_NO;
    public String MUSTERI_AD;
    public int MUSTERI_NO;
    public String MUSTERI_SOYAD;

    public String getCustomerName(){
        return MUSTERI_AD + " " + MUSTERI_SOYAD;
    }

    public String getAddress(){
        return (SIPARIS_MAH == null || SIPARIS_MAH.isEmpty() ? "" : (SIPARIS_MAH + " mah.")) +
               (SIPARIS_SOKAK == null || SIPARIS_SOKAK.isEmpty() ? "" : (SIPARIS_SOKAK + " sk.")) +
               (SIPARIS_APARTMAN == null || SIPARIS_APARTMAN.isEmpty() ? "" : (SIPARIS_APARTMAN + " apt.")) +
               (SIPARIS_BLOK == null || SIPARIS_BLOK.isEmpty() ? "" : (SIPARIS_BLOK + " blok")) +
               (SIPARIS_KAT == null || SIPARIS_KAT.isEmpty() ? "" : ("Kat: " + SIPARIS_KAT)) +
               (SIPARIS_DAIRE == null || SIPARIS_DAIRE.isEmpty() ? "" : ("D: " + SIPARIS_DAIRE)) +
                SIPARIS_ADRES;
    }

    public void onDetailClick(View view){
        FragmentHelper.ChangeFragment(new RatedProcessDetailsFragment(this), fragmentManager, R.id.bottom_nav_host_fragment, true);
    }


    //-------Details-------//
    public List<RatedProcessOrderDetail> orderDetailList;

    @Bindable
    public double odeme;

    public void setOdeme(double odeme) {
        this.odeme = odeme;
        notifyPropertyChanged(BR.odeme);
    }

    @Bindable
    public double indirimsonrasi;

    public void setIndirimsonrasi(double indirimsonrasi) {
        this.indirimsonrasi = indirimsonrasi;
        notifyPropertyChanged(BR.indirimsonrasi);
    }

}
