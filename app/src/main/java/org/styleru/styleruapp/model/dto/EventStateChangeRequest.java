package org.styleru.styleruapp.model.dto;

/**
 * Created by Tetawex on 09.03.2017.
 */

public class EventStateChangeRequest {
    private String token;
    private int id;
    private int currentState;
    private int desiredState;

    public EventStateChangeRequest(String token, int id, int currentState, int desiredState) {
        this.token = token;
        this.id = id;
        this.currentState = currentState;
        this.desiredState = desiredState;
    }
}
