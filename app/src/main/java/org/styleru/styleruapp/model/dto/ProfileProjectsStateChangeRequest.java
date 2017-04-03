package org.styleru.styleruapp.model.dto;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsStateChangeRequest {
    private String token;
    private int id;
    private int currentState;
    private int desiredState;

    public ProfileProjectsStateChangeRequest(String token, int id, int currentState, int desiredState) {
        this.token = token;
        this.id = id;
        this.currentState = currentState;
        this.desiredState = desiredState;
    }
}
