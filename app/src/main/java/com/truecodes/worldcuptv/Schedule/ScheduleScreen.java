package com.truecodes.worldcuptv.Schedule;



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
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class ScheduleScreen extends AppCompatActivity {
    AdManager adManager;
    FrameLayout frameLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_schedule_screen);
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(this, getLifecycle());
        builder.getDialogProperties().setConnectionCallback(new ConnectionCallback() { // from class: com.digital.livecricketapp.Schedule.ScheduleScreen.1
            @Override
            public void hasActiveConnection(boolean z) {
            }
        });
        builder.build();
        this.frameLayout = (FrameLayout) findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_color));
        }
        ViewPager viewPager =   findViewById(R.id.viewpager);
        this.viewPager = viewPager;
        addTabs(viewPager);
        TabLayout tabLayout =   findViewById(R.id.tabs);
        this.tabLayout = tabLayout;
        tabLayout.setupWithViewPager(this.viewPager);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FragmentLiveSchedule(), "Live");
        viewPagerAdapter.addFrag(new FragmentUpcomingSchedule(), "Upcoming");
        viewPagerAdapter.addFrag(new FragmentResultSchedule(), "Result");
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
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
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
}