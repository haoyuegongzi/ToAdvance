package com.mydemo.toadvanced;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mydemo.toadvanced.animation.ExplodeAnimationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.view.View;

public class BaseActivity extends AppCompatActivity {
    public String TAG = "TAGTAG";
    public String sKey = "Animation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

    public void startView(Class<?> classes){
        startActivity(new Intent(this, classes));
    }

    public void startViewFromAllDirections(Class<?> classes){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(this, classes);
            intent.putExtra(sKey, "AllDirections");
            //这个是固定的写法，同时注意目标Activity中的getWindow().setEnterTransition(new Explode());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else {
            startView(ExplodeAnimationActivity.class);
        }
    }

    public void startViewFromBottomToTop(Class<?> classes){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(this, classes);
            intent.putExtra(sKey, "BottomToTop");
            //这个是固定的写法，同时注意目标Activity中的getWindow().setEnterTransition(new Explode());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else {
            startView(ExplodeAnimationActivity.class);
        }
    }

    public void startViewWithAlpha(Class<?> classes){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(this, classes);
            intent.putExtra(sKey, "Alpha");
            //这个是固定的写法，同时注意目标Activity中的getWindow().setEnterTransition(new Explode());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else {
            startView(ExplodeAnimationActivity.class);
        }
    }


}
