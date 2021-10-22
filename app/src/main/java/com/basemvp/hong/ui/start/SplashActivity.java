package com.basemvp.hong.ui.start;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.basemvp.hong.R;
import com.basemvp.hong.ui.MainActivity;
import com.basemvp.hong.ui.base.BaseActivity;
import com.basemvp.hong.ui.base.internal.FConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Create by Hong on 2020/4/13 18:44.
 */

@FConfig(value = R.layout.activity_splash,hideToolbar = true)
public class SplashActivity extends BaseActivity {
    @BindView(R.id.lottie_view)
    LottieAnimationView animationView;


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //解决手机按home 点击app图标每次重启问题
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
        List<String> list = new ArrayList<>();
        list.add("pride-hard-seltzer.json");
        list.add("lamsa-splash-screen.json");
        list.add("ramadan-kareem-hello-doc.json");
        list.add("logo-animation.json");
        animationView.playAnimation();
        animationView.setAnimation(list.get(new Random().nextInt(list.size())));
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                startActivity(MainActivity.class);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }
}
