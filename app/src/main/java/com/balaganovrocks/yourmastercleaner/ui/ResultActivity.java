package com.balaganovrocks.yourmastercleaner.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.balaganovrocks.yourmastercleaner.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import static android.content.ContentValues.TAG;

public class ResultActivity extends Activity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btngo_to_saving_battery = (Button)findViewById(R.id.goto_saving_battery);
        Button btngoto_memory_clean = (Button)findViewById(R.id.goto_memory_clean);
        Button btngoto_rate_app = (Button)findViewById(R.id.goto_rate_app);
        btngo_to_saving_battery.setOnClickListener(onClickListener);
        btngoto_memory_clean.setOnClickListener(onClickListener);
        btngoto_rate_app.setOnClickListener(onClickListener);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdSize adSize = new AdSize(300, 250);
        adSize.equals(AdSize.BANNER);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG,"onAdLoaded");// Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(TAG,"onAdFailedToLoad");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                Log.d(TAG,"onAdOpened");
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                Log.d(TAG,"onAdLeftApplication");
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.d(TAG,"onAdClosed");
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.goto_saving_battery:
                    Intent intent = new Intent(v.getContext(), BatterySavingActivity.class);
                    startActivityForResult(intent, 0); // do something
                    break;
                case R.id.goto_memory_clean:
                    Intent intent2 = new Intent(v.getContext(), MemoryCleanActivity.class);
                    startActivityForResult(intent2, 0); // do something
                    break;
                case R.id.goto_rate_app:
                  //  Intent browserIntent = new
                  //          Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com"));
                  //  startActivity(browserIntent);
                    break;
            }

        }
    };
}
