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

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.joda.time.DateTime;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.presenter.EventsPresenter;
import org.styleru.styleruapp.presenter.EventsPresenterImpl;
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

    private EventsPresenter presenter;
    private ToolbarInteractor toolbarInteractor;

    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipyRefreshLayout swipeRefreshLayout;
    //protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    protected View progressbar;


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
        toolbarInteractor=(ToolbarInteractor)getActivity();
        toolbarInteractor.setToolbarTitleMode(ToolbarInteractor.Mode.BASIC);
        toolbarInteractor.setToolbarElevationDp(4);
        toolbarInteractor.setToolbarTitle(getString(R.string.events));
        ButterKnife.bind(this,view);
        progressbar.setVisibility(View.VISIBLE);
        //Адаптер
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        //Адаптер для ресайклера
        recyclerAdapter=new EventsRecyclerAdapter(getContext(),new ArrayList<EventsItem>(),this);
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);

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
        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onDataUpdate(DEFAULT_BATCH_SIZE);

            }
        });*/
        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                presenter.onDataUpdate(DEFAULT_BATCH_SIZE);
            }
        });
        presenter=new EventsPresenterImpl(this);
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
        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressbar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void appendData(List<EventsItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<EventsItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
        for (int i=0;i<data.size();i++){
            if(DateTime.now().isAfter(new DateTime(data.get(i).getDateTime().replace(' ','T')))) {
                recyclerView.scrollToPosition(i);
                return;
            }
        }
    }

    @Override
    public void changeEventStateSuccess(int id,int state) {
        recyclerAdapter.getItemById(id).setState(state);
        recyclerAdapter.notifyItemChanged(
                recyclerAdapter.getData().indexOf(recyclerAdapter.getItemById(id)));
        int strId=R.string.event_signed;
        if(state==0)
            strId=R.string.event_unsigned;
        Toast.makeText(getContext(), getString(strId)+" "+recyclerAdapter.getItemById(id).getTitle(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void changeEventStateFail(int id) {
        EventsItem item=recyclerAdapter.getItemById(id);
        item.setState(item.getState()+2);
        recyclerAdapter.notifyItemChanged(
                recyclerAdapter.getData().indexOf(recyclerAdapter.getItemById(id)));
        Toast.makeText(getContext(), R.string.err_event_state_change_fail,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void requestChangeEventState(int id) {
        presenter.onEventStateChange(id);
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
