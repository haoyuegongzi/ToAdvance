package com.mydemo.toadvanced.animation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;
import com.mydemo.toadvanced.widget.SurfaceAnimView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO:矢量动画：对图形做动画
 */
public class VectorAnimationActivity extends BaseActivity {

    @BindView(R.id.btnSurfaceAnimView)
    SurfaceAnimView btnSurfaceAnimView;
    @BindView(R.id.btnSurface)
    Button btnSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSurface)
    public void onViewClicked() {
        add();
    }

    public void add() {
        Drawable drawable =  this.getResources().getDrawable(R.drawable.shishi160228243);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        SurfaceBean surfaceBean = new SurfaceBean(bitmap, btnSurfaceAnimView);
        btnSurfaceAnimView.addBean(surfaceBean);
    }
}
