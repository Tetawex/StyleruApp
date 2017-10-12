package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.util.SparseArray;

import org.styleru.styleruapp.model.dto.support.IdItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tetawex on 05.04.2017.
 * Makes the base adapter be able to getItemById()
 */

public abstract class MappedIdRecyclerAdapter<T extends IdItem> extends BaseRecyclerAdapter<T> {
    private Map<Integer, T> idMap;

    public MappedIdRecyclerAdapter(Context context, List<T> data) {
        super(context, data);
        idMap = new HashMap<Integer, T>();
        for (T item : data) {
            idMap.put(item.getId(), item);
        }
    }

    @Override
    public void setDataWithNotify(List<T> data) {
        setData(new ArrayList<T>());
        getData().addAll(data);
        idMap.clear();
        for (T item : data) {
            idMap.put(item.getId(), item);
        }
        notifyDataSetChanged();
    }

    @Override
    public void appendDataWithNotify(List<T> data) {
        if (this.getData().equals(Collections.emptyList())) {
            setDataWithNotify(data);
            return;
        }
        getData().addAll(data);
        for (T item : data) {
            idMap.put(item.getId(), item);
        }
        notifyDataSetChanged();
    }

    public void removeItemByIdWithNotify(int id) {
        getData().remove(idMap.get(id));
        idMap.remove(id);
        notifyDataSetChanged();
    }

    public T getItemById(int id) {
        return idMap.get(id);
    }

}
