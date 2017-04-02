package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 27.03.2017.
 */

public class Project_Profile extends AppCompatActivity {


    Button need;
    Toolbar tool;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_profile);

        tool = (Toolbar) findViewById(R.id.toolbar_proj_prof);
        tool.setTitle("Проект");
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        need = (Button) findViewById(R.id.needed);
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Vacancy.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}








