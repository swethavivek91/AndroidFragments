package com.example.android.fragments.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.fragments.R;
import com.example.android.fragments.data.AndroidImageAssets;

import java.util.ArrayList;

public class MasterActivity extends AppCompatActivity implements MasterFragment.onImageClickListener{

    int headIndex;
    int bodyIndex;
    int legIndex;
    boolean mTwoPane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.android_fragment_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.gridView);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
            Button nextButton = (Button) findViewById(R.id.button);
            nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getHeads());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.header_container, headFragment)
                        .commit();

                // New body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // New leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
    }

    @Override
    public void onImageClicked(int position) {
        Toast.makeText(this,"Ïmage clicked with position:"+position,Toast.LENGTH_LONG ).show();
        int bodyPartNumber = position/12;
        int listIndex = position-12*bodyPartNumber;
        if(mTwoPane == true){
            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getHeads());
                    newFragment.setImageID(listIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.header_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getBodies());
                    newFragment.setImageID(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds((ArrayList<Integer>) AndroidImageAssets.getLegs());
                    newFragment.setImageID(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);
            Intent mIntent = new Intent(this, MainActivity.class);
            mIntent.putExtras(b);
            // The "Next" button launches a new AndroidMeActivity
            Button nextButton = (Button) findViewById(R.id.button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(mIntent);
                }
            });
        }

    }
}
