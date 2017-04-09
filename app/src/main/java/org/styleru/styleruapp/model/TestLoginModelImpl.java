package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.LoginResponse;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Tetawex on 31.03.2017.
 */
@Deprecated
public class TestLoginModelImpl implements LoginModel {
    @Override
    public Observable<LoginResponse> getLoginResponse(LoginRequest request) {
        if(request.getUserEmail().equals("admin")&&request.getUserPassword().equals("admin"))
            return Observable.just(generateLoginResponse());
        else
            return Observable.error(new Exception("meh"));
    }

    @Override
    public Observable<LoginResponse> validateToken(ValidateTokenRequest request) {
        return Observable.just(generateLoginResponse());
    }
    public LoginResponse generateLoginResponse(){
        LoginResponse response=new LoginResponse();
        response.setFirstName("Иван");
        response.setLastName("Иванов");
        response.setEmail("blazerhero@coockle.ua");
        response.setToken("t366eyy3663yy366e4djg98");
        response.setUserId(322);
        response.setImageUrl("https://1fichier.com/?grewwa64sp");
        return response;
    }
}
