package com.mervemutlu.haliekspres.ui.rated_process;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mervemutlu.haliekspres.models.RatedProcess;
import com.mervemutlu.haliekspres.databinding.ItemRatedProcessBinding;

import java.util.List;

public class RatedProcessAdapter extends ArrayAdapter<RatedProcess> {
    List<RatedProcess> list;
    Context context;
    int resource;

    public RatedProcessAdapter(Context context, int resource, List<RatedProcess> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemRatedProcessBinding binding;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, null, false);
        binding = DataBindingUtil.bind(convertView);
        convertView.setTag(binding);
        binding.setModel(list.get(position));
        return binding.getRoot();

    }
}