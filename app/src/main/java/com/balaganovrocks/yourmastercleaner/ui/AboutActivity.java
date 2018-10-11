package com.balaganovrocks.yourmastercleaner.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.balaganovrocks.yourmastercleaner.R;
import com.balaganovrocks.yourmastercleaner.base.BaseSwipeBackActivity;
import com.balaganovrocks.yourmastercleaner.utils.AppUtil;

import butterknife.InjectView;

public class AboutActivity extends BaseSwipeBackActivity {

    @InjectView(R.id.subVersion)
    TextView subVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle("关于");
        TextView tv = (TextView) findViewById(R.id.app_information);
        Linkify.addLinks(tv, Linkify.ALL);

        subVersion.setText("V"+ AppUtil.getVersion(mContext));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
