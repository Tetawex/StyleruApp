package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Tetawex on 01.04.2017.
 */

public class TestPeopleModelImpl implements PeopleModel {
    public static final int BATCH_SIZE=50;
    private List<PeopleItem> peopleItemList;
    private Disposable disposable= Disposables.empty();
    private ApiService apiService;
    public TestPeopleModelImpl(){
        peopleItemList=new ArrayList<>();
        apiService=new ApiService();
    }
    public void appendData(int offset){
        //disposable= Observable.error(null);
    }
    @Override
    public Observable<PeopleResponse> getData(int batchSize,int currentId,String requestString, PeopleFilter filter) {
        return null;
    }

}
