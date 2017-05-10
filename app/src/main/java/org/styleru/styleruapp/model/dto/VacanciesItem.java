package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Пользователь on 04.04.2017.
 */

public class VacanciesItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("required_amount")
    @Expose
    private int requiredAmount;
    @SerializedName("action")
    @Expose
    private boolean transferToNextPage;
    @SerializedName("status")
    @Expose
    private boolean enabled;

    public boolean isTransferToNextPage() {
        return transferToNextPage;
    }

    public void setTransferToNextPage(boolean transferToNextPage) {
        this.transferToNextPage = transferToNextPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
