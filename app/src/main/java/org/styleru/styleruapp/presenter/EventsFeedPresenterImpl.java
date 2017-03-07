package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.view.EventsView;

/**
 * Created by tetawex on 06.03.17.
 */

public class EventsFeedPresenterImpl implements EventsFeedPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private EventsView view;

    public EventsFeedPresenterImpl(EventsView view) {
        this.view=view;
    }

    @Override
    public void onStop() {

    }
}
