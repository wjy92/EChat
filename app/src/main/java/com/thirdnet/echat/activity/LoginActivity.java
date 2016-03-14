package com.thirdnet.echat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dd.morphingbutton.MorphingButton;
import com.dd.morphingbutton.impl.IndeterminateProgressButton;
import com.jakewharton.rxbinding.view.RxView;
import com.rey.material.widget.EditText;
import com.thirdnet.echat.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btnLogin)
    IndeterminateProgressButton mBtnLogin;

    private boolean mMorphFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        RxView.clicks(mBtnLogin)
                .throttleFirst(200, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onBtnLoginClick();
                    }
                });

        morphToSquare(0);
    }

    private void onBtnLoginClick() {
        if (mMorphFlag) {
            mMorphFlag = !mMorphFlag;
            morphToSquare(getResources().getInteger(R.integer.login_mp_duration));
        } else {
            mMorphFlag = !mMorphFlag;
            simulateProgress();
        }
    }


    private void simulateProgress() {
        int progressColor1 = getResources().getColor(R.color.morphing_blue_bright);
        int progressColor2 = getResources().getColor(R.color.morphing_green_light);
        int progressColor3 = getResources().getColor(R.color.morphing_orange_light);
        int color = getResources().getColor(R.color.morphing_gray);
        int progressCornerRadius = getResources().getDimensionPixelSize(R.dimen.login_mp_corner_radius_4);
        int width = (int) getResources().getDimension(R.dimen.login_mp_width_200);
        int height = (int) getResources().getDimension(R.dimen.login_mp_height_8);
        int duration = getResources().getInteger(R.integer.login_mp_duration);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                morphToSuccess(mBtnLogin);
                mBtnLogin.unblockTouch();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }, 1000);

            }
        }, 4000);
        mBtnLogin.blockTouch(); // prevent user from clicking while button is in progress
        mBtnLogin.morphToProgress(color, progressCornerRadius, width, height, duration, progressColor1, progressColor2);
    }


    private void morphToSquare(int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius((int) getResources().getDimension(R.dimen.login_mp_corner_radius_2))
                .width((int) getResources().getDimension(R.dimen.login_mp_width_100))
                .height((int) getResources().getDimension(R.dimen.login_mp_height_56))
                .color(getResources().getColor(R.color.colorPrimary))
                .colorPressed(getResources().getColor(R.color.colorPrimaryDark))
                .text("登录");
        mBtnLogin.morph(square);
    }

    private void morphToSuccess(final IndeterminateProgressButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(getResources().getInteger(R.integer.login_mp_duration))
                .cornerRadius((int) getResources().getDimension(R.dimen.login_mp_height_56))
                .width((int) getResources().getDimension(R.dimen.login_mp_height_56))
                .height((int) getResources().getDimension(R.dimen.login_mp_height_56))
                .color(getResources().getColor(R.color.morphing_gray_green))
                .colorPressed(getResources().getColor(R.color.morphing_gray_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
