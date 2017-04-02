package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.presenter.PeoplePresenter;
import org.styleru.styleruapp.presenter.PeoplePresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.PeopleView;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.view.adapter.recycler.PeopleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleFragment extends Fragment implements PeopleView {
    private static final int DEFAULT_BATCH_SIZE=10;//глобальные переменные - признак плохого кода
    private PeopleFragment.OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private PeopleRecyclerAdapter recyclerAdapter;
    private PeopleFilter filter=new PeopleFilter();

    private PeoplePresenter presenter;

    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    protected View progressbar;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance() {
        PeopleFragment fragment = new PeopleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().show();


        toolbar.setTitle(getString(R.string.people));
        setHasOptionsMenu(true);

        ButterKnife.bind(this,view);
        progressbar.setVisibility(View.VISIBLE);
        //Адаптер
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        recyclerAdapter=new PeopleRecyclerAdapter(getContext(),new ArrayList<PeopleItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary);

        //Добавляем листенер для ресайклера, чтобы понять, когда загружать новый фид
        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onDataAppend(recyclerAdapter
                        .getItemCount(),DEFAULT_BATCH_SIZE);
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
        presenter=new PeoplePresenterImpl(this);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MainActivity activity = (MainActivity) getActivity();
        MenuInflater inflater1 = activity.getMenuInflater();
        inflater1.inflate(R.menu.menu_activity_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(40000);
        searchView.setQueryHint(getString(R.string.hint_people));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doQuery(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void doQuery(String query){
        presenter.onSetRequestString(query.trim());
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
    }

    @Override
    public void appendData(List<PeopleItem> data) {
        Log.e("appended",""+data.size());
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<PeopleItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    public void onDataUpdated()
    {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
