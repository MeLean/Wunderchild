package com.mdimitrov.wunderchild.utils.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected static final String LANGUAGE_KEY = "language";

    public BaseFragment() {
        // Required empty public constructor
    }

    public void changeLanguage(String languageString){
        View view = getView();

        if(view != null){
           translateFields(view, languageString);
        }

        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE_KEY, languageString);
        setArguments(bundle);
    }

    protected abstract void translateFields(View view, String languageString);

    abstract public String getFragmentTag();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeLanguage(getArguments().getString(LANGUAGE_KEY));
    }
}
