package com.truecodes.worldcuptv.UI;



import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.StadiumAdapter;
import com.truecodes.worldcuptv.R;


public class StadiumScreen extends AppCompatActivity {
    AdManager adManager;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    String[] stadiumImage;
    String[] stadiumName;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_stadium_screen);
        this.frameLayout = (FrameLayout) findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.stadiumName = getResources().getStringArray(R.array.stadium_name);
        this.stadiumImage = getResources().getStringArray(R.array.stadium_image);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        this.recyclerView.setAdapter(new StadiumAdapter(this.stadiumName, this.stadiumImage, getApplicationContext()));
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