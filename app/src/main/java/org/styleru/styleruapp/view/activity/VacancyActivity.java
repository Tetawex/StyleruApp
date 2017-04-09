package org.styleru.styleruapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 29.03.2017.
 */

public class VacancyActivity extends AppCompatActivity {


    Button back;
    Toolbar tool;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);

        tool = (Toolbar) findViewById(R.id.toolbar_vac);
        tool.setTitle("Вакансии");
        setSupportActionBar(tool);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
