package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.TimelineItem;
import org.styleru.styleruapp.presenter.TimelinePresenter;
import org.styleru.styleruapp.presenter.TimelinePresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.TimelineView;
import org.styleru.styleruapp.view.adapter.recycler.TimelineRecyclerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 19.03.2017.
 */

public class ProfileFragmentTabTimeline extends Fragment implements TimelineView {


    public ProfileFragmentTabTimeline() {
        // Required empty public constructor
    }
    private static final int DEFAULT_BATCH_SIZE=10;
    private TimelineRecyclerAdapter recyclerAdapter;
    private OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private TimelinePresenter presenter;
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.date)
    protected TextView date1;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout  swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_timeline, container, false);
        ButterKnife.bind(this,view);
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        viewPager.setPadding(0,0,0,0);
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        if (mm<=8) {
            date1.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(dd).append(".0").append(mm + 1).append(".").append(yy));
        }
        else {
            date1.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(dd).append(".").append(mm + 1).append(".").append(yy));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter=new TimelineRecyclerAdapter(getActivity(),new ArrayList<TimelineItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onTimelineAppend(recyclerAdapter.getItemCount(),DEFAULT_BATCH_SIZE);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onTimelineUpdate(DEFAULT_BATCH_SIZE);

            }
        });
        presenter=new TimelinePresenterImpl(this);
        presenter.onTimelineUpdate(DEFAULT_BATCH_SIZE);
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

    }

    @Override
    public void stopProgressBar() {

    }


    @Override
    public void appendData(List<TimelineItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<TimelineItem> data) {
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


