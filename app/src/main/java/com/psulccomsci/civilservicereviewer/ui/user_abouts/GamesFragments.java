package com.psulccomsci.civilservicereviewer.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.ui.abouts.AboutsViewModel;

public class GamesFragments extends Fragment{

        private AboutsViewModel mViewModel;

        public static com.psulccomsci.civilservicereviewer.ui.abouts.Abouts newInstance() {
            return new com.psulccomsci.civilservicereviewer.ui.abouts.Abouts();
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

}


