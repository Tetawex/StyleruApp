package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 13.03.2017.
 */

public class LoginActivity extends AppCompatActivity {


    Button btn;
    EditText login;
    EditText password;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login = (EditText) findViewById(R.id.log) ;
        password = (EditText) findViewById(R.id.pass) ;
        btn = (Button) findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}