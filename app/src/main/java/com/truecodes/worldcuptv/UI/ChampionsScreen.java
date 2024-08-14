package com.truecodes.worldcuptv.UI;



import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.ChampionsAdapter;
import com.truecodes.worldcuptv.R;

public class ChampionsScreen extends AppCompatActivity {
    AdManager adManager;
    String[] championsList;
    int[] championsTeamImage = {R.drawable.ind, R.drawable.srilanka, R.drawable.pakistan, R.drawable.ban};
    String[] championsTeamName;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    String[] runnersUpList;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_champions_screen);
        frameLayout =   findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
//        adManager = adManager;
        adManager.loadBanner(frameLayout);
        championsTeamName = getResources().getStringArray(R.array.championsTeamName);
        championsList = getResources().getStringArray(R.array.championsArrayList);
        runnersUpList = getResources().getStringArray(R.array.runnersUpArrayList);
        RecyclerView recyclerView =   findViewById(R.id.recyclerView);
//        recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChampionsAdapter(championsTeamImage, championsTeamName, championsList, runnersUpList));
    }

    @Override
    public void onPause() {
        super.onPause();
        adManager.destroyBanner();
    }


    @Override
    public void onResume() {
        super.onResume();
        adManager.loadBanner(frameLayout);
    }
}