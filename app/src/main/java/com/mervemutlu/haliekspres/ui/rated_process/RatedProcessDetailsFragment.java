package com.mervemutlu.haliekspres.ui.rated_process;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.mervemutlu.haliekspres.R;
import com.mervemutlu.haliekspres.api.VolleyManager;
import com.mervemutlu.haliekspres.databinding.FragmentRatedProcessDetailsBinding;
import com.mervemutlu.haliekspres.helper.JsonManager;
import com.mervemutlu.haliekspres.helper.TextManager;
import com.mervemutlu.haliekspres.helper.Utils;
import com.mervemutlu.haliekspres.models.RatedProcess;
import com.mervemutlu.haliekspres.models.RatedProcessOrderDetail;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class RatedProcessDetailsFragment extends Fragment implements VolleyManager.AsyncResponse{
    View view;
    RatedProcess ratedProcess;

    ListView lv;
    TextView tv_piece,tv_m2,tv_amount;

    public RatedProcessDetailsFragment(RatedProcess ratedProcess) {
        this.ratedProcess = ratedProcess;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentRatedProcessDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rated_process_details, container, false);
        binding.setLifecycleOwner(this);
        binding.setModel(ratedProcess);
        view = binding.getRoot();

        init();
        load();
        return view;
    }

    private void init() {
        lv = view.findViewById(R.id.lv);
        tv_piece = view.findViewById(R.id.tv_piece);
        tv_m2 = view.findViewById(R.id.tv_m2);
        tv_amount = view.findViewById(R.id.tv_amount);

        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.lv_header_rated_process_order_detail, lv,false);
        lv.addHeaderView(headerView);
    }

    private void load() {
        //$post[‘islem’]= "musteriDetaysiparis";
        //$post[‘veri’]=siparişid;
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(Utils.getLogNameValuePair());
        nameValuePairs.add(Utils.getTokenNameValuePair());
        nameValuePairs.add(Utils.getNameValuePair("islem", "siparisKalemListele"));
        JSONObject veriObject = new JSONObject();
        try {
            veriObject.put("siparisid", ratedProcess.SIPARIS_ID);
            veriObject.put("musteriid", ratedProcess.MUSTERI_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nameValuePairs.add(Utils.getNameValuePair("veri", veriObject));

        new VolleyManager(nameValuePairs, RatedProcessDetailsFragment.this).execute();
    }

    @SuppressLint("NewApi")
    @Override
    public void processFinish(String output) {
        //SIPARISKALEM_ID,SIPARISKALEM_BASLIK,SIPARISKALEM_FIYAT,SIPARISKALEM_M2,SIPARISKALEM_ADET,SIPARISKALEM_TOPLAM
        try {
            JSONObject outputJson = new JSONObject(output);

            //odeme-indirimsonrasi
            ratedProcess.setOdeme(outputJson.getDouble("odeme"));
            ratedProcess.setIndirimsonrasi(outputJson.getDouble("indirimsonrasi"));

            //order detail lv
            JsonManager<RatedProcessOrderDetail> jsonManager = new JsonManager<>();
            List<RatedProcessOrderDetail> orderDetailList = jsonManager.JsonArrayToObjectList(outputJson.getString("sipariskalem"),  new TypeToken<List<RatedProcessOrderDetail>>(){}.getType());
            RatedProcessOrderDetailAdapter adapter = new RatedProcessOrderDetailAdapter(view.getContext(), R.layout.item_rated_process_order_detail, orderDetailList);
            lv.setAdapter(adapter);

            //total
            double total_m2 = 0;
            double total_amount = 0;
            for (RatedProcessOrderDetail detail : orderDetailList){
                total_m2 += detail.SIPARISKALEM_M2;
                total_amount += detail.SIPARISKALEM_TOPLAM;
            }

            tv_piece.setText(orderDetailList.size() + " Adt.");
            tv_m2.setText(total_m2 + " ");
            tv_amount.setText(total_amount + " TL");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
