package com.example.realestate.RealEstate;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapterRealEstate extends FragmentStatePagerAdapter {

    // FragmentPagerAdapter

    private final List<Fragment> Fragmentlist = new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();

    public ViewpagerAdapterRealEstate(FragmentManager fm) {
        //super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        return Fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }

    public void addFragment(Fragment fragment, String Title){
        Fragmentlist.add(fragment);
        FragmentListTitles.add(Title);
    }


}