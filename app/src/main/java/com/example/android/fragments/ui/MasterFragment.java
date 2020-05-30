package com.example.android.fragments.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.fragments.R;
import com.example.android.fragments.data.AndroidImageAssets;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    onImageClickListener mCallback;
    public MasterFragment() {
        // Required empty public constructor
    }

    public interface onImageClickListener{
        public void onImageClicked(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView mGrid = rootView.findViewById(R.id.gridView);
        MasterListAdapter masterListAdapter = new MasterListAdapter(this.getContext(), AndroidImageAssets.getAll());
        mGrid.setAdapter(masterListAdapter);
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageClicked(position);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{mCallback = (onImageClickListener) context;}
        catch (ClassCastException e){
            Log.e("MasterFragment","Activity not implementing listener");
        }

    }
}
