package com.mdimitrov.wunderchild.utils.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.utils.ui.activities.StartActivity;
import com.mdimitrov.wunderchild.utils.ui.utils.LanguageManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends BaseFragment implements View.OnClickListener{
    public static final String TAG = "com.mdimitrov.wunderchild.utils.ui.fragments.join_fragment";

    public JoinFragment() {
        // Required empty public constructor
    }

    public static JoinFragment newInstance(String language) {
        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE_KEY, language);

        JoinFragment fragment = new JoinFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join, container, false);
        view.findViewById(R.id.join_login_btn).setOnClickListener(this);
        view.findViewById(R.id.join_sing_up_btn).setOnClickListener(this);
        return view;
    }


    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    protected void translateFields(View view, String languageString) {
        Context context = view.getContext();
        ((TextView) view.findViewById(R.id.join_text))
                .setText(LanguageManager.Join.getText(context, languageString));
        ((Button) view.findViewById(R.id.join_login_btn))
                .setText(LanguageManager.Join.getLoginText(context, languageString));
        ((Button) view.findViewById(R.id.join_sing_up_btn))
                .setText(LanguageManager.Join.getSingUpText(context, languageString));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        StartActivity activity = (StartActivity) getActivity();
        //todo
        if(id == R.id.join_login_btn){
            Toast.makeText(activity, "Kliknaha join_login_btn", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.join_sing_up_btn){
            Toast.makeText(activity, "Kliknaha join_sing_up_btn", Toast.LENGTH_SHORT).show();
        }
    }
}
