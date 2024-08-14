package com.truecodes.worldcuptv.TeamAndPlayer;



import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class PlayersScreen extends AppCompatActivity {
    AdManager adManager;
    private PlayersAdapter adapter;
    FrameLayout frameLayout;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Players> schedules = new ArrayList<>();
    private TextView squadNotFound;
    private String url;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_players_screen);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(getIntent().getStringExtra("teamName") + " Squad");
        this.frameLayout = (FrameLayout) findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.squadNotFound = (TextView) findViewById(R.id.squadNotFound);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.url = getIntent().getStringExtra(ImagesContract.URL);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        PlayersAdapter playersAdapter = new PlayersAdapter(this.schedules, getApplicationContext(), this);
        this.adapter = playersAdapter;
        this.recyclerView.setAdapter(playersAdapter);
    }


    private class Content extends AsyncTask<Void, Void, Void> {
        private Content() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayersScreen.this.schedules.clear();
            PlayersScreen.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            PlayersScreen.this.adapter.notifyDataSetChanged();
            PlayersScreen.this.recyclerView.setVisibility(0);
            PlayersScreen.this.progressBar.setVisibility(8);
            if (PlayersScreen.this.adapter.getItemCount() == 0) {
                PlayersScreen.this.squadNotFound.setVisibility(0);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayersScreen.this.url).userAgent("Opera").get().select("div.team-player-wrap").select("ul.team-player-list > li");
                int size = select.size();
                Log.d("size", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    String attr = select.get(i).select("a").attr("title");
                    String attr2 = select.get(i).select("a").select("img").attr("src");
                    PlayersScreen.this.schedules.add(new Players(attr, attr2));
//                    Log.d(FirebaseAnalytics.Param.ITEMS, "doInBackground: " + attr2);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.schedules.clear();
        this.adManager.destroyBanner();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.schedules.clear();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        new Content().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.adManager.loadBanner(this.frameLayout);
    }
}