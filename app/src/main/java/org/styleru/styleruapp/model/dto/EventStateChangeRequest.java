package org.styleru.styleruapp.model.dto;

/**
 * Created by Tetawex on 09.03.2017.
 */

public class EventStateChangeRequest {
    private String token;

    private int id;
    private int currentState;
    private int desiredState;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getDesiredState() {
        return desiredState;
    }

    public void setDesiredState(int desiredState) {
        this.desiredState = desiredState;
    }
}
