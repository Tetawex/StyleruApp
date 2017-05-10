package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ParticipantsItem;
import org.styleru.styleruapp.model.dto.VacanciesItem;
import org.styleru.styleruapp.view.ProjectView;
import org.styleru.styleruapp.view.activity.ProfileActivity;
import org.styleru.styleruapp.view.activity.VacancyActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class ProjectVacanciesRecyclerAdapter extends BaseRecyclerAdapter<VacanciesItem> {
    private ProjectView projectsView;
    public ProjectVacanciesRecyclerAdapter(
            Context context, List<VacanciesItem> data,ProjectView projectView) {
        super(context, data);
        this.projectsView=projectView;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.view_project_vacancies_recycleritem,parent,false);
        ProjectVacanciesViewHolder  holder;
        holder = new ProjectVacanciesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        VacanciesItem item= getData().get(position);
        ProjectVacanciesViewHolder holder=(ProjectVacanciesViewHolder) uncastedHolder;
        holder.button.setText(item.getTitle()+" ("+item.getRequiredAmount()+")");
        holder.button.setEnabled(item.isEnabled());
        if(item.isTransferToNextPage())
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putInt("id",item.getId());
                    Intent intent = new Intent(context,VacancyActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        else
            holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    class ProjectVacanciesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button)
        AppCompatButton button;
        public ProjectVacanciesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
