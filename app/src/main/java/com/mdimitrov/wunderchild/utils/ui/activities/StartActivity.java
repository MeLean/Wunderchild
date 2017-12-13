package com.mdimitrov.wunderchild.utils.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.utils.ui.fragments.IntroFragment;
import com.mdimitrov.wunderchild.utils.ui.fragments.BaseFragment;
import com.mdimitrov.wunderchild.utils.ui.utils.SharedPreferenceUtils;

public class StartActivity extends AppCompatActivity {
    private static final String SAVED_LANGUAGE_KEY = "saver_language";
    private BaseFragment mCurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) actionbar.hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mCurFragment = (BaseFragment) fragmentManager.findFragmentByTag(IntroFragment.TAG);
        if (mCurFragment == null){
            //If there is no save string EN is a start language
            mCurFragment = IntroFragment
                    .newInstance(SharedPreferenceUtils.loadString(this, SAVED_LANGUAGE_KEY, getString(R.string.en)));
        }

        loadFragment(fragmentManager, mCurFragment);

        Spinner spinner = findViewById(R.id.language_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //todo manage selected language
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("AppDebug", "Language selected: " + parent.getSelectedItem());
                String language = parent.getSelectedItem().toString();
                mCurFragment.changeLanguage(language);
                SharedPreferenceUtils.saveString(StartActivity.this, SAVED_LANGUAGE_KEY, language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }


    public void loadFragment(FragmentManager fragmentManager, BaseFragment fragment) {
        mCurFragment = fragment;
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_wrapper, fragment, fragment.getFragmentTag())
                .commit();
    }

}
