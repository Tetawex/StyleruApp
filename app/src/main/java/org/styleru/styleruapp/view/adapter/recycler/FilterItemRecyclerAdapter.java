package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.support.FilterItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class FilterItemRecyclerAdapter extends BaseRecyclerAdapter<FilterItem> {
    public FilterItemRecyclerAdapter(Context context, List<FilterItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_filter_item, parent, false);
        FilterItemViewHolder holder;
        holder = new FilterItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {

        FilterItem item = getData().get(position);
        FilterItemViewHolder holder = (FilterItemViewHolder) uncastedHolder;
        holder.title.setText(item.getName());
        if (item.isChecked())
            holder.checkbox.setVisibility(View.VISIBLE);
        else
            holder.checkbox.setVisibility(View.INVISIBLE);
        holder.ctarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setChecked(!item.isChecked());
                if (item.isChecked())
                    holder.checkbox.setVisibility(View.VISIBLE);
                else
                    holder.checkbox.setVisibility(View.INVISIBLE);
            }
        });
    }

    class FilterItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ctarget)
        View ctarget;
        @BindView(R.id.checkbox)
        View checkbox;
        @BindView(R.id.title)
        TextView title;

        public FilterItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
