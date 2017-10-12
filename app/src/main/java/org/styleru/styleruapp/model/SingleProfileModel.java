package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.SingleProfileRequest;
import org.styleru.styleruapp.model.dto.SingleProfileResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 07.04.2017.
 */

public interface SingleProfileModel extends Model {
    Observable<SingleProfileResponse> getSingleProfile(SingleProfileRequest request);
//    Observable<SingleProfileResponse> validateToken(ValidateTokenRequest request);
}
