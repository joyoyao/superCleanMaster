package com.yzy.supercleanmaster.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yzy.supercleanmaster.R;

public class settings extends Activity {

    String[] data = {"Русский", "Английский", "Украинский", "Белорусский", "Немецкий"};
    private final int IDD_THREE_BUTTONS = 0;
    AlertDialog.Builder ad;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button Exit = (Button) findViewById(R.id.exit);
        Button Privacy_Policy = (Button) findViewById(R.id.privacy_policy);
        Spinner spinner = (Spinner) findViewById(R.id.language);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Язык");
        // выделяем элемент
        spinner.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
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
        Exit.setOnClickListener(oclBtnExit);
        Privacy_Policy.setOnClickListener(oclBtnPrivacyPolicy);
    }
}
