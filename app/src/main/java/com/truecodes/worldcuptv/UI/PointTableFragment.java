package com.truecodes.worldcuptv.UI;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.AsiaCupPointTableAdapter;
import com.truecodes.worldcuptv.Models.AsiaCupPointTable;
import com.truecodes.worldcuptv.R;
import java.util.ArrayList;


public class PointTableFragment extends Fragment {
    private AsiaCupPointTableAdapter adapter;
    private AsiaCupPointTableAdapter adapterB;
    private TextView error;
    private LinearLayout pointTableHead;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewB;
    private ArrayList<AsiaCupPointTable> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-series/4499/asia-cup-2022/points-table";
    private ArrayList<AsiaCupPointTable> schedulesB = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_point_table, viewGroup, false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.recyclerViewB = (RecyclerView) inflate.findViewById(R.id.recyclerViewB);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        TextView textView = (TextView) inflate.findViewById(R.id.error);
        this.error = textView;
        textView.setVisibility(View.GONE);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.pointTableHead);
        this.pointTableHead = linearLayout;
        linearLayout.setVisibility(View.GONE);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AsiaCupPointTableAdapter asiaCupPointTableAdapter = new AsiaCupPointTableAdapter(this.schedules);
        this.adapter = asiaCupPointTableAdapter;
        this.recyclerView.setAdapter(asiaCupPointTableAdapter);
        this.recyclerViewB.setHasFixedSize(true);
        this.recyclerViewB.setLayoutManager(new LinearLayoutManager(getContext()));
        AsiaCupPointTableAdapter asiaCupPointTableAdapter2 = new AsiaCupPointTableAdapter(this.schedulesB);
        this.adapterB = asiaCupPointTableAdapter2;
        this.recyclerViewB.setAdapter(asiaCupPointTableAdapter2);
        new Content().execute(new Void[0]);
        return inflate;
    }

    private class Content extends AsyncTask<Void, Void, Void> {
        private Content() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PointTableFragment.this.schedules.clear();
            PointTableFragment.this.schedulesB.clear();
            PointTableFragment.this.adapterB.notifyDataSetChanged();
            PointTableFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            PointTableFragment.this.progressBar.setVisibility(View.GONE);
            PointTableFragment.this.adapter.notifyDataSetChanged();
            PointTableFragment.this.adapterB.notifyDataSetChanged();
            PointTableFragment.this.recyclerView.setVisibility( View.VISIBLE);
            if (PointTableFragment.this.adapter.getItemCount() == 0) {
                PointTableFragment.this.error.setVisibility(View.VISIBLE);
                PointTableFragment.this.pointTableHead.setVisibility(View.GONE);
                return;
            }
            PointTableFragment.this.recyclerView.setVisibility(View.VISIBLE);
            PointTableFragment.this.error.setVisibility(View.GONE);
            PointTableFragment.this.pointTableHead.setVisibility(View.VISIBLE);
        }

        @Override
        public java.lang.Void doInBackground(java.lang.Void... r30) {

            throw new UnsupportedOperationException("Method not decompiled: com.truecodes.worldcuptv.UI.PointTableFragment.Content.doInBackground(java.lang.Void[]):java.lang.Void");
        }
    }
}