package com.example.android.fragments.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.fragments.R;

public class MasterActivity extends AppCompatActivity implements MasterFragment.onImageClickListener{

    int headIndex;
    int bodyIndex;
    int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
    }

    @Override
    public void onImageClicked(int position) {
        Toast.makeText(this,"Ïmage clicked with position:"+position,Toast.LENGTH_LONG ).show();
        int bodyPartNumber = position/12;
        int listIndex = position-12*bodyPartNumber;
        switch (bodyPartNumber){
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2:legIndex = listIndex;
                break;
            default:break;
        }

        Bundle b = new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);
        Intent mIntent = new Intent(this,MainActivity.class);
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
