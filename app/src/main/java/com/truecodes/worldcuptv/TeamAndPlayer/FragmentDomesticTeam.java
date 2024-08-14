package com.truecodes.worldcuptv.TeamAndPlayer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.truecodes.worldcuptv.R;


public class FragmentDomesticTeam extends Fragment {
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_domestic_team, viewGroup, false);
    }
}