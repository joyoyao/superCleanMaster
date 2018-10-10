package com.balaganovrocks.yourmastercleaner.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.balaganovrocks.yourmastercleaner.R;

public class Privacy_Policy extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        TextView Text = (TextView) findViewById(R.id.privacy_policy);
    }
}
