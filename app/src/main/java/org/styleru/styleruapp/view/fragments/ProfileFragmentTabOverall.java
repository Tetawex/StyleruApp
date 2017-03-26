package org.styleru.styleruapp.view.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.styleru.styleruapp.R;

public class ProfileFragmentTabOverall extends Fragment {


    public ProfileFragmentTabOverall() {
        // Required empty public constructor
    }

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_overall, container, false);
        btn = (Button) view.findViewById(R.id.but_change);
        btn.setTextColor(Color.rgb(58,81,85));
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FRAG","myprofile destr");
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
