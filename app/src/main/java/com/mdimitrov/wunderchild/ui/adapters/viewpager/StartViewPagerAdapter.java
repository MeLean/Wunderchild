package com.mdimitrov.wunderchild.ui.adapters.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.mdimitrov.wunderchild.R;
import com.mdimitrov.wunderchild.ui.fragments.BaseFragment;
import com.mdimitrov.wunderchild.ui.fragments.FirstPageFragment;
import com.mdimitrov.wunderchild.ui.fragments.SecondPageFragment;

public class StartViewPagerAdapter extends FragmentPagerAdapter {
    private static int mItemsCount;
    private final FragmentManager mFragmentManager;

    public StartViewPagerAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        this.mFragmentManager = activity.getSupportFragmentManager();
        //todo check if всичко е точно ;D
        mItemsCount = 2;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = mFragmentManager.findFragmentByTag("android:switcher:" + R.id.start_view_pager + ":" + position);
                return fragment != null ? fragment : new FirstPageFragment();
            case 1:
                fragment = mFragmentManager.findFragmentByTag("android:switcher:" + R.id.start_view_pager + ":" + position);
                return fragment != null ? fragment : new SecondPageFragment();
            default:
                throw new UnsupportedOperationException("Nod valid position: " + position);
        }
    }

    @Override
    public int getCount() {
        return mItemsCount;
    }

    public void changeFragmentsLanguage(String language){
        for (int i = 0; i < mItemsCount; i++){
            BaseFragment page = (BaseFragment) getItem(i);
            if (page != null){
                page.changeLanguage(language);
            }
        }
    }
}