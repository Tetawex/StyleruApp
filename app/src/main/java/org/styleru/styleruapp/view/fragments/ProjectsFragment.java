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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.ProjectsItem;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;
import org.styleru.styleruapp.presenter.PeoplePresenter;
import org.styleru.styleruapp.presenter.PeoplePresenterImpl;
import org.styleru.styleruapp.presenter.ProjectsPresenter;
import org.styleru.styleruapp.presenter.ProjectsPresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.PeopleView;
import org.styleru.styleruapp.view.ProjectsView;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.view.adapter.recycler.PeopleRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.ProjectsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsFragment extends Fragment implements ProjectsView {
    private static final int DEFAULT_BATCH_SIZE = 10;
    private ProjectsFragment.OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private ProjectsRecyclerAdapter recyclerAdapter;

    private ProjectsFilter filter = new ProjectsFilter();
    private String[] spinnerOptions;

    private ProjectsPresenter presenter;

    @BindView(R.id.recycler)
    public RecyclerView recyclerView;
    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    public View progressbar;

    private ToolbarInteractor toolbarInteractor;
    private MenuItem searchItem;

    public ProjectsFragment() {
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
        toolbarInteractor = (ToolbarInteractor) getActivity();
        toolbarInteractor.setToolbarTitleMode(ToolbarInteractor.Mode.SPINNER);
        toolbarInteractor.setToolbarElevationDp(4);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getContext(), R.array.project_options, R.layout.view_toolbar_title_mimic);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbarInteractor.setToolbarSpinnerAdapter(adapter);
        toolbarInteractor.setToolbarSpinnerListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onSetFilter(new ProjectsFilter(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                presenter.onSetFilter(new ProjectsFilter());
            }
        });
        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);
        progressbar.setVisibility(View.VISIBLE);
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        recyclerAdapter = new ProjectsRecyclerAdapter(getContext(), new ArrayList<ProjectsItem>());
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);

        //Добавляем листенер для ресайклера, чтобы понять, когда загружать новый фид
        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onDataAppend(recyclerAdapter
                        .getItemCount(), DEFAULT_BATCH_SIZE);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        //Рефреш-лэйаут сверху
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchItem.collapseActionView();
                presenter.onModelUpdateCachedData();
            }
        });
        presenter = new ProjectsPresenterImpl(this);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_projects, menu);
        searchItem = menu.findItem(R.id.action_search);
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
        searchItem.getIcon().setAlpha(138);
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void doQuery(String query) {
        presenter.onSetRequestString(query.trim());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void appendData(List<ProjectsItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<ProjectsItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    public void onDataUpdated() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
