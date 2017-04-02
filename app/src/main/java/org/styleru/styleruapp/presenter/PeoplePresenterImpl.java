package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.PeopleModel;
import org.styleru.styleruapp.model.PeopleModelImpl;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.util.ErrorListener;
import org.styleru.styleruapp.view.PeopleView;

import java.util.ArrayList;
import java.util.function.Consumer;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 29.03.17.
 */

public class PeoplePresenterImpl implements PeoplePresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    public static final int BATCH_SIZE=25;

    private PeopleView view;
    private PeopleModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public PeoplePresenterImpl(PeopleView view) {
        this.view=view;
        this.model=new PeopleModelImpl();
        model.setDataChangedListener(()->{
            if(currentId!=0)
                onDataAppend(currentId,BATCH_SIZE);
            else
                onDataUpdate(BATCH_SIZE);
        });
        model.setErrorListener(new ErrorListener() {
                                   @Override
                                   public void accept(Throwable throwable) {
                                       view.showError(throwable);
                                       view.stopProgressBar();
                                   }});
    }

    @Override
    public void onDataAppend(int offset, int batchSize) {
        currentId=offset;
        disposable = model.getData(batchSize,currentId)
                // .flatMap(Observable::from)
                //.toList()
                .subscribe(response -> view.appendData(response),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId+=batchSize;
    }
    @Override
    public void onModelUpdateCachedData(){
        model.updateCachedData();
        model.setRequestString("");
        view.setData(new ArrayList<>());
        view.startProgressBar();
        onDataUpdate(BATCH_SIZE);
        currentId=0;
    }
    @Override
    public void onDataUpdate(int batchSize) {
        currentId=0;
        disposable = model.getData(batchSize,currentId)
                //.toList()
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.setData(response);
                            view.onDataUpdated();
                        },
                        throwable ->
                        {
                            view.stopProgressBar();
                            view.showError(throwable);
                        },
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId+=batchSize;
    }

    @Override
    public void onSetFilter(PeopleFilter filter) {
        model.setFilter(filter);
        view.setData(new ArrayList<>());
        view.startProgressBar();
        onDataUpdate(BATCH_SIZE);
        currentId=0;
    }
    @Override
    public void onSetRequestString(String requestString) {
        model.setRequestString(requestString);
        view.setData(new ArrayList<>());
        view.startProgressBar();
        onDataUpdate(BATCH_SIZE);
        currentId=0;
    }

    @Override
    public void onStop() {

    }

}
