
package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeline {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("event_type")
    @Expose
    private Integer eventType;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("action_name")
    @Expose
    private String actionName;
    @SerializedName("date")
    @Expose
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
