package com.thirdnet.echat.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.AddDrawerAdapter;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * 作者：杨水强
 * 时间：2016/3/11
 */
public class AddActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_rv)
    RecyclerView mDrawerRv;

    @Bind(R.id.accept)
    TextView mAccept;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    AddDrawerAdapter mDrawerAdapter;
    /**
     * 左上角导航栏所用图标
     */
    private MaterialMenuDrawable mMaterialMenuDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.ARROW);
        mToolbar.setNavigationIcon(mMaterialMenuDrawable);

        mDrawerRv.setHasFixedSize(true);
        mDrawerRv.setLayoutManager(new LinearLayoutManager(this));
        mDrawerAdapter = new AddDrawerAdapter(this);
        mDrawerRv.setAdapter(mDrawerAdapter);
        mDrawerRv.setItemAnimator(new DefaultItemAnimator());
        mAccept.setKeyListener(null);//收件人EditText不可编辑

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭DrawerLayout手势滑动
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//开启DrawerLayout手势滑动
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭DrawerLayout手势滑动
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        RxView.clicks(mAccept)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mDrawerRv, "您的工作有最新动态", Snackbar.LENGTH_LONG)
                        .setAction("查看", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                            }
                        })
                        .show();
            }
        }, 5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_conversation, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
