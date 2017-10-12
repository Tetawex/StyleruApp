package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.VacancyResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by tetawex on 25.04.17.
 */

public interface VacancyModel extends Model {
    Observable<VacancyResponse> getVacancyData(int id);

    Completable approveVacancy(int id);

    Completable recommendVacancy(int id);
}
