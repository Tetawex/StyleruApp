package org.styleru.styleruapp.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 19.03.2017.
 */

public class ProfileFragmentTabProjects extends Fragment {


    public ProfileFragmentTabProjects() {
        // Required empty public constructor
    }
TextView date1;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_projects, container, false);

        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("FRAG","Peopledestroy");
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
