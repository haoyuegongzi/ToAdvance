package com.mydemo.toadvanced.animation;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;

public class ExplodeAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String value = getIntent().getStringExtra(sKey);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!TextUtils.isEmpty(value)){
                switch (value){
                    case "AllDirections":
                        getWindow().setEnterTransition(new Explode());
                        break;
                    case "BottomToTop":
                        getWindow().setEnterTransition(new Slide());
                        break;
                    case "Alpha":
                        getWindow().setEnterTransition(new Fade());
                        break;
                    case "Share":
                        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                        break;
                    default:
                        break;
                }
            }

        }
        setContentView(R.layout.activity_explode_animation);
    }
}
