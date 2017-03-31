
package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subskill {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("score")
    @Expose
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
