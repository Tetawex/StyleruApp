package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.TimelineItem;
import org.styleru.styleruapp.presenter.TimelineProfilePresenter;
import org.styleru.styleruapp.view.adapter.recycler.TimelineRecyclerAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 19.03.2017.
 */

public class ProfileFragmentTabTimeline extends Fragment {


    public ProfileFragmentTabTimeline() {
        // Required empty public constructor
    }
    private TimelineRecyclerAdapter recyclerAdapter;
    private OnFragmentInteractionListener mListener;
    private TimelineProfilePresenter presenter;
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.today_date)
    protected TextView date1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_timeline, container, false);
        ButterKnife.bind(this,view);
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        viewPager.setPadding(0,0,0,0);
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        if (mm<=8) {
            date1.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(dd).append(".0").append(mm + 1).append(".").append(yy));
        }
        else {
            date1.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(dd).append(".").append(mm + 1).append(".").append(yy));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter=new  TimelineRecyclerAdapter(getActivity(),new ArrayList<TimelineItem>());

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FRAG","Peopledestroy");
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("2","pause3");
//        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
//        viewPager.setPadding(0,0,0,0);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("2","resume3");
//        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
//        viewPager.setPadding(0,84,0,0);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
