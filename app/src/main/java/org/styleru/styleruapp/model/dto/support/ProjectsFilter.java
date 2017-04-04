package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.ProjectsItem;

/**
 * Created by tetawex on 03.04.17.
 */

public class ProjectsFilter {
    @SerializedName("filter_mode")
    @Expose
    private int filterMode;//0-all,1-unfinished,2-finished,3-vacant

    public int getFilterMode() {
        return filterMode;
    }

    public void setFilterMode(int filterMode) {
        this.filterMode = filterMode;
    }
    public boolean valid(ProjectsItem item){
        return (filterMode==0||filterMode==1&&item.isFinished()
                ||filterMode==2&&(!item.isFinished())||filterMode==3&&item.isVacantPlaces());
    }

    public ProjectsFilter(int filterMode) {
        this.filterMode = filterMode;
    }

    public ProjectsFilter() {
    }
}
