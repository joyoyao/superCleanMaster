package com.balaganovrocks.yourmasterclean.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.balaganovrocks.yourmasterclean.R;

public class ChangeLanguage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        RadioButton rbtnRussian = (RadioButton)findViewById(R.id.rbtnRussian);
        rbtnRussian.setOnClickListener(radioButtonClickListener);

        RadioButton rbtnEnglish = (RadioButton)findViewById(R.id.rbtnEnglish);
        rbtnEnglish.setOnClickListener(radioButtonClickListener);

        RadioButton rbtnSpanish = (RadioButton)findViewById(R.id.rbtnSpanish);
        rbtnSpanish.setOnClickListener(radioButtonClickListener);
    }
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.rbtnRussian:

                    break;
                case R.id.rbtnEnglish:

                    break;
                case R.id.rbtnSpanish:

                    break;

                default:
                    break;
            }
        }
    };
}
