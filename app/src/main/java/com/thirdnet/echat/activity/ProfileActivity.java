package com.thirdnet.echat.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.thirdnet.echat.R;
import com.tr4android.support.extension.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    public static final String MORPHING_FLAG = "MORPHING_FLAG";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.portrait)
    CircleImageView mCircleImageView;


    /**
     * 左上角导航栏所用图标
     */
    private MaterialMenuDrawable mMaterialMenuDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mCircleImageView.setImageResource(R.mipmap.portrait_test1);

        if (Build.VERSION.SDK_INT < 30)
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        else {
            mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
            mToolbar.setNavigationIcon(mMaterialMenuDrawable);
            mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.BURGER);
            mMaterialMenuDrawable.setTransformationDuration(600);
            mToolbar.post(new Runnable() {
                @Override
                public void run() {
                    mMaterialMenuDrawable.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                }
            });
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mMaterialMenuDrawable != null)
            mMaterialMenuDrawable.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }
}
