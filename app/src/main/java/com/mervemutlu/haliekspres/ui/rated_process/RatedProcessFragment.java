package com.mervemutlu.haliekspres.ui.rated_process;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mervemutlu.haliekspres.api.VolleyManager;
import com.mervemutlu.haliekspres.helper.JsonManager;
import com.mervemutlu.haliekspres.helper.Utils;
import com.mervemutlu.haliekspres.models.RatedProcess;
import com.mervemutlu.haliekspres.R;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mervemutlu.haliekspres.helper.GlobalVars.fragmentManager;

public class RatedProcessFragment extends Fragment implements VolleyManager.AsyncResponse {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_rated_process, container, false);
        fragmentManager = getFragmentManager();
        load();
        return view;
    }

    private void load() {
        //{"log":{"usr":"..."},"islem":"islemdekiler", "token":"..."}
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(Utils.getLogNameValuePair());
        nameValuePairs.add(Utils.getTokenNameValuePair());
        nameValuePairs.add(Utils.getNameValuePair("islem", "islemdekiler"));

        new VolleyManager(nameValuePairs, RatedProcessFragment.this).execute();
    }

    @Override
    public void processFinish(String output) {
        //(SELECT FIRMA_ADI FROM FIRMA WHERE FIRMA.FIRMA_ID=MUSTERI.FIRMA_ID)FIRMA,
        // SIPARIS.*, MUSTERI.MUSTERI_AD, MUSTERI.MUSTERI_NO, MUSTERI.MUSTERI_SOYAD
        try {
            JSONObject outputJson = new JSONObject(output);

            List<RatedProcess> list = new ArrayList<>();
            JsonManager<RatedProcess> jsonManager = new JsonManager<>();
            JSONObject jsonObject = null;
            RatedProcess model;
            int i = 0;
            while(outputJson.has(String.valueOf(i)) && !outputJson.isNull(String.valueOf(i))) {
                jsonObject = outputJson.getJSONObject(String.valueOf(i++));
                model = jsonManager.JsonToObject(jsonObject.toString(), RatedProcess.class);
                list.add(model);
            }

            ((TextView)view.findViewById(R.id.tv_count)).setText(String.valueOf(i));

            RatedProcessAdapter adapter = new RatedProcessAdapter(view.getContext(), R.layout.item_rated_process, list);
            ((ListView)view.findViewById(R.id.lv)).setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
