package com.truecodes.worldcuptv.Rankings;



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
import com.truecodes.worldcuptv.Rankings.ODI.FragmentODITabRanking;
import com.truecodes.worldcuptv.Rankings.T20.FragmentT20TabRanking;
import com.truecodes.worldcuptv.Rankings.Test.FragmentTestTabRanking;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class RankingScreen extends AppCompatActivity {
    AdManager adManager;
    FrameLayout frameLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ranking_screen);
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(this, getLifecycle());
        builder.getDialogProperties().setConnectionCallback(new ConnectionCallback() {
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
        this.tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager =findViewById(R.id.viewpager);
        this.viewPager = viewPager;
        this.tabLayout.setupWithViewPager(viewPager);
        addTabs(this.viewPager);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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
            return this.mFragment.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragment.size();
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