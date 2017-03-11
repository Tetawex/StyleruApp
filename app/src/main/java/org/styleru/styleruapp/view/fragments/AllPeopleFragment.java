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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FRAG","people");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_allpeople, container, false);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FRAG","aLLPeopledestroy");
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
