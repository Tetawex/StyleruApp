
package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subdepartment implements FilterItem {
    private boolean checked;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setName(String name) {
        this.name = name;
    }

}
