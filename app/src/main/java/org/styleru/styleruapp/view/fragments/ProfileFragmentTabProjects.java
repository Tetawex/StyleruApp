package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ProfileProjectsItem;
import org.styleru.styleruapp.presenter.ProfileProjectsPresenter;
import org.styleru.styleruapp.presenter.ProfileProjectsPresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.ProfileProjectsView;
import org.styleru.styleruapp.view.adapter.recycler.ProfileProjectsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A screen responsible for viewing event feed, implements corresponding interface
 */
public class ProfileFragmentTabProjects extends Fragment {
    private static final int DEFAULT_BATCH_SIZE = 10;
    private OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private ProfileProjectsRecyclerAdapter recyclerAdapter;

    private ProfileProjectsPresenter presenter;

    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    protected View progressbar;


    public ProfileFragmentTabProjects() {
    }

    public static ProfileFragmentTabProjects newInstance() {
        ProfileFragmentTabProjects fragment = new ProfileFragmentTabProjects();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_profile_projects, container, false);


        ButterKnife.bind(this, view);
        progressbar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter = new ProfileProjectsRecyclerAdapter(getActivity(), new ArrayList<ProfileProjectsItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary);

        recyclerView.addOnScrollListener(recyclerViewScrollListener);
//        presenter=new ProfileProjectsPresenterImpl(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public void showError(Throwable throwable) {
//        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT);
//    }
//
//    @Override
//    public void startProgressBar() {
//
//    }
//
//    @Override
//    public void stopProgressBar() {
//
//    }
//
//
//    @Override
//    public void appendData(List<ProfileProjectsItem> data) {
//        recyclerAdapter.appendDataWithNotify(data);
//    }
//
//


    public void onDataUpdated() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
