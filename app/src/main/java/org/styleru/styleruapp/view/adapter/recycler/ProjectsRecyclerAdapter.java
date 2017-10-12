package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ProjectsItem;
import org.styleru.styleruapp.view.activity.ProjectActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class ProjectsRecyclerAdapter extends BaseRecyclerAdapter<ProjectsItem> {
    private DateTimeFormatter formatter;

    public ProjectsRecyclerAdapter(Context context, List<ProjectsItem> data) {
        super(context, data);
        formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_projects_vol2, parent, false);
        ProjectsViewHolder holder;
        holder = new ProjectsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {

        ProjectsItem item = getData().get(position);
        ProjectsViewHolder holder = (ProjectsViewHolder) uncastedHolder;

        DateTime itemDateTime = new DateTime(item.getEndDateTime());
        holder.date.setText(context.getString(R.string.until) + " " + itemDateTime.toString(formatter));

        if (item.isVacantPlaces())
            holder.vacant.setVisibility(View.VISIBLE);
        else
            holder.vacant.setVisibility(View.INVISIBLE);
        holder.name.setText(item.getName());
        holder.managerName.setText(item.getManagerName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", item.getId());
                bundle.putString("name", item.getName());
                Intent intent = new Intent(context, ProjectActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    class ProjectsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.parent)
        View parent;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.vacant)
        TextView vacant;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.manager_name)
        TextView managerName;

        public ProjectsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
