package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

/**
 * Created on 01/05/17.
 *
 * @author dilipkumar4813
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legsIndex;

    public static final String INDEX_HEAD = "head_index";
    public static final String INDEX_BODY = "body_index";
    public static final String INDEX_LEGS = "legs_index";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Image position" + position, Toast.LENGTH_SHORT).show();
        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;
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

        Bundle b = new Bundle();
        b.putInt(INDEX_HEAD, headIndex);
        b.putInt(INDEX_BODY, bodyIndex);
        b.putInt(INDEX_LEGS, legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        Button btn = (Button) findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
