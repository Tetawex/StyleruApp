package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ProjectResponse;
import org.styleru.styleruapp.presenter.ProjectPresenter;
import org.styleru.styleruapp.presenter.ProjectPresenterImpl;
import org.styleru.styleruapp.view.ProjectView;
import org.styleru.styleruapp.view.adapter.recycler.ProjectParticipantsRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.ProjectVacanciesRecyclerAdapter;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 27.03.2017.
 */

public class ProjectActivity extends AppCompatActivity implements ProjectView{
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.text_manager_name)
    public TextView textManagerName;
    @BindView(R.id.text_end)
    public TextView textDateEnd;
    @BindView(R.id.text_start)
    public TextView textDateStart;
    @BindView(R.id.refresher)
    public SwipeRefreshLayout refresher;
    @BindView(R.id.recycler_participants)
    public RecyclerView participantsRecycler;
    @BindView(R.id.recycler_vacancies)
    public RecyclerView vacanciesRecycler;
    @BindView(R.id.progressbar_completion)
    public DonutProgress completionProgressbar;
    @BindView(R.id.progressbar)
    public View progressbar;

    private ProjectParticipantsRecyclerAdapter participantsRecyclerAdapter;
    private ProjectVacanciesRecyclerAdapter vacanciesRecyclerAdapter;

    private DateTimeFormatter formatter= DateTimeFormat.forPattern("dd.MM.yyyy");
    private int id;
    private ProjectPresenter presenter;

    private ProjectResponse response;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);
        startProgressBar();
        
        toolbar.setTitle(getIntent().getExtras().getString("name"));
        id=getIntent().getExtras().getInt("id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        participantsRecyclerAdapter=new ProjectParticipantsRecyclerAdapter(this, Collections.emptyList());
        vacanciesRecyclerAdapter=new ProjectVacanciesRecyclerAdapter(this, Collections.emptyList(),this);
        participantsRecycler.setLayoutManager(new LinearLayoutManager(this));
        vacanciesRecycler.setLayoutManager(new LinearLayoutManager(this));
        participantsRecycler.setAdapter(participantsRecyclerAdapter);
        vacanciesRecycler.setAdapter(vacanciesRecyclerAdapter);

        refresher.setColorSchemeResources(R.color.colorPrimaryDark);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onLoadData(id);
            }
        });
        presenter=new ProjectPresenterImpl(this);
        presenter.onLoadData(id);
    }
    @Override
    public void inflateData(ProjectResponse response){
        toolbar.setTitle(response.getName());
        textDateEnd.setText(new DateTime(response.getDateTimeEnd()).toString(formatter));
        textDateStart.setText(new DateTime(response.getDateTimeStart()).toString(formatter));
        participantsRecyclerAdapter.setDataWithNotify(response.getParticipants());
        vacanciesRecyclerAdapter.setDataWithNotify(response.getVacancies());
        completionProgressbar.setProgress(response.getCompletion());

        textManagerName.setText(response.getManagerName());
        textManagerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("id",response.getManagerId());
                bundle.putString("name",response.getManagerName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        refresher.setRefreshing(false);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void requestVacancy(int id,boolean accepted) {
        presenter.onRequestVacancy(id,accepted);
    }
    @Override
    public void notifyVacancyRequestCompleted(boolean accepted){
        if(accepted)
            Toast.makeText(this, R.string.vacancy_request_submitted,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.vacancy_request_removed,Toast.LENGTH_SHORT).show();
    }
}








