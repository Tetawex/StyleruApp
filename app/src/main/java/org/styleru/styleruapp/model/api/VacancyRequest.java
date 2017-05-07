package org.styleru.styleruapp.model.api;

/**
 * Created by tetawex on 30.04.17.
 */

public class VacancyRequest {
    private int id;
    private String token;

    public VacancyRequest(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
