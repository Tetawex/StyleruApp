package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tetawex on 01.04.2017.
 */

public class PeopleModelImpl implements PeopleModel {
    public static final int BATCH_SIZE=256;

    private String requestString="";
    private PeopleFilter filter=new PeopleFilter();

    private String authToken;

    private Action dataChangedListener;
    private Consumer<Throwable> errorListener;

    private boolean finishedLoading;
    private Disposable disposable= Disposables.empty();
    private ApiService apiService;

    private List<PeopleItem> filteredItemList;
    private List<PeopleItem> itemList;

    public PeopleModelImpl(){
        itemList =new ArrayList<>();
        filteredItemList =itemList;
        apiService= Singletons.getApiService();
        authToken=Singletons.getPreferencesManager().getAuthToken();
        appendData(0);
    }
    @Override
    public Observable<List<PeopleItem>> getData(int batchSize,int currentId) {
        int cap=currentId+batchSize;
        if(cap>filteredItemList.size())
            cap=filteredItemList.size();
        return Observable.just(new ArrayList<>(filteredItemList.subList(currentId,cap)));
        //я додумался завернуть саблист в новый массив только после получаса борьбы с ConcurrentModificationException
        /*return Observable.fromArray(filteredItemList.subList(currentId,cap))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
    }
    public void appendData(int offset){
        Observable<PeopleResponse> observable =apiService
                .getApiInterface()
                .getPeople(new PeopleRequest(authToken,BATCH_SIZE,offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable=observable.subscribe(
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
                    {appendData(itemList.size());
                        dataChangedListener.run();}
                    else
                        finishedLoading=true;

                });
    }
    private void filter(){
        filteredItemList=new ArrayList<>();
        for (PeopleItem item :itemList) {
            if(!(item.getFirstName()+" "+item.getLastName()).toLowerCase().contains(requestString.toLowerCase()))
                continue;
            if(!filter.valid(item))
                continue;

            filteredItemList.add(item);
        }
    }
    @Override
    public void setFilter(PeopleFilter filter){
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
        itemList.clear();
        appendData(0);
    }
    @Override
    public void setDataChangedListener(Action dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }
    @Override
    public void setErrorListener(Consumer<Throwable> errorListener) {
        this.errorListener = errorListener;
    }
}
