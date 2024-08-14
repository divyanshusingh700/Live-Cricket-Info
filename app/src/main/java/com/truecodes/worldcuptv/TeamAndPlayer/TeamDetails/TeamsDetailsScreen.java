package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Fragments.TeamsResultsFragment;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Fragments.TeamsScheduleFragment;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Fragments.TeamsSquadFragment;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;


public class TeamsDetailsScreen extends AppCompatActivity {
    AdManager adManager;
    FrameLayout frameLayout;
    private TabLayout tabLayout;
    private String teamName;
    private String url;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_teams_details_screen);
        this.frameLayout = findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.url = getIntent().getStringExtra(ImagesContract.URL);
        this.teamName = getIntent().getStringExtra("team");
        getWindow().getDecorView().setSystemUiVisibility(8192);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_color));
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager = viewPager;
        addTabs(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout = tabLayout;
        tabLayout.setupWithViewPager(this.viewPager);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.adManager.destroyBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.adManager.loadBanner(this.frameLayout);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new TeamsScheduleFragment(), "Schedule");
        viewPagerAdapter.addFrag(new TeamsResultsFragment(), "Recent");
        viewPagerAdapter.addFrag(new TeamsSquadFragment(), "Squad");
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
            bundle.putString(ImagesContract.URL, TeamsDetailsScreen.this.url);
            bundle.putString("teamName", TeamsDetailsScreen.this.teamName);
            fragment.setArguments(bundle);
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }
}