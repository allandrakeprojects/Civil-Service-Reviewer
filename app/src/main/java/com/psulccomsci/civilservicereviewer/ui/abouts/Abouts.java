package com.psulccomsci.civilservicereviewer.ui.abouts;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psulccomsci.civilservicereviewer.R;

public class Abouts extends Fragment {

    TextView textView;

    private AboutsViewModel mViewModel;

    public static Abouts newInstance() {
        return new Abouts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.abouts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AboutsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.about_text);
        textView.setText(
                "\t\n" +
                "\tThis mobile Civil Service Reviewer app contains reviewer that are broken down into different categories for the reviewer to be able to easily grasp and \n" +
                "understand the topics and questions. It is intended and  specifially designed to help those who want to prepare or review for the Civil Service Examination in a fun and interactive way. The app also has educational game, the purpose of this is to entertain the reviewee and to avoid stress and pressure.\n" +
                "\n" +
                "Practice--Review--Learn--Enjoy and have fun!");


    }
}
