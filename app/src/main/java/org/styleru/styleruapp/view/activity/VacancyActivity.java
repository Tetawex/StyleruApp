package org.styleru.styleruapp.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.VacanciesItem;
import org.styleru.styleruapp.model.dto.support.Request;
import org.styleru.styleruapp.presenter.VacancyPresenter;
import org.styleru.styleruapp.presenter.VacancyPresenterImpl;
import org.styleru.styleruapp.view.VacancyView;
import org.styleru.styleruapp.view.adapter.recycler.VacancyRecyclerAdapter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VacancyActivity extends AppCompatActivity implements VacancyView {
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.refresher)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.progressbar)
    protected View progressbar;

    private VacancyRecyclerAdapter recyclerAdapter;
    private VacancyPresenter presenter;
    private int vacancyId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);
        ButterKnife.bind(this);

        Bundle intentBundle = getIntent().getExtras();

        vacancyId = intentBundle.getInt("id");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onLoadVacanciesData(vacancyId);
            }
        });
        presenter = new VacancyPresenterImpl(this);
        presenter.onLoadVacanciesData(vacancyId);
        recyclerAdapter = new VacancyRecyclerAdapter(this, Collections.emptyList(), this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar.setTitle(R.string.vacancies);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void setVacanciesData(List<Request> data) {
        recyclerAdapter.setDataWithNotify(data);
    }

    @Override
    public void setPrivileges(boolean canApprove, boolean canRecommend) {
        recyclerAdapter.setPrivileges(canApprove, canRecommend);
    }

    @Override
    public void onApproveVacancy(int id, String name) {
        presenter.onApproveVacancy(id, name);
    }

    @Override
    public void onRecommendVacancy(int id, String name, boolean status) {
        presenter.onRecommendVacancy(id, name, status);
    }

    @Override
    public void removeVacancy(int id) {
        recyclerAdapter.removeItemByIdWithNotify(id);
    }

    @Override
    public void tickVacancy(int id) {
        recyclerAdapter.tickVacancyByIdWithNotify(id);
    }

    @Override
    public void notifyVacancyRecommended(String name, boolean status) {
        String msg = getString(R.string.vacancy_recommended);
        if (!status)
            msg = getString(R.string.vacancy_recommended_false);
        Toast.makeText(this, name + " " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyVacancyApproved(String name) {
        Toast.makeText(this, name + " " + getString(R.string.vacancy_approved), Toast.LENGTH_SHORT).show();
    }
}
