package com.mdimitrov.wunderchild.utils.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.utils.ui.activities.StartActivity;
import com.mdimitrov.wunderchild.utils.ui.utils.LanguageManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends BaseFragment {
    public static final String TAG = "com.mdimitrov.wunderchild.utils.ui.fragments.intro_fragment";

    public IntroFragment() {
        // Required empty public constructor
    }

    public static IntroFragment newInstance(String language) {
        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE_KEY, language);

        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        view.findViewById(R.id.intro_test_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity activity = (StartActivity) getActivity();
                activity.findViewById(R.id.activity_wrapper)
                        .setBackgroundColor(ContextCompat.getColor(activity, R.color.activity_blue));

                FragmentManager manager = activity.getSupportFragmentManager();
                JoinFragment nextFragment = (JoinFragment) manager.findFragmentByTag(JoinFragment.TAG);

                if (nextFragment == null){
                    nextFragment =  JoinFragment.newInstance(getArguments().getString(BaseFragment.LANGUAGE_KEY));
                }

                activity.loadFragment(manager, nextFragment);
            }
        });
        return view;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    protected void translateFields(View view, String languageString) {
        Context context = view.getContext();
        ((ImageView) view.findViewById(R.id.intro_text_picture))
                .setImageResource(LanguageManager.Intro.getTextPicture(context, languageString));
        ((TextView) view.findViewById(R.id.intro_text))
                .setText(LanguageManager.Intro.getText(context, languageString));
        ((Button) view.findViewById(R.id.intro_test_button))
                .setText(LanguageManager.Intro.getButtonText(context, languageString));
    }

}
