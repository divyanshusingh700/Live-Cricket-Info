package com.truecodes.worldcuptv.TeamAndPlayer;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.TeamAdapters;
import com.truecodes.worldcuptv.R;


public class FragmentRegularTeam extends Fragment {
    int[] asiaCupIconList = {R.drawable.afghanistan, R.drawable.aus, R.drawable.ban, R.drawable.ind, R.drawable.eng, R.drawable.nz, R.drawable.pakistan, R.drawable.sa, R.drawable.srilanka, R.drawable.wi, R.drawable.zim, R.drawable.ire, R.drawable.kenya, R.drawable.scotland};
    String[] asiaCupItemList;
    RecyclerView recyclerView;
    String[] regularTeamUrl;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_regular_team, viewGroup, false);
        asiaCupItemList = getResources().getStringArray(R.array.regular_team_name);
        regularTeamUrl = getResources().getStringArray(R.array.regular_team_url);
        RecyclerView recyclerView =  inflate.findViewById(R.id.recyclerView);
        recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new TeamAdapters(this.asiaCupItemList, this.regularTeamUrl, this.asiaCupIconList, getContext(), getActivity()));
        return inflate;
    }
}