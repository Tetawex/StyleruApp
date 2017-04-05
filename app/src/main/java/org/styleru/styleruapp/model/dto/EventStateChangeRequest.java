package org.styleru.styleruapp.model.dto;

/**
 * Created by Tetawex on 09.03.2017.
 */

public class EventStateChangeRequest {
    private String token;
    private int id;

    public EventStateChangeRequest(String token, int id) {
        this.token = token;
        this.id = id;
    }
}
