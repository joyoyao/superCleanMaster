package com.balaganovrocks.yourmastercleaner.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.balaganovrocks.yourmastercleaner.R;
public class Exit extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit);

        Button Yes_Exit = (Button) findViewById(R.id.yes_exit);
        Button No_Exit = (Button) findViewById(R.id.no_exit);

        View.OnClickListener oclBtnExit = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finishAffinity();
                System.exit(0);
            }
        };
        View.OnClickListener oclBtnNoExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        };
        Yes_Exit.setOnClickListener(oclBtnExit);
        No_Exit.setOnClickListener(oclBtnNoExit);
    }
}
