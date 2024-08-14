package com.truecodes.worldcuptv.Rankings.Test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.truecodes.worldcuptv.R;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class FragmentTestTabRanking extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_fragment_test_ranking, viewGroup, false);
        this.tabLayout = inflate.findViewById(R.id.tabs);
        ViewPager viewPager = inflate.findViewById(R.id.viewpager);
        this.viewPager = viewPager;
        this.tabLayout.setupWithViewPager(viewPager);
        addTabs(this.viewPager);
        return inflate;
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new FragmentTestTeamRanking(), "Team");
        viewPagerAdapter.addFrag(new FragmentTestBatting(), "Batsman");
        viewPagerAdapter.addFrag(new FragmentTestBowling(), "Bowler");
        viewPagerAdapter.addFrag(new FragmentTestAllRounder(), "All Rounder");
        viewPager.setAdapter(viewPagerAdapter);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragment = new ArrayList();
        private final List<String> mTitle = new ArrayList();

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mTitle.get(i);
        }

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public ViewPagerAdapter(FragmentManager fragmentManager, int i) {
            super(fragmentManager, i);
        }

        public void addFrag(Fragment fragment, String str) {
            this.mFragment.add(fragment);
            this.mTitle.add(str);
        }

        @Override
        public Fragment getItem(int i) {
            return this.mFragment.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragment.size();
        }
    }
}