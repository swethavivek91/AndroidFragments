package com.example.android.fragments.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.fragments.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class BodyPartFragment extends Fragment {

    public static final String IMAGEID= "BODY_IMAGE_ID";
    public static final String IMAGEARRAY= "BODY_IMAGE_ARRAY";
    public int imageID;
    ArrayList<Integer> mImageIds = new ArrayList<>();

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public BodyPartFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("BodyF","OnCreateView"+savedInstanceState);
        if(savedInstanceState!=null){
            imageID = savedInstanceState.getInt(IMAGEID);
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGEARRAY);
        }
        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);
        ImageView imageView = (ImageView)rootView.findViewById(R.id.bodypartImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImageIds.size()-1==imageID){
                    setImageID(0);
                }else{
                    setImageID(imageID+1);
                }
                imageView.setImageResource(mImageIds.get(getImageID()));
            }
        });

        imageView.setImageResource(mImageIds.get(getImageID()));
        return rootView;
    }

    public void setImageIds(ArrayList<Integer> ids){
        mImageIds.addAll(0,ids);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(IMAGEID,getImageID());
        outState.putIntegerArrayList(IMAGEARRAY,mImageIds);
        //super.onSaveInstanceState(outState);
    }
}
