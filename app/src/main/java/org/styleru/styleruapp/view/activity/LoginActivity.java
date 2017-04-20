package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.presenter.LoginPresenter;
import org.styleru.styleruapp.presenter.LoginPresenterImpl;
import org.styleru.styleruapp.util.Md5Hash;
import org.styleru.styleruapp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 13.03.2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.progressbar)
    View progressbar;
    @BindView(R.id.login_button)
    Button button;
    @BindView(R.id.login_edit)
    EditText login;
    @BindView(R.id.password_edit)
    EditText password;

    private LoginPresenter presenter;

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter=new LoginPresenterImpl(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.onLogin(login.getText().toString(),
                        Md5Hash.md5(Md5Hash.md5(password.getText().toString())));//double hash means double security
            }
        });
        presenter.onValidateToken();
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        finish();
    }





    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this,throwable.getMessage(),Toast.LENGTH_LONG).show();
        Log.e("auth",throwable.getMessage());
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void switchToMainPage() {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}