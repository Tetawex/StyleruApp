package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.LoginResponse;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;

import io.reactivex.Observable;

/**
 * Created by Tetawex on 31.03.2017.
 */

public interface LoginModel extends Model{
    Observable<LoginResponse> getLoginResponse(LoginRequest request);
    Observable<LoginResponse> validateToken(ValidateTokenRequest request);
}
