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
import android.widget.Toast;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.utils.LanguageManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondPageFragment extends BaseFragment implements View.OnClickListener{

    public SecondPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_page, container, false);
        view.findViewById(R.id.second_page_login_btn).setOnClickListener(this);
        view.findViewById(R.id.second_page_sing_up_btn).setOnClickListener(this);
        return view;
    }

    @Override
    protected void translateFields(View view, String languageString) {
        //Log.d("AppDebug", "translateFields SecondFragment called");
        Context context = view.getContext();
        ((TextView) view.findViewById(R.id.second_page_text))
                .setText(LanguageManager.Join.getText(context, languageString));
        ((Button) view.findViewById(R.id.second_page_login_btn))
                .setText(LanguageManager.Join.getLoginText(context, languageString));
        ((Button) view.findViewById(R.id.second_page_sing_up_btn))
                .setText(LanguageManager.Join.getSingUpText(context, languageString));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //todo animate login show reveal center
        if(id == R.id.second_page_login_btn){
            Toast.makeText(getActivity(), "Kliknaha join_login_btn", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.second_page_sing_up_btn){
            Toast.makeText(getActivity(), "Kliknaha join_sing_up_btn", Toast.LENGTH_SHORT).show();
        }
    }
}
