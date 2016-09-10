package com.example.hk.mycontacts;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public class WelcomeActivity extends Activity {


    //XML加载动画的TextView

    RelativeLayout rl_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        initView();//加载控件
        initAnim();//加载动画

    }


    private void initView() {

        rl_context=(RelativeLayout) findViewById(R.id.rl_context);
    }

    private void initAnim() {
        //创建透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        //设置透明的持续时间
        alphaAnimation.setDuration(4000);
        //设置透明的插值器(加速)
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        //设置动画播放延迟
        alphaAnimation.setStartOffset(500);


        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //启动动画
        rl_context.startAnimation(alphaAnimation);


    }

}