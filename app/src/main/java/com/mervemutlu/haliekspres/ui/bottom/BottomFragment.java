package com.mervemutlu.haliekspres.ui.bottom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mervemutlu.haliekspres.helper.FragmentHelper;
import com.mervemutlu.haliekspres.R;
import com.mervemutlu.haliekspres.ui.home.HomeFragment;
import com.mervemutlu.haliekspres.ui.rated_process.RatedProcessFragment;

public class BottomFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_main, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BottomNavigationView navigation = view.findViewById(R.id.bottom_nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ChangeFragment(new HomeFragment());
    }

    private void ChangeFragment(Fragment fragment){
        FragmentHelper.ChangeFragment(fragment, getFragmentManager(), R.id.bottom_nav_host_fragment, false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bottom_home:
                    ChangeFragment(new HomeFragment());
                    return true;
                case R.id.navigation_back:
                    onBackPressed();
                    return true;
                default:
                    ChangeFragment(new HomeFragment());
                    return true;
            }
        }
    };

    private void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
}
