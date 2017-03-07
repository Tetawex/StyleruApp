package org.styleru.styleruapp.view;

/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsView extends View{
    void appendData(int offset,int batchSize);
    void updateData(int batchSize);
}
