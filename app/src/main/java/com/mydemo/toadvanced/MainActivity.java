package com.mydemo.toadvanced;

import android.os.Bundle;
import android.view.View;

import com.mydemo.toadvanced.animation.AnimationHomeActivity;

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


    }
}
