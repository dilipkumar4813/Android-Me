package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created on 01/05/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legsIndex = 0;

    public static final String INDEX_HEAD = "head_index";
    public static final String INDEX_BODY = "body_index";
    public static final String INDEX_LEGS = "legs_index";

    private boolean mTwoPane = false;
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (Button) findViewById(R.id.btn_next);
        if (findViewById(R.id.ll_display_droid) != null) {
            mTwoPane = true;
            btnNext.setVisibility(View.GONE);

            FragmentManager fragmentManager = getSupportFragmentManager();

            BodyPartFragment headPartFragment = new BodyPartFragment();
            headPartFragment.setImageIds(AndroidImageAssets.getHeads());
            headPartFragment.setListIndex(headIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headPartFragment)
                    .commit();

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyPartFragment.setListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyPartFragment)
                    .commit();

            BodyPartFragment legsPartFragment = new BodyPartFragment();
            legsPartFragment.setImageIds(AndroidImageAssets.getLegs());
            legsPartFragment.setListIndex(legsIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.feet_container, legsPartFragment)
                    .commit();
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Image position" + position, Toast.LENGTH_SHORT).show();
        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            BodyPartFragment newPartFragment = new BodyPartFragment();

            switch (bodyPartNumber) {
                case 0:
                    newPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    newPartFragment.setListIndex(listIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.head_container, newPartFragment)
                            .commit();
                    break;
                case 1:
                    newPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    newPartFragment.setListIndex(listIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.body_container, newPartFragment)
                            .commit();
                    break;
                case 2:
                    newPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    newPartFragment.setListIndex(listIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.feet_container, newPartFragment)
                            .commit();
                    break;
            }
        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legsIndex = listIndex;
                    break;
            }
        }


        Bundle b = new Bundle();
        b.putInt(INDEX_HEAD, headIndex);
        b.putInt(INDEX_BODY, bodyIndex);
        b.putInt(INDEX_LEGS, legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
