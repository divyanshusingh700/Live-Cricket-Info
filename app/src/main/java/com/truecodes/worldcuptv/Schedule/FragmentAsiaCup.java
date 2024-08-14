package com.truecodes.worldcuptv.Schedule;



import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.Adapters.AsiaCupScheduleAdapter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentAsiaCup extends Fragment {
    private AsiaCupScheduleAdapter adapter;
    private TextView noMatchTextView;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<AsiaCupSchedule> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/matches";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_asia_cup, viewGroup, false);
        this.noMatchTextView = (TextView) inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AsiaCupScheduleAdapter asiaCupScheduleAdapter = new AsiaCupScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = asiaCupScheduleAdapter;
        this.recyclerView.setAdapter(asiaCupScheduleAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }

    /* loaded from: classes.dex */
    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentAsiaCup.this.progressBar.setVisibility(0);
            FragmentAsiaCup.this.schedules.clear();
            FragmentAsiaCup.this.adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentAsiaCup.this.progressBar.setVisibility(8);
            FragmentAsiaCup.this.adapter.notifyDataSetChanged();
            if (FragmentAsiaCup.this.adapter.getItemCount() == 0) {
                FragmentAsiaCup.this.noMatchTextView.setVisibility(0);
            } else {
                FragmentAsiaCup.this.noMatchTextView.setVisibility(8);
            }
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentAsiaCup.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-series-filters").select("div.cb-series-matches");
                int size = select.size();
                Log.d("itemss", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("span").text();
                    String attr = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").select("a.text-hvr-underline").attr("href");
                    String text2 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("div.text-gray").text();
                    String text3 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("a.cb-text-complete").text();
                    String text4 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("a.cb-text-inprogress").text();
                    FragmentAsiaCup.this.schedules.add(new AsiaCupSchedule(select.get(i).select("div.cb-col-75").select("div.cb-col-40").select("span.schedule-date").attr("timestamp"), text3, text4, text2, text, attr));
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