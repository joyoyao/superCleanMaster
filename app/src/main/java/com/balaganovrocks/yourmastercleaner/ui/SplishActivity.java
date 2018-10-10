package com.balaganovrocks.yourmastercleaner.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.balaganovrocks.yourmastercleaner.base.BaseActivity;
import com.balaganovrocks.yourmastercleaner.R;
import com.balaganovrocks.yourmastercleaner.base.BaseActivity;
import com.balaganovrocks.yourmastercleaner.fragment.MainFragment;
import com.balaganovrocks.yourmastercleaner.service.CleanerService;
import com.balaganovrocks.yourmastercleaner.service.CoreService;
import com.balaganovrocks.yourmastercleaner.utils.SharedPreferencesUtils;


public class SplishActivity extends BaseActivity {

    /**
     * 三个切换的动画
     */
    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;
    private Button BtnStart;

    //  @InjectView(R.id.image)
    ImageView mImageView;

    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);

        mImageView = (ImageView) findViewById(R.id.image);
        BtnStart = (Button) findViewById(R.id.start_main);
        //int index = new Random().nextInt(2);
        //if (index == 1) {
        //    mImageView.setImageResource(R.drawable.entrance3);
        //} else {
        //    mImageView.setImageResource(R.drawable.entrance2);
        //}
        View.OnClickListener OclBtnStart = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonEffect(BtnStart);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        };
        BtnStart.setOnClickListener(OclBtnStart);
        startService(new Intent(this, CoreService.class));
        startService(new Intent(this, CleanerService.class));


        if (!SharedPreferencesUtils.isShortCut(mContext)) {
            createShortCut();
        }

       // initAnim();
//        setListener();
    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    private void createShortCut() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "一键加速");
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.short_cut_icon));
        Intent i = new Intent();
        i.setAction("com.balaganovrocks.shortcut");
        i.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
        sendBroadcast(intent);
        SharedPreferencesUtils.setIsShortCut(mContext, true);
    }

    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(500);
        mFadeInScale = AnimationUtils.loadAnimation(this,
                R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(2000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        mFadeOut.setDuration(500);
//        mImageView.startAnimation(mFadeIn);
    }


    /**
     * 监听事件
     */
 // public void setListener() {
 //     /**
 //      * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
 //      * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
 //      */
 //     mFadeIn.setAnimationListener(new AnimationListener() {

 //         public void onAnimationStart(Animation animation) {

 //         }

 //         public void onAnimationRepeat(Animation animation) {

 //         }

 //         public void onAnimationEnd(Animation animation) {
 //             mImageView.startAnimation(mFadeInScale);
 //         }
 //     });
 //     mFadeInScale.setAnimationListener(new AnimationListener() {

 //         public void onAnimationStart(Animation animation) {

 //         }

 //         public void onAnimationRepeat(Animation animation) {

 //         }

 //         public void onAnimationEnd(Animation animation) {
 //             startActivity(MainActivity.class);
 //             finish();
 //             // mImageView.startAnimation(mFadeOut);
 //         }
 //     });
 //     mFadeOut.setAnimationListener(new AnimationListener() {

 //         public void onAnimationStart(Animation animation) {

 //         }

 //         public void onAnimationRepeat(Animation animation) {

 //         }

 //         public void onAnimationEnd(Animation animation) {
 //             // startActivity(MainActivity.class);
 //         }
 //     });
 // }
}
