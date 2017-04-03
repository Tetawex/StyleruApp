package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.ProjectsItem;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;
import org.styleru.styleruapp.util.ErrorListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tetawex on 01.04.2017.
 */

public class ProjectsModelImpl implements ProjectsModel {
    public static final int BATCH_SIZE=16;

    private String requestString="";
    private ProjectsFilter filter=new ProjectsFilter();

    private String authToken;

    private Action dataChangedListener;
    private Action dataResetListener;
    private ErrorListener errorListener;

    private boolean finishedLoading=false;
    private Disposable disposable= Disposables.empty();
    private ApiService apiService;

    private List<ProjectsItem> filteredItemList;
    private List<ProjectsItem> itemList;

    public ProjectsModelImpl(){
        itemList =new ArrayList<>();
        filteredItemList =itemList;
        apiService= Singletons.getApiService();
        authToken=Singletons.getPreferencesManager().getAuthToken();
        appendData(0);
    }
    @Override
    public Observable<List<ProjectsItem>> getData(int batchSize, int currentId) {
        int cap=currentId+batchSize;
        if(cap>filteredItemList.size())
            cap=filteredItemList.size();
        return Observable.just(new ArrayList<>(filteredItemList.subList(currentId,cap)));
    }
    public void appendData(int offset){
        Observable<ProjectsResponse> observable =apiService
                .getApiInterface()
                .getProjects(new ProjectsRequest(authToken,BATCH_SIZE,offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable=observable.take(BATCH_SIZE).subscribe(
                response ->
                {
                    itemList.addAll(response.getData());
                },
                throwable ->
                {
                    errorListener.accept(throwable);
                    finishedLoading=true;
                },
                () -> {
                    if(!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    if(!finishedLoading&&itemList.size()<500)//TODO: убрать временный костыль
                    {
                        appendData(itemList.size());
                        dataChangedListener.run();
                    }
                    else
                        finishedLoading=true;

                });
    }
    private void filter(){
        filteredItemList=new ArrayList<>();
        for (ProjectsItem item :itemList) {
            if(!(item.getName()+item.getManagerName()).toLowerCase()
                    .contains(requestString.toLowerCase()))
                continue;
            if(!filter.valid(item))
                continue;

            filteredItemList.add(item);
        }
    }
    @Override
    public void setFilter(ProjectsFilter filter){
        this.filter=filter;
        filter();
    }
    @Override
    public void setRequestString(String requestString){
        this.requestString=requestString;
        filter();
    }

    @Override
    public void updateCachedData() {
        finishedLoading=false;
        itemList.clear();
        filteredItemList=itemList;
        appendData(0);
    }
    @Override
    public void setDataChangedListener(Action dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }
    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void setDataResetListener(Action dataResetListener) {
        this.dataResetListener = dataResetListener;
    }
}
