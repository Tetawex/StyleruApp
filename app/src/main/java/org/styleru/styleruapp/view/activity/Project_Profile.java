package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 27.03.2017.
 */

public class Project_Profile extends AppCompatActivity {
Button need;
    Button back1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_profile);
        need = (Button) findViewById(R.id.needed);
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Vacancy.class);
                startActivity(intent);
            }
        });

        Log.d("BUT","2");
        back1 = (Button) findViewById(R.id.back3);
        Log.d("BUT","3");
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUT","1");
                finish();
            }
        });

    }
}
