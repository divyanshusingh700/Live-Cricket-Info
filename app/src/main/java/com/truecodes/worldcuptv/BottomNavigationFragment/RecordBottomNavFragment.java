package com.truecodes.worldcuptv.BottomNavigationFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.RecordsAdapters;
import com.truecodes.worldcuptv.OnClickListener.OnClickInterface;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.UI.RecordsDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

public class RecordBottomNavFragment extends Fragment implements OnClickInterface {
    AdManager adManager;
    int[] recordsImageArray = {R.drawable.highest_totals, R.drawable.most_runs, R.drawable.high_score, R.drawable.highest_avareage, R.drawable.duck, R.drawable.six, R.drawable.wicket, R.drawable.ball};
    String[] recordsTextArray;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_record_bottom_nav, viewGroup, false);
        this.adManager = new AdManager(getContext(), getActivity());
        this.recordsTextArray = getResources().getStringArray(R.array.recordsArrayList);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.recyclerView.setAdapter(new RecordsAdapters(this.recordsImageArray, this.recordsTextArray, this));
        return inflate;
    }

    @Override
    public void onItemClickListener(int i) {
        if (i == 0) {
            Intent intent = new Intent(getContext(), RecordsDetailsScreen.class);
            intent.putExtra("recordName", this.recordsTextArray[i]);
            intent.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/team-highest-innings-totals/world-cup-12");
            intent.putExtra("scorePosition", IronSourceConstants.BOOLEAN_TRUE_AS_STRING);
            intent.putExtra("secondScorePosition", ExifInterface.GPS_MEASUREMENT_3D);
            intent.putExtra("scoreTitle", "Score");
            intent.putExtra("secondScoreTitle", "Run rate");
            startActivity(intent);
            adManager.showInterstitial();
        } else if (i == 1) {
            Intent intent2 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent2.putExtra("recordName", this.recordsTextArray[i]);
            intent2.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/batting-most-runs-career/world-cup-12");
            intent2.putExtra("scorePosition", "5");
            intent2.putExtra("secondScorePosition", ExifInterface.GPS_MEASUREMENT_2D);
            intent2.putExtra("scoreTitle", "Runs");
            intent2.putExtra("secondScoreTitle", "Innings");
            startActivity(intent2);
            adManager.showInterstitial();
        } else if (i == 2) {
            Intent intent3 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent3.putExtra("recordName", this.recordsTextArray[i]);
            intent3.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/batting-most-runs-innings/world-cup-12");
            intent3.putExtra("scorePosition", IronSourceConstants.BOOLEAN_TRUE_AS_STRING);
            intent3.putExtra("secondScorePosition", ExifInterface.GPS_MEASUREMENT_2D);
            intent3.putExtra("scoreTitle", "Runs");
            intent3.putExtra("secondScoreTitle", "Balls");
            startActivity(intent3);
            adManager.showInterstitial();
        } else if (i == 3) {
            Intent intent4 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent4.putExtra("recordName", this.recordsTextArray[i]);
            intent4.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/batting-highest-career-batting-average/world-cup-12");
            intent4.putExtra("scorePosition", "7");
            intent4.putExtra("secondScorePosition", "5");
            intent4.putExtra("scoreTitle", "Average");
            intent4.putExtra("secondScoreTitle", "Runs");
            startActivity(intent4);
            adManager.showInterstitial();
        } else if (i == 4) {
            Intent intent5 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent5.putExtra("recordName", this.recordsTextArray[i]);
            intent5.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/batting-most-ducks-career/world-cup-12");
            intent5.putExtra("scorePosition", "12");
            intent5.putExtra("secondScorePosition", ExifInterface.GPS_MEASUREMENT_2D);
            intent5.putExtra("scoreTitle", "Ducks");
            intent5.putExtra("secondScoreTitle", "Innings");
            startActivity(intent5);
            adManager.showInterstitial();
        } else if (i == 5) {
            Intent intent6 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent6.putExtra("recordName", this.recordsTextArray[i]);
            intent6.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/batting-most-sixes-career/world-cup-12");
            intent6.putExtra("scorePosition", "14");
            intent6.putExtra("secondScorePosition", "13");
            intent6.putExtra("scoreTitle", "Sixes");
            intent6.putExtra("secondScoreTitle", "Fores");
            startActivity(intent6);
            adManager.showInterstitial();
        } else if (i == 6) {
            Intent intent7 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent7.putExtra("recordName", this.recordsTextArray[i]);
            intent7.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/bowling-most-wickets-career/world-cup-12");
            intent7.putExtra("scorePosition", "8");
            intent7.putExtra("secondScorePosition", ExifInterface.GPS_MEASUREMENT_3D);
            intent7.putExtra("scoreTitle", "Wickets");
            intent7.putExtra("secondScoreTitle", "Innings");
            startActivity(intent7);
            adManager.showInterstitial();
        } else if (i != 7) {
        } else {
            Intent intent8 = new Intent(getContext(), RecordsDetailsScreen.class);
            intent8.putExtra("recordName", this.recordsTextArray[i]);
            intent8.putExtra(ImagesContract.URL, "https://www.espncricinfo.com/records/trophy/bowling-best-figures-innings/world-cup-12");
            intent8.putExtra("scorePosition", "4");
            intent8.putExtra("secondScorePosition", IronSourceConstants.BOOLEAN_TRUE_AS_STRING);
            intent8.putExtra("scoreTitle", "Wickets");
            intent8.putExtra("secondScoreTitle", "Overs");
            startActivity(intent8);
            adManager.showInterstitial();
        }
    }
}