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
import com.truecodes.worldcuptv.Schedule.Adapters.UpcomingScheduleAdapter;
import com.truecodes.worldcuptv.Schedule.Models.Upcomings;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentUpcomingSchedule extends Fragment {
    private UpcomingScheduleAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Upcomings> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-match/live-scores/upcoming-matches";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_upcoming_schedule, viewGroup, false);
        this.noMatchTextView = (TextView) inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UpcomingScheduleAdapter upcomingScheduleAdapter = new UpcomingScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = upcomingScheduleAdapter;
        this.recyclerView.setAdapter(upcomingScheduleAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }


    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility( View.VISIBLE);
            schedules.clear();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentUpcomingSchedule.this.progressBar.setVisibility(View.GONE);
            FragmentUpcomingSchedule.this.adapter.notifyDataSetChanged();
            if (FragmentUpcomingSchedule.this.adapter.getItemCount() == 0) {
                FragmentUpcomingSchedule.this.noMatchTextView.setVisibility( View.VISIBLE);
            } else {
                FragmentUpcomingSchedule.this.noMatchTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentUpcomingSchedule.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-tms-itm");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-schdl").eq(0).select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                    String text2 = select.get(i).select("div.cb-schdl").eq(0).select("span.text-gray").eq(0).text();
                    String attr = select.get(i).select("div.cb-schdl").eq(0).select("h3.cb-lv-scr-mtch-hdr").select("a").attr("href");
                    String text3 = select.get(i).select("div.cb-schdl").eq(0).select("span.text-gray").eq(1).text();
                    String text4 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("a.cb-text-complete").text();
                    String text5 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("a.cb-text-inprogress").text();
                    FragmentUpcomingSchedule.this.schedules.add(new Upcomings(select.get(i).select("div.cb-schdl").eq(0).select("div.text-gray").select("span").attr("ng-bind"), text4, text5, text3, text, text2, attr));
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