package com.truecodes.worldcuptv.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.truecodes.worldcuptv.CricketSeries.Fragments.CricketSeriesTeamsFragment;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.FragmentRegularTeam;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class TeamBottomNavFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_team_bottom_nav, viewGroup, false);
        ViewPager viewPager = inflate.findViewById(R.id.viewpager);
        viewPager = viewPager;
        addTabs(viewPager);
        TabLayout tabLayout = inflate.findViewById(R.id.tabs);
        tabLayout = tabLayout;
        tabLayout.setupWithViewPager(viewPager);
        return inflate;
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new CricketSeriesTeamsFragment(), "WORLD CUP TEAM");
        viewPagerAdapter.addFrag(new FragmentRegularTeam(), "Regular Team");
        viewPager.setAdapter(viewPagerAdapter);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("seriesUrl", "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/squads");
            fragment.setArguments(bundle);
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return mFragmentTitleList.get(i);
        }
    }
}