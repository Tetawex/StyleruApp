package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.FilterModelResponse;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.support.Department;
import org.styleru.styleruapp.model.dto.support.Experience;
import org.styleru.styleruapp.model.dto.support.FilterItem;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.Subdepartment;
import org.styleru.styleruapp.model.dto.support.University;
import org.styleru.styleruapp.presenter.PeoplePresenter;
import org.styleru.styleruapp.presenter.PeoplePresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.PeopleView;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.view.adapter.recycler.FilterItemRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.PeopleRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleFragment extends Fragment implements PeopleView {
    private static final int DEFAULT_BATCH_SIZE=10;
    private PeopleFragment.OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private PeopleRecyclerAdapter recyclerAdapter;
    private PeopleFilter peopleFilter=new PeopleFilter();
    private Toolbar activityToolbar;

    private PeoplePresenter presenter;

    @BindView(R.id.recycler)
    public RecyclerView recyclerView;
    //@BindView(R.id.swipe)
    //protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressbar)
    public View progressbar;
    @BindView(R.id.filter)
    public View filter;
    @BindView(R.id.filter_options)
    public View filterOptions;
    @BindView(R.id.filter_recycler)
    public RecyclerView filterRecycler;
    @BindView(R.id.filter_back)
    public ImageButton filterBack;
    @BindView(R.id.filter_recycler_back)
    public ImageButton filterRecyclerBack;
    @BindView(R.id.filter_options_title)
    public TextView filterOptionsTitle;

    @BindView(R.id.ctarget_departments)
    public Button departmentsClickTarget;
    @BindView(R.id.ctarget_subdepartments)
    public Button subdepartmentsClickTarget;
    @BindView(R.id.ctarget_universities)
    public Button universitiesClickTarget;
    @BindView(R.id.ctarget_experiences)
    public Button experiencesClickTarget;

    @BindView(R.id.departments_list)
    public TextView departmentsTextList;
    @BindView(R.id.subdepartments_list)
    public TextView subdepartmentsTextList;
    @BindView(R.id.universities_list)
    public TextView universitiesTextList;
    @BindView(R.id.experiences_list)
    public TextView experiencesTextList;

    private FilterItemRecyclerAdapter filterRecyclerAdapter;
    private FilterModelResponse filterModel=null;
    private List<FilterItem> selectedDepartments=new ArrayList<>();
    private List<FilterItem> selectedSubdepartments=new ArrayList<>();
    private List<FilterItem> selectedUniversities=new ArrayList<>();
    private List<FilterItem> selectedExperiences=new ArrayList<>();


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
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activityToolbar=toolbar;

        setHasOptionsMenu(true);

        ButterKnife.bind(this,view);
        progressbar.setVisibility(View.VISIBLE);
        filterRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        filterRecyclerAdapter=new FilterItemRecyclerAdapter(getContext(), Collections.emptyList());
        filterRecycler.setAdapter(filterRecyclerAdapter);
        //Адаптер
        //Тут можно сделать поддержку вертикальной ориентации, использовав GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Адаптер для ресайклера
        recyclerAdapter=new PeopleRecyclerAdapter(getContext(),new ArrayList<PeopleItem>());
        recyclerView.setAdapter(recyclerAdapter);

        //swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);

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
        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                presenter.onModelUpdateCachedData();
            }
        });*/
        filterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.setVisibility(View.GONE);
                activityToolbar.setVisibility(View.VISIBLE);
                List<Integer> departmentIds=new ArrayList<>();
                for (FilterItem filterItem: selectedDepartments) {
                    if(filterItem.isChecked())
                        departmentIds.add(filterItem.getId());
                }
                List<Integer> subdepartmentIds=new ArrayList<>();
                for (FilterItem filterItem: selectedSubdepartments) {
                    if(filterItem.isChecked())
                        subdepartmentIds.add(filterItem.getId());
                }
                List<Integer> universityIds=new ArrayList<>();
                for (FilterItem filterItem: selectedUniversities) {
                    if(filterItem.isChecked())
                        departmentIds.add(filterItem.getId());
                }
                List<Integer> experienceIds=new ArrayList<>();
                for (FilterItem filterItem: selectedExperiences) {
                    if(filterItem.isChecked())
                        departmentIds.add(filterItem.getId());
                }
                presenter.onSetFilter(
                        new PeopleFilter(departmentIds,
                                subdepartmentIds,universityIds,experienceIds));
            }
        });
        departmentsClickTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.VISIBLE);
                filterOptionsTitle.setText(getString(R.string.departments));
                filterRecyclerAdapter.setDataWithNotify(selectedDepartments);
            }
        });
        subdepartmentsClickTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.VISIBLE);
                filterOptionsTitle.setText(getString(R.string.subdepartments));
                filterRecyclerAdapter.setDataWithNotify(selectedSubdepartments);
            }
        });
        universitiesClickTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.VISIBLE);
                filterOptionsTitle.setText(getString(R.string.university));
                filterRecyclerAdapter.setDataWithNotify(selectedUniversities);
            }
        });
        experiencesClickTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.VISIBLE);
                filterOptionsTitle.setText(getString(R.string.experience));
                filterRecyclerAdapter.setDataWithNotify(selectedExperiences);
            }
        });
        filterRecyclerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterOptions.setVisibility(View.GONE);

                StringBuilder builder = new StringBuilder();
                int counter = 0;
                for (FilterItem item : selectedDepartments) {
                    if (item.isChecked()) {
                        builder.append(item.getName());
                        counter++;
                        builder.append(", ");
                    }
                }
                if (!(counter == 0 || counter == filterModel.getDepartments().size())){
                    builder.setLength(builder.length() - 2);
                    departmentsTextList.setText(builder.toString());
                }

                else
                    departmentsTextList.setText(R.string.all);

                counter=0;
                builder = new StringBuilder();
                    for (FilterItem item : selectedSubdepartments) {
                        if (item.isChecked()) {
                            builder.append(item.getName());
                            counter++;
                            builder.append(", ");
                        }
                    }
                if(!(counter==0||counter==filterModel.getSubdepartments().size())) {
                    builder.setLength(builder.length() - 2);
                    subdepartmentsTextList.setText(builder.toString());
                }
                else
                    subdepartmentsTextList.setText(R.string.all);

                counter=0;
                    builder = new StringBuilder();
                    for (FilterItem item : selectedUniversities) {
                        if (item.isChecked()) {
                            builder.append(item.getName());
                            counter++;
                            builder.append(", ");
                        }
                    }
                if(!(counter==0||counter==filterModel.getUniversities().size())) {
                    builder.setLength(builder.length() - 2);
                    universitiesTextList.setText(builder.toString());
                }
                else
                    universitiesTextList.setText(R.string.all);
                counter=0;
                builder = new StringBuilder();
                    for (FilterItem item : selectedExperiences) {
                        if (item.isChecked()) {
                            builder.append(item.getName());
                            counter++;
                            builder.append(", ");
                        }
                    }
                if(!(counter==0||counter==filterModel.getExperiences().size())) {
                    builder.setLength(builder.length()-2);
                    experiencesTextList.setText(builder.toString());
                }
                else
                    experiencesTextList.setText(R.string.all);
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
        inflater1.inflate(R.menu.menu_fragment_people, menu);
        MenuItem filterItem=menu.findItem(R.id.action_filter);
        filterItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onFilterModelLoad();
                return true;
            }
        });
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
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
        //swipeRefreshLayout.setRefreshing(false);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void setFilterModel(FilterModelResponse model) {
        filterModel=model;
        selectedUniversities.clear();
        selectedDepartments.clear();
        selectedSubdepartments.clear();
        selectedExperiences.clear();
        for(Department dep :filterModel.getDepartments()){
            selectedDepartments.add(dep);
        }
        for(Subdepartment sub :filterModel.getSubdepartments()){
            selectedSubdepartments.add(sub);
        }
        for(University uni :filterModel.getUniversities()){
            selectedUniversities.add(uni);
        }
        for(Experience exp :filterModel.getExperiences()){
            selectedExperiences.add(exp);
        }
        activityToolbar.setVisibility(View.GONE);
        filter.setVisibility(View.VISIBLE);
    }

    @Override
    public void appendData(List<PeopleItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }

    @Override
    public void setData(List<PeopleItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    public void onDataUpdated()
    {
        //swipeRefreshLayout.setRefreshing(false);
        recyclerViewScrollListener.resetState();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
