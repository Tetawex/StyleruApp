
package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subskill {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("score")
    @Expose
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
