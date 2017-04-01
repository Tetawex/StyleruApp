package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.PeopleModel;
import org.styleru.styleruapp.model.ProjectsModel;
import org.styleru.styleruapp.model.TestPeopleModelImpl;
import org.styleru.styleruapp.model.TestProjectsModelImpl;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.view.PeopleView;
import org.styleru.styleruapp.view.ProjectsView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 29.03.17.
 */

public class PeoplePresenterImpl implements PeoplePresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private PeopleView view;
    private PeopleModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;
    private PeopleFilter filter=new PeopleFilter();

    public PeoplePresenterImpl(PeopleView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestPeopleModelImpl();
    }

    @Override
    public void onPeopleAppend(int offset, int batchSize, String requestString,PeopleFilter filter) {
        currentId=offset;
        disposable = model.getData(batchSize,currentId,requestString,filter)
                // .flatMap(Observable::from)
                //.toList()
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId+=batchSize;
    }

    @Override
    public void onPeopleUpdate(int batchSize, String requestString,PeopleFilter filter) {
        currentId=0;
        disposable = model.getData(batchSize,currentId,requestString,filter)
                //.toList()
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.setData(response.getData());
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
    public void onSetFilterMode(PeopleFilter filter) {
        this.filter=filter;
    }

    @Override
    public void onStop() {

    }

}
