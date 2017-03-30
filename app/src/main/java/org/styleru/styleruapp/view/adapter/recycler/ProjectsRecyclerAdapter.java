package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ProjectsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class ProjectsRecyclerAdapter extends BaseRecyclerAdapter<ProjectsItem> {
    public ProjectsRecyclerAdapter(Context context, List<ProjectsItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_projects_vol2,parent,false);
        ProjectsViewHolder  holder;
        holder = new ProjectsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        ProjectsItem item= getData().get(position);
        ProjectsViewHolder holder=(ProjectsViewHolder) uncastedHolder;

        holder.title.setText(item.getName());
    }

    class ProjectsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.proj_adm)
        TextView title;

        @BindView(R.id.proj_deadline)
        TextView subtitle;

        @BindView(R.id.proj_name)
        TextView date;

        @BindView(R.id.proj_vacation)
        TextView time;

        public ProjectsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
