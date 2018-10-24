package com.balaganovrocks.yourmasterclean.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.balaganovrocks.yourmasterclean.R;

public class settings extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button Exit = (Button) findViewById(R.id.exit);
        Button Privacy_Policy = (Button) findViewById(R.id.privacy_policy);
        Button btnChangeLaguage = (Button) findViewById(R.id.btnChangeLanguage);
        View.OnClickListener oclBtnExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), Exit.class);
                startActivityForResult(intent, 0);
            }
        };
        View.OnClickListener oclBtnPrivacyPolicy = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Privacy_Policy.class);
                startActivityForResult(intent, 0);
            }
        };
        View.OnClickListener oclBtnChangeLanguage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), ChangeLanguage.class);
                startActivityForResult(intent, 0);
            }
        };
        Exit.setOnClickListener(oclBtnExit);
        Privacy_Policy.setOnClickListener(oclBtnPrivacyPolicy);
        btnChangeLaguage.setOnClickListener(oclBtnChangeLanguage);
    }
}
