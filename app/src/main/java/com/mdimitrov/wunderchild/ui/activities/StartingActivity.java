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


import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.ui.adapters.viewpager.StartViewPagerAdapter;
import com.mdimitrov.wunderchild.utils.AnimationsUtils;
import com.mdimitrov.wunderchild.utils.SharedPreferencesUtils;

public class StartingActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String SAVED_LANGUAGE_KEY = "saved_language";

    private ViewPager mStartViewPager;
    private boolean notFirstTimeSelected = false;
    private StartViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        setupLanguageSpinner();

        setupPagerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mStartViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mStartViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            int fragmentId = mStartViewPager.getCurrentItem() - 1;
            mStartViewPager.setCurrentItem(fragmentId, true);
        }
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
                Log.d("AppDebug", "Language selected: " + parent.getSelectedItem()
                        + " notFirstTimeSelected :" + notFirstTimeSelected
                );
                if (notFirstTimeSelected) {
                    String language = parent.getSelectedItem().toString();
                    mViewPagerAdapter.changeFragmentsLanguage(language);
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

                        mViewPagerAdapter.changeFragmentsLanguage(savedLanguage);
                    }

                    notFirstTimeSelected = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void setupPagerView() {
        mStartViewPager = findViewById(R.id.start_view_pager);
        mViewPagerAdapter = new StartViewPagerAdapter(this);
        mStartViewPager.setAdapter(mViewPagerAdapter);
        mStartViewPager.setPageTransformer(false, new ZoomInTransformer());
    }

    public void loadNextPage() {
        int curItemId = mStartViewPager.getCurrentItem();

        if (curItemId < mStartViewPager.getAdapter().getCount()) {
            int nextItemId = curItemId + 1;
            mStartViewPager.setCurrentItem(nextItemId, true);
        } else {
            throw new UnsupportedOperationException("page id can not be greater or equal then "
                    + mStartViewPager.getAdapter().getCount());
        }

    }

    private void setActivityBackground(int fragmentId) {
        int colorId;
        //Log.d("AppDebug","position "+fragmentId);
        if(fragmentId == 0){
            colorId = R.color.fragment_orange;
        }else if (fragmentId == 1){
            colorId = R.color.fragment_blue;
        } else {
            throw new UnsupportedOperationException("no color for id: " + fragmentId);
        }

        View activityView = findViewById(R.id.activity_wrapper);
/*        AnimationsUtils.animateRevealCenterOfClickedTarget(
                activityView, colorId, (int) activityView.getX(), (int) activityView.getY()
        );*/
        activityView.setBackgroundResource(colorId);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setActivityBackground(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}