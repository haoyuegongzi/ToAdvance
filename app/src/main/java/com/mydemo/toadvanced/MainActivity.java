package com.mydemo.toadvanced;

import android.os.Bundle;
import android.view.View;

import com.mydemo.toadvanced.animation.AnimationHomeActivity;
import com.mydemo.toadvanced.average_distribut.AverageDistributActivity;
import com.mydemo.toadvanced.file.FileOptionsActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jumpView();
    }

    private void jumpView(){
        findViewById(R.id.btnToAnimation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startView(AnimationHomeActivity.class);
            }
        });

        findViewById(R.id.btnToAverage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startView(AverageDistributActivity.class);
            }
        });

        findViewById(R.id.btnFileOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startView(FileOptionsActivity.class);
            }
        });

    }
}
