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

import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.presenter.EventsFeedPresenter;
import org.styleru.styleruapp.presenter.EventsFeedPresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.EventsView;
import org.styleru.styleruapp.view.adapter.recycler.EventsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A screen responsible for viewing event feed, implements corresponding interface
 */
public class EventsFragment extends Fragment implements EventsView{
    private static final int DEFAULT_BATCH_SIZE=10;//глобальные переменные - признак некачественного кода
    private OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private EventsRecyclerAdapter recyclerAdapter;

    private EventsFeedPresenter presenter;

    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipeRefreshLayout;


    public EventsFragment() {
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
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
        toolbar.setTitle("События");



        ButterKnife.bind(this,view);
        //Адаптер
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        recyclerAdapter=new EventsRecyclerAdapter(getContext(),new ArrayList<EventsItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary);

        //Добавляем листенер для ресайклера, чтобы понять, когда загружать новый фид
        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onEventsAppend(recyclerAdapter.getItemCount(),DEFAULT_BATCH_SIZE);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        //Рефреш-лэйаут сверху
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onEventsUpdate(DEFAULT_BATCH_SIZE);

            }
        });
        presenter=new EventsFeedPresenterImpl(this);
        presenter.onEventsUpdate(DEFAULT_BATCH_SIZE);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void appendData(List<EventsItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<EventsItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    @Override
    public void changeEventState(int id) {

    }

    public void onDataUpdated()
    {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }

    /*@Override
    public void onGoButtonClicked(boolean desiredStatus, int serverId, int viewId) {

    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
