package com.balaganovrocks.yourmasterclean.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.balaganovrocks.yourmasterclean.R;
import com.balaganovrocks.yourmasterclean.base.BaseSwipeBackActivity;
import com.balaganovrocks.yourmasterclean.utils.AppUtil;

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
        getActionBar().setTitle("на");
        TextView tv = (TextView) findViewById(R.id.app_information);
        Linkify.addLinks(tv, Linkify.ALL);

        subVersion.setText("V"+ AppUtil.getVersion(mContext));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
