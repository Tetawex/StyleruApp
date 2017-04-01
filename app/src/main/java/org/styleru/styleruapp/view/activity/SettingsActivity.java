package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;

/**
 * Created by Пользователь on 29.03.2017.
 */

public class SettingsActivity extends AppCompatActivity{
    Toolbar tool32;
    public static final int IDM_HSE = 101;
    public static final int IDM_REU = 102;
    public static final int IDM_ALL = 103;
    Button but,exit;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
        tool32 = (Toolbar) findViewById(R.id.toolbar5);
       tool32.setTitle("Настройки");
        setSupportActionBar(tool32);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#111111"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        but = (Button) findViewById(R.id.vuz);

        registerForContextMenu(but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(but);
            }
        });
        exit= (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singletons.getPreferencesManager().setAuthToken("");
                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, IDM_HSE, Menu.NONE, "ВШЭ");
        menu.add(Menu.NONE, IDM_REU, Menu.NONE, "РЭУ");
        menu.add(Menu.NONE, IDM_ALL, Menu.NONE, "ВСЕ");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case IDM_HSE:
               but.setText("ВШЭ");
                break;
            case IDM_REU:
                but.setText("РЭУ");
                break;
            case IDM_ALL:
                but.setText("ВСЕ");
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
