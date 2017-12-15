package com.mdimitrov.wunderchild.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.ui.activities.StartingActivity;
import com.mdimitrov.wunderchild.utils.SharedPreferencesUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    public void changeLanguage(String languageString){
        View view = getView();

        if(view != null){
           translateFields(view, languageString);
        }
    }

    protected abstract void translateFields(View view, String languageString);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //if no string is stored english will be default
        changeLanguage(SharedPreferencesUtils.loadString(getContext(), StartingActivity.SAVED_LANGUAGE_KEY, getString(R.string.en)));
    }
}
