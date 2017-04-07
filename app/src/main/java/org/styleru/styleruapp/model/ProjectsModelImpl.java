package org.styleru.styleruapp.model;

import android.util.Log;

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
        filteredItemList =new ArrayList<>();
        apiService= Singletons.getApiService();
        authToken=Singletons.getPreferencesManager().getAuthToken();
        appendData(0);
    }
    @Override
    public Observable<List<ProjectsItem>> getData(int batchSize, int currentId) {
        int cap=currentId+batchSize;
        int start=currentId;
        if(cap>filteredItemList.size())
            cap=filteredItemList.size();
        if(currentId>=cap)
            start=cap;
        return Observable.just(new ArrayList<>(filteredItemList.subList(start,cap)));
    }
    public void appendData(int offset){
        Observable<ProjectsResponse> observable =apiService
                .getApiInterface()
                .getProjects(new ProjectsRequest(authToken,BATCH_SIZE,offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable=observable.subscribe(
                response ->
                {
                    itemList.addAll(response.getData());
                    filteredItemList.addAll(filter(response.getData()));
                    dataChangedListener.run();
                },
                throwable ->
                {
                    errorListener.accept(throwable);
                    finishedLoading=true;
                },
                () -> {
                    if(!finishedLoading&&itemList.size()<1000)//TODO: убрать временный костыль
                    {
                        appendData(itemList.size()+offset);
                    }
                    else {
                        finishedLoading=true;
                        if(!disposable.isDisposed()) {
                            disposable.dispose();
                    }
                    }
                });
    }
    private List<ProjectsItem> filter(List<ProjectsItem> fullList){
        List<ProjectsItem> fList=new ArrayList<>();
        for (ProjectsItem item :fullList) {
            if(filter.valid(item)&&
            (item.getName()+item.getManagerName()).toLowerCase()
                    .contains(requestString.toLowerCase())) {
                fList.add(item);
            }
        }
        return fList;
    }
    private void filter(){
        filteredItemList=new ArrayList<>();
        for (ProjectsItem item :itemList) {
            if(!filter.valid(item))
                continue;
            if(!(item.getName()+item.getManagerName()).toLowerCase()
                    .contains(requestString.toLowerCase()))
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
        requestString="";
        finishedLoading=false;
        itemList.clear();
        filteredItemList.clear();
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
