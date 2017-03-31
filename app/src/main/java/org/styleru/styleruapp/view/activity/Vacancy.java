package org.styleru.styleruapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 29.03.2017.
 */

public class Vacancy extends AppCompatActivity {


    Button back;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacancy);

//        back = (Button) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
