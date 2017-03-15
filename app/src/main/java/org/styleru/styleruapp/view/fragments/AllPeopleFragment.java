package org.styleru.styleruapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.styleruapp.R;


public class AllPeopleFragment extends Fragment {


    public AllPeopleFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_activity_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FRAG","people");
//        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_all, container, false);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FRAG","aLLPeopledestroy");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
