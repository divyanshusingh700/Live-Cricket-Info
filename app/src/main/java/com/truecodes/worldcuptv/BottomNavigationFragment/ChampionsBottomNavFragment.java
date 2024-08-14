package com.truecodes.worldcuptv.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.ChampionsAdapter;
import com.truecodes.worldcuptv.R;

public class ChampionsBottomNavFragment extends Fragment {
    String[] championsList;
    int[] championsTeamImage = {R.drawable.ind, R.drawable.srilanka, R.drawable.pakistan, R.drawable.ban};
    String[] championsTeamName;
    RecyclerView recyclerView;
    String[] runnersUpList;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_champions_bottom_nav, viewGroup, false);
        championsTeamName = getResources().getStringArray(R.array.championsTeamName);
        championsList = getResources().getStringArray(R.array.championsArrayList);
        runnersUpList = getResources().getStringArray(R.array.runnersUpArrayList);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ChampionsAdapter(championsTeamImage, championsTeamName, championsList, runnersUpList));
        return inflate;
    }
}