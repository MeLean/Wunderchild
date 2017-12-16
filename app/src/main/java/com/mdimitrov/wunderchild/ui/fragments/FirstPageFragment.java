package com.mdimitrov.wunderchild.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.ui.activities.StartingActivity;
import com.mdimitrov.wunderchild.utils.LanguageManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends BaseFragment {

    public FirstPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        view.findViewById(R.id.first_page_test_button).setOnClickListener(v -> {
            StartingActivity activity = (StartingActivity) getActivity();
            //should change color of activity because of header in activity
            activity.loadNextPage();
        });
        return view;
    }


    @Override
    protected void translateFields(View view, String languageString) {
        //Log.d("AppDebug", "translateFields FirstFragment called");
        Context context = view.getContext();
        ((TextView) view.findViewById(R.id.first_page_text_title))
                .setText(LanguageManager.Intro.getTextTitle(context, languageString));
        ((TextView) view.findViewById(R.id.first_page_text))
                .setText(LanguageManager.Intro.getText(context, languageString));
        ((Button) view.findViewById(R.id.first_page_test_button))
                .setText(LanguageManager.Intro.getButtonText(context, languageString));
    }

}
