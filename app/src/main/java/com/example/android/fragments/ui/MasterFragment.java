package com.example.android.fragments.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.fragments.R;
import com.example.android.fragments.data.AndroidImageAssets;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView mGrid = rootView.findViewById(R.id.gridView);
        MasterListAdapter masterListAdapter = new MasterListAdapter(this.getContext(), AndroidImageAssets.getAll());
        mGrid.setAdapter(masterListAdapter);
        return rootView;
    }
}
