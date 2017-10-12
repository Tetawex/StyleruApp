package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ProjectVacanciesRecyclerAdapter extends MappedIdRecyclerAdapter<VacanciesItem> {
    private ProjectView projectsView;

    public boolean isCanViewSubmissions() {
        return canViewSubmissions;
    }

    public void setCanViewSubmissions(boolean canViewSubmissions) {
        this.canViewSubmissions = canViewSubmissions;
    }

    private boolean canViewSubmissions;

    public ProjectVacanciesRecyclerAdapter(
            Context context, List<VacanciesItem> data, ProjectView projectView) {
        super(context, data);
        this.projectsView = projectView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_project_vacancies_recycleritem, parent, false);
        ProjectVacanciesViewHolder holder;
        holder = new ProjectVacanciesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        VacanciesItem item = getData().get(position);
        ProjectVacanciesViewHolder holder = (ProjectVacanciesViewHolder) uncastedHolder;
        holder.button.setText(item.getTitle() + " (" + item.getRequiredAmount() + ")");
        if (item.isEnabled())
            holder.indicator.setVisibility(View.VISIBLE);
        else
            holder.indicator.setVisibility(View.GONE);
        if (canViewSubmissions)
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", item.getId());
                    Intent intent = new Intent(context, VacancyActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        else if (item.isTransferToNextPage())
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projectsView.requestVacancy(item.getId(), item.isEnabled());
                }
            });
        else
            holder.button.setEnabled(false);
    }

    public void tickVacancyByIdWithNotify(int id) {
        VacanciesItem r = getItemById(id);
        r.setEnabled(!r.isEnabled());
        notifyItemChanged(getData().indexOf(r));
    }

    class ProjectVacanciesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button)
        AppCompatButton button;
        @BindView(R.id.submitted_indicator)
        ImageView indicator;

        public ProjectVacanciesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
