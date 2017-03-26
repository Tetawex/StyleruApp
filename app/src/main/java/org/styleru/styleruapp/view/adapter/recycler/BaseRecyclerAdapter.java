package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tetawex on 02.03.2017.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    protected Context context;
    protected LayoutInflater inflater;
    private List<T> data= Collections.emptyList();

    public BaseRecyclerAdapter(Context context, List<T> data)
    {
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public void setDataWithNotify(List<T> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    public void appendDataWithNotify(List<T> data)
    {
        if(this.data.equals(Collections.emptyList()))
        {
            setDataWithNotify(data);
            return;
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
