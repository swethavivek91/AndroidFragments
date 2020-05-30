/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.fragments.ui;

import android.os.Bundle;

import com.example.android.fragments.R;
import com.example.android.fragments.data.AndroidImageAssets;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_fragment);
        if(savedInstanceState ==null) {
            BodyPartFragment headFrag = new BodyPartFragment();
            headFrag.setImageIds((ArrayList<Integer>) AndroidImageAssets.getHeads());
            headFrag.setImageID(0);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.header_container, headFrag).commit();
            BodyPartFragment bodyFrag = new BodyPartFragment();
            bodyFrag.setImageIds((ArrayList<Integer>) AndroidImageAssets.getBodies());
            bodyFrag.setImageID(0);
            fragmentManager.beginTransaction().add(R.id.body_container, bodyFrag).commit();
            BodyPartFragment legFrag = new BodyPartFragment();
            legFrag.setImageIds((ArrayList<Integer>) AndroidImageAssets.getLegs());
            legFrag.setImageID(0);
            fragmentManager.beginTransaction().add(R.id.leg_container, legFrag).commit();
        }
    }
}

