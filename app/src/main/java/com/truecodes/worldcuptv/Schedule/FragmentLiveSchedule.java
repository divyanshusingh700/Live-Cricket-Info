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
import com.truecodes.worldcuptv.Schedule.Adapters.LiveScheduleAdapter;
import com.truecodes.worldcuptv.Schedule.Models.Live;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class FragmentLiveSchedule extends Fragment {
    private LiveScheduleAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Live> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-match/live-scores";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_live_schedule, viewGroup, false);
        this.noMatchTextView = (TextView) inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LiveScheduleAdapter liveScheduleAdapter = new LiveScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = liveScheduleAdapter;
        this.recyclerView.setAdapter(liveScheduleAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }

    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentLiveSchedule.this.progressBar.setVisibility(0);
            FragmentLiveSchedule.this.schedules.clear();
            FragmentLiveSchedule.this.adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            FragmentLiveSchedule.this.progressBar.setVisibility(View.GONE);
            FragmentLiveSchedule.this.adapter.notifyDataSetChanged();
            if (FragmentLiveSchedule.this.adapter.getItemCount() == 0) {
                FragmentLiveSchedule.this.noMatchTextView.setVisibility(View.VISIBLE);
            } else {
                FragmentLiveSchedule.this.noMatchTextView.setVisibility(View.GONE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentLiveSchedule.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-tms-itm");
                int size = select.size();
                int i = 0;
                int i2 = 0;
                while (i2 < size) {
                    String text = select.get(i2).select("div.cb-schdl").eq(i).select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                    String text2 = select.get(i2).select("div.cb-schdl").eq(i).select("span.text-gray").eq(i).text();
                    String attr = select.get(i2).select("div.cb-schdl").eq(i).select("h3.cb-lv-scr-mtch-hdr").select("a").attr("href");
                    String text3 = select.get(i2).select("div.cb-schdl").eq(i).select("span.text-gray").eq(1).text();
                    String text4 = select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well").eq(i).select("span.cb-text-preview").text();
                    String text5 = select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well").select("div.cb-scr-wll-chvrn").eq(i).select("div.cb-text-live").text();
                    FragmentLiveSchedule.this.schedules.add(new Live(select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-text-complete").text(), text4, text5, text3, text, text2, attr, select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well").select("div.cb-scr-wll-chvrn").eq(i).select("div.cb-hmscg-bat-txt").text(), select.get(i2).select("div.cb-schdl").eq(1).select("a.cb-lv-scrs-well").select("div.cb-scr-wll-chvrn").eq(0).select("div.cb-hmscg-bwl-txt").text()));
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