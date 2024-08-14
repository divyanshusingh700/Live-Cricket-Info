package com.truecodes.worldcuptv.UI;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.RecordsDetailsAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.UI.Model.RecordsDetails;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class RecordsDetailsScreen extends AppCompatActivity {
    private AdManager adManager;
    private RecordsDetailsAdapter adapter;
    private FrameLayout frameLayout;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private ArrayList<RecordsDetails> recordsDetails = new ArrayList<>();
    private RecyclerView recyclerView;
    private String scorePosition;
    private String scoreTitle;
    private String secondScorePosition;
    private String secondScoreTitle;
    private String url;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_records_details_screen);
        getSupportActionBar().setTitle(getIntent().getStringExtra("recordName"));
        this.frameLayout = (FrameLayout) findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.scoreTitle = getIntent().getStringExtra("scoreTitle");
        this.secondScoreTitle = getIntent().getStringExtra("secondScoreTitle");
        this.scorePosition = getIntent().getStringExtra("scorePosition");
        this.secondScorePosition = getIntent().getStringExtra("secondScorePosition");
        this.url = getIntent().getStringExtra(ImagesContract.URL);
        this.noMatchTextView = (TextView) findViewById(R.id.noMatchTextView);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecordsDetailsAdapter recordsDetailsAdapter = new RecordsDetailsAdapter(this.recordsDetails, this.scoreTitle, this.secondScoreTitle);
        this.adapter = recordsDetailsAdapter;
        this.recyclerView.setAdapter(recordsDetailsAdapter);
        new GetLiveSchedule().execute(new Void[0]);
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

    /* loaded from: classes.dex */
    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RecordsDetailsScreen.this.progressBar.setVisibility(View.VISIBLE);
            RecordsDetailsScreen.this.recordsDetails.clear();
            RecordsDetailsScreen.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            RecordsDetailsScreen.this.progressBar.setVisibility(View.GONE);
            RecordsDetailsScreen.this.adapter.notifyDataSetChanged();
            if (RecordsDetailsScreen.this.adapter.getItemCount() == 0) {
                RecordsDetailsScreen.this.noMatchTextView.setVisibility(View.VISIBLE);
            } else {
                RecordsDetailsScreen.this.noMatchTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(RecordsDetailsScreen.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("table").select("tbody > tr");
                int size = select.size();
                Log.d("itesms", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("td").eq(0).select("a").text();
                    String text2 = select.get(i).select("td").eq(Integer.valueOf(RecordsDetailsScreen.this.scorePosition).intValue()).text();
                    String text3 = select.get(i).select("td").eq(Integer.valueOf(RecordsDetailsScreen.this.secondScorePosition).intValue()).text();
                    if (text2 != null && !text2.equals("")) {
                        RecordsDetailsScreen.this.recordsDetails.add(new RecordsDetails(text, text2, text3));
                    }
                    Log.d("itesms", "doInBackground: " + text);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }
}