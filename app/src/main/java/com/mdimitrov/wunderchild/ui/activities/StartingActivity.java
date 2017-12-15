package com.mdimitrov.wunderchild.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.ui.adapters.viewpager.MainViewPagerAdapter;
import com.mdimitrov.wunderchild.ui.fragments.BaseFragment;
import com.mdimitrov.wunderchild.utils.SharedPreferencesUtils;

public class StartingActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String SAVED_LANGUAGE_KEY = "saved_language";

    private ViewPager mStartViewPager;
    private boolean notFirstTimeSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        setupLanguageSpinner();

        setupPagerView();

    }


    private void setupLanguageSpinner() {
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
                if (notFirstTimeSelected) {
                    Log.d("AppDebug", "Language selected: " + parent.getSelectedItem());
                    String language = parent.getSelectedItem().toString();
                    getCurrentPageFragment().changeLanguage(language);
                    SharedPreferencesUtils.saveString(StartingActivity.this, SAVED_LANGUAGE_KEY, language);
                    notFirstTimeSelected = true;
                } else {
                    //if there us already saved language it should be selected
                    String savedLanguage = SharedPreferencesUtils.loadString(StartingActivity.this, SAVED_LANGUAGE_KEY, null);
                    if (savedLanguage != null) {
                        if (savedLanguage.equals(getString(R.string.en))) {
                            parent.setSelection(0);
                        } else if (savedLanguage.equals(getString(R.string.bg))) {
                            parent.setSelection(1);
                        } else {
                            throw new UnsupportedOperationException(savedLanguage + " language is not supported");
                        }

                        getCurrentPageFragment().changeLanguage(savedLanguage);
                        notFirstTimeSelected = true;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }


    public void loadNextPage() {
        int curItemId = mStartViewPager.getCurrentItem();

        if (curItemId < mStartViewPager.getAdapter().getCount()) {
            mStartViewPager.setCurrentItem(curItemId + 1, true);
        } else {
            throw new UnsupportedOperationException("page id can not be greater or equal then "
                    + mStartViewPager.getAdapter().getCount());
        }

    }

    @Override
    public void onBackPressed() {
        if (mStartViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mStartViewPager.setCurrentItem(mStartViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private BaseFragment getCurrentPageFragment() {
        return (BaseFragment) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.start_view_pager + ":" + mStartViewPager.getCurrentItem());
    }

    private void setupPagerView() {
        mStartViewPager = findViewById(R.id.start_view_pager);
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(this);
        mStartViewPager.setAdapter(viewPagerAdapter);
        mStartViewPager.setPageTransformer(false, new ZoomOutSlideTransformer());
        mStartViewPager.addOnPageChangeListener(this);
    }
}