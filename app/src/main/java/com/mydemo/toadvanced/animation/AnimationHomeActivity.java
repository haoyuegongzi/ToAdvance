package com.mydemo.toadvanced.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationHomeActivity extends BaseActivity {

    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.btnRotate)
    Button btnRotate;
    @BindView(R.id.btnTranslate)
    Button btnTranslate;
    @BindView(R.id.btnScale)
    Button btnScale;
    @BindView(R.id.btnSlpha)
    Button btnSlpha;
    @BindView(R.id.btnSet)
    Button btnSet;

    Animation mAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_home);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnRotate, R.id.btnTranslate, R.id.btnScale, R.id.btnSlpha, R.id.btnSet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRotate:
                mAnimation = AnimationUtils.loadAnimation(this, R.anim.iv_rotate);
                ivHeader.startAnimation(mAnimation);
                break;
            case R.id.btnTranslate:

                break;
            case R.id.btnScale:

                break;
            case R.id.btnSlpha:

                break;
            case R.id.btnSet:
                
                break;
            default:
                break;
        }
    }
}
