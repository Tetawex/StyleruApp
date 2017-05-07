
package org.styleru.styleruapp.model.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Request;

public class VacancyResponse {

    @SerializedName("id_vacancy")
    @Expose
    private int idVacancy;
    @SerializedName("requests")
    @Expose
    private List<Request> requests = null;
    @SerializedName("recommend_submission")
    @Expose
    private boolean recommendSubmission;
    @SerializedName("approve_submission")
    @Expose
    private boolean approveSubmission;

    public int getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public boolean isRecommendSubmission() {
        return recommendSubmission;
    }

    public void setRecommendSubmission(boolean recommendSubmission) {
        this.recommendSubmission = recommendSubmission;
    }

    public boolean isApproveSubmission() {
        return approveSubmission;
    }

    public void setApproveSubmission(boolean approveSubmission) {
        this.approveSubmission = approveSubmission;
    }

}
