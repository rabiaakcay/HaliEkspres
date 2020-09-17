package com.mervemutlu.haliekspres.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.mervemutlu.haliekspres.helper.FragmentHelper;
import com.mervemutlu.haliekspres.R;
import com.mervemutlu.haliekspres.ui.rated_process.RatedProcessFragment;

public class HomeFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.rated_process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeFragment(new RatedProcessFragment());
            }
        });

        return  view;
    }

    private void ChangeFragment(Fragment fragment){
        FragmentHelper.ChangeFragment(fragment, getFragmentManager(), R.id.bottom_nav_host_fragment, true);
    }
}