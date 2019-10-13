package com.mydemo.toadvanced.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO:Tween动画
 */
public class AnimationHomeActivity extends BaseActivity {
    Animation mAnimation;
    AnimationHomeActivity mActivity;


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
    @BindView(R.id.btnTransScreen)
    Button btnTransScreen;
    @BindView(R.id.tvValue)
    TextView tvValue;
    @BindView(R.id.btnValue)
    Button btnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_home);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @OnClick({R.id.btnRotate, R.id.btnTranslate, R.id.btnScale, R.id.btnSlpha, R.id.btnSet, R.id.btnTransScreen,
            R.id.btnValue, R.id.ivHeader, R.id.btnObject, R.id.btnPath})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHeader:
                Log.i(TAG, "onViewClicked: ivHeader的位置在哪里？");
                break;
            case R.id.btnRotate:
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_rotate);
                ivHeader.startAnimation(mAnimation);
                break;
            //只是移动了ivHeader的影响，实际位置还是在原来的地方，点击事件也是在原来的地方。
            case R.id.btnTranslate:
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_translate);
                ivHeader.startAnimation(mAnimation);
                break;
            case R.id.btnTransScreen:
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_translate_screen);
                ivHeader.startAnimation(mAnimation);
                break;
            case R.id.btnScale:
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_scale);
                ivHeader.startAnimation(mAnimation);
                break;
            case R.id.btnSlpha:
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_alpha);
                ivHeader.startAnimation(mAnimation);
                break;
            case R.id.btnSet:
                Context context;
                mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.iv_set);
                ivHeader.startAnimation(mAnimation);
                break;
            //属性动画
            case R.id.btnValue:
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 500);
                valueAnimator.setDuration(1500);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.setRepeatCount(2);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //完成百分比:0→→→→1；
                        float function = animation.getAnimatedFraction();
                        //完成值
                        float value = (float) animation.getAnimatedValue();
                        Log.i(TAG, "onAnimationUpdate: function==" + function + "\n value==" + value);
                        tvValue.setText(value + "");
                        tvValue.setAlpha(function);
                        ivHeader.setRotationX(value);
                        ivHeader.setRotationY(value);

                        ivHeader.setScaleX(function);
                        ivHeader.setScaleY(function);
                        // 通过属性动画的移动，ivHeader的实际位置也随之移动，
                        // 移动完成后，原来所在位置的点击事件不再响应，但新的位置处能够响应点击事件
                        ivHeader.setTranslationY(200 * function);
                    }
                });
                valueAnimator.start();
                break;
            //对象动画
            case R.id.btnObject:
                //rotationX、rotationY:x y向的旋转
                //translationY、translationY:x y向的移动
                //Alpha：透明度
                //scaleX、scaleY：x y向的缩放
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivHeader, "Alpha", 1, 0);
                objectAnimator.setDuration(1500);
                objectAnimator.setRepeatCount(1);
                objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
                objectAnimator.start();
                break;
            case R.id.btnPath:
                Path path = new Path();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    path.addArc(0, 0, 300, 200, 0,100);
                    ObjectAnimator obj = ObjectAnimator.ofFloat(ivHeader, View.X, View.Y, path);
                    obj.setInterpolator(new AnticipateInterpolator());
                    obj.setDuration(1500);
                    obj.setRepeatCount(10);
                    obj.setRepeatMode(ObjectAnimator.REVERSE);
                    obj.start();
                }
                break;
            default:
                break;
        }
    }
}
