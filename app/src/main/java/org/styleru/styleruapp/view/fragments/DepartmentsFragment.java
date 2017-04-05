package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.DepartmentsItem;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.presenter.DepartmentsPresenter;
import org.styleru.styleruapp.presenter.DepartmentsPresenterImpl;
import org.styleru.styleruapp.presenter.EventsPresenter;
import org.styleru.styleruapp.presenter.EventsPresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.DepartmentsView;
import org.styleru.styleruapp.view.EventsView;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.view.adapter.recycler.DepartmentsRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.EventsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A screen responsible for viewing event feed, implements corresponding interface
 */
public class DepartmentsFragment extends Fragment implements DepartmentsView{
    private static final int DEFAULT_BATCH_SIZE=10;
    private OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private DepartmentsRecyclerAdapter recyclerAdapter;

    private DepartmentsPresenter presenter;

    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    protected View progressbar;


    public DepartmentsFragment() {
    }

    public static DepartmentsFragment newInstance() {
        DepartmentsFragment fragment = new DepartmentsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        View view=inflater.inflate(R.layout.fragment_events, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().show();
        toolbar.setTitle(R.string.departments);


        ButterKnife.bind(this,view);
        progressbar.setVisibility(View.VISIBLE);
        //Адаптер
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        recyclerAdapter=new DepartmentsRecyclerAdapter(getContext(),new ArrayList<DepartmentsItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary);

        //Добавляем листенер для ресайклера, чтобы понять, когда загружать новый фид
        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onDataAppend(recyclerAdapter.getItemCount(),DEFAULT_BATCH_SIZE);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        //Рефреш-лэйаут сверху
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onDataUpdate(DEFAULT_BATCH_SIZE);

            }
        });
        presenter=new DepartmentsPresenterImpl(this);
        presenter.onDataUpdate(DEFAULT_BATCH_SIZE);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT);
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void appendData(List<DepartmentsItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<DepartmentsItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    public void onDataUpdated()
    {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
