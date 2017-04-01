package org.styleru.styleruapp.presenter;

/**
 * Created by Tetawex on 31.03.2017.
 */

public interface LoginPresenter extends Presenter{
    void onLogin(String email,String password);
    void onValidateToken();
}
