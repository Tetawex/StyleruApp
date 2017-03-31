
package org.styleru.styleruapp.model.dto.support;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subskills")
    @Expose
    private List<Subskill> subskills = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subskill> getSubskills() {
        return subskills;
    }

    public void setSubskills(List<Subskill> subskills) {
        this.subskills = subskills;
    }

}
