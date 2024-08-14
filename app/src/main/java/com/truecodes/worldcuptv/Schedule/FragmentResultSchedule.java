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
import com.truecodes.worldcuptv.Schedule.Adapters.RecentScheduleAdapter;
import com.truecodes.worldcuptv.Schedule.Models.Recents;
//import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class FragmentResultSchedule extends Fragment {
    private RecentScheduleAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Recents> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-match/live-scores/recent-matches";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_result_schedule, viewGroup, false);
        this.noMatchTextView =   inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView =   inflate.findViewById(R.id.recyclerView);
        this.progressBar =   inflate.findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecentScheduleAdapter recentScheduleAdapter = new RecentScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = recentScheduleAdapter;
        this.recyclerView.setAdapter(recentScheduleAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }

    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentResultSchedule.this.progressBar.setVisibility(View.VISIBLE);
            FragmentResultSchedule.this.schedules.clear();
            FragmentResultSchedule.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            FragmentResultSchedule.this.progressBar.setVisibility(View.GONE);
            FragmentResultSchedule.this.adapter.notifyDataSetChanged();
            if (FragmentResultSchedule.this.adapter.getItemCount() == 0) {
                FragmentResultSchedule.this.noMatchTextView.setVisibility(View.VISIBLE);
            } else {
                FragmentResultSchedule.this.noMatchTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentResultSchedule.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-tms-itm");
                int size = select.size();
                int i = 0;
                int i2 = 0;
                while (i2 < size) {
                    String text = select.get(i2).select("div.cb-schdl").eq(i).select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                    String text2 = select.get(i2).select("div.cb-schdl").eq(i).select("span.text-gray").eq(i).text();
                    String attr = select.get(i2).select("div.cb-schdl").eq(i).select("h3.cb-lv-scr-mtch-hdr").select("a").attr("href");
                    String text3 = select.get(i2).select("div.cb-schdl").eq(i).select("span.text-gray").eq(1).text();
                    String text4 = select.get(i2).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(i).select("a.cb-text-complete").text();
                    String text5 = select.get(i2).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(i).select("a.cb-text-inprogress").text();
                    FragmentResultSchedule.this.schedules.add(new Recents(select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-text-complete").text(), text4, text5, text3, text, text2, attr, select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-hmscg-bat-txt").text(), select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-hmscg-bwl-txt").text()));
//                    Log.d(FirebaseAnalytics.Param.ITEMS, "doInBackground: " + attr);
                    i2++;
                    i = 0;
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