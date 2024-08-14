package com.truecodes.worldcuptv.TeamAndPlayer;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.AsiaCupPlayer.AsiaCupTeamDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;
import soup.neumorphism.NeumorphCardView;


public class FragmentAsiaCupTeam extends Fragment implements View.OnClickListener {
    private NeumorphCardView aFirstTeamCardView;
    private NeumorphCardView aSecondTeamCardView;
    private NeumorphCardView aThirdTeamCardView;
    AdManager adManager;
    int[] asiaCupIconList = {R.drawable.afghanistan, R.drawable.ban, R.drawable.hong_kong, R.drawable.ind, R.drawable.pakistan, R.drawable.srilanka};
    String[] asiaCupItemList;
    String[] asiaCupSquad;
    private NeumorphCardView bFirstTeamCardView;
    private NeumorphCardView bSecondTeamCardView;
    private NeumorphCardView bThirdTeamCardView;
    RecyclerView recyclerView;
    String[] regular_team_url;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_asia_cup_team, viewGroup, false);
        this.adManager = new AdManager(getContext(), getActivity());
        this.aFirstTeamCardView = inflate.findViewById(R.id.aFirstTeamCardView);
        this.aSecondTeamCardView = inflate.findViewById(R.id.aSecondTeamCardView);
        this.aThirdTeamCardView = inflate.findViewById(R.id.aThirdTeamCardView);
        this.bFirstTeamCardView = inflate.findViewById(R.id.bFirstTeamCardView);
        this.bSecondTeamCardView = inflate.findViewById(R.id.bSecondTeamCardView);
        this.bThirdTeamCardView = inflate.findViewById(R.id.bThirdTeamCardView);
        this.aFirstTeamCardView.setOnClickListener(this);
        this.aSecondTeamCardView.setOnClickListener(this);
        this.aThirdTeamCardView.setOnClickListener(this);
        this.bFirstTeamCardView.setOnClickListener(this);
        this.bSecondTeamCardView.setOnClickListener(this);
        this.bThirdTeamCardView.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.aFirstTeamCardView) {
            Intent intent = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/india/2");
            intent.putExtra("team", "");
            intent.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4499/asia-cup-2022/squads/20989");
            startActivity(intent);
            this.adManager.showInterstitial();
        } else if (view.getId() == R.id.aSecondTeamCardView) {
            Intent intent2 = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent2.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/pakistan/3");
            intent2.putExtra("team", "");
            intent2.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4499/asia-cup-2022/squads/20996");
            startActivity(intent2);
            this.adManager.showInterstitial();
        } else if (view.getId() == R.id.aThirdTeamCardView) {
            Intent intent3 = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent3.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/hong-kong/8");
            intent3.putExtra("team", "");
            intent3.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4506/asia-cup-qualifier-2022/squads/20961");
            startActivity(intent3);
            this.adManager.showInterstitial();
        } else if (view.getId() == R.id.bFirstTeamCardView) {
            Intent intent4 = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent4.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/afghanistan/96");
            intent4.putExtra("team", "");
            intent4.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4499/asia-cup-2022/squads/21010");
            startActivity(intent4);
            this.adManager.showInterstitial();
        } else if (view.getId() == R.id.bSecondTeamCardView) {
            Intent intent5 = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent5.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/bangladesh/6");
            intent5.putExtra("team", "");
            intent5.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4499/asia-cup-2022/squads/21003");
            startActivity(intent5);
            this.adManager.showInterstitial();
        } else if (view.getId() != R.id.bThirdTeamCardView) {
        } else {
            Intent intent6 = new Intent(getContext(), AsiaCupTeamDetailsScreen.class);
            intent6.putExtra(ImagesContract.URL, "https://www.cricbuzz.com/cricket-team/sri-lanka/5");
            intent6.putExtra("team", "");
            intent6.putExtra("squad", "https://m.cricbuzz.com/cricket-series/4499/asia-cup-2022/squads/21017");
            startActivity(intent6);
            this.adManager.showInterstitial();
        }
    }
}