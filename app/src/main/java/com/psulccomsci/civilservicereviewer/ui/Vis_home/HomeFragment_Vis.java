package com.psulccomsci.civilservicereviewer.ui.Vis_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.psulccomsci.civilservicereviewer.R;

public class HomeFragment_Vis extends Fragment {

    private HomeViewModel_Vis homeViewModelVis;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModelVis =
                ViewModelProviders.of(this).get(HomeViewModel_Vis.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        return root;
    }


}