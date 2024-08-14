package com.truecodes.worldcuptv.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Rankings.ODI.FragmentODITabRanking;
import com.truecodes.worldcuptv.Rankings.T20.FragmentT20TabRanking;
import com.truecodes.worldcuptv.Rankings.Test.FragmentTestTabRanking;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

public class RankingBottomNavFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ranking_bottom_nav, viewGroup, false);
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(getActivity(), getLifecycle());
        builder.getDialogProperties().setConnectionCallback(new ConnectionCallback() {
            @Override
            public void hasActiveConnection(boolean z) {
            }
        });
        builder.build();
        tabLayout = inflate.findViewById(R.id.tabs);
        ViewPager viewPager = inflate.findViewById(R.id.viewpager);
        viewPager = viewPager;
        tabLayout.setupWithViewPager(viewPager);
        addTabs(viewPager);
        return inflate;
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new FragmentTestTabRanking(), "Test Ranking");
        viewPagerAdapter.addFrag(new FragmentODITabRanking(), "ODI Ranking");
        viewPagerAdapter.addFrag(new FragmentT20TabRanking(), "T20 Ranking");
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
            return mFragment.get(i);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    }
}