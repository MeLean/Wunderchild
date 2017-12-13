package com.mdimitrov.wunderchild;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mdimitrov.wunderchild.utils.SharedPreferenceUtils;

public class StartActivity extends AppCompatActivity {
    private static final String LANGUAGE_KEY = "languages_key";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mFragmentManager = getFragmentManager();
        ActionBar actionBar = getActionBar();
        if(actionBar != null) actionBar.hide();

        Spinner spinner = findViewById(R.id.language_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("AppDebugg", "Language selected: " + parent.getSelectedItem());
                SharedPreferenceUtils.saveString(view.getContext(), LANGUAGE_KEY, parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        //loadFragment(mFragmentManager,  Fragment.class);
    }
}
