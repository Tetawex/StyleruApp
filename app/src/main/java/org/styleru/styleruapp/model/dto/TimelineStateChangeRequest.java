package org.styleru.styleruapp.model.dto;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelineStateChangeRequest {
    private String token;
    private int id;
    private int currentState;
    private int desiredState;

    public TimelineStateChangeRequest(String token, int id, int currentState, int desiredState) {
        this.token = token;
        this.id = id;
        this.currentState = currentState;
        this.desiredState = desiredState;
    }
}
