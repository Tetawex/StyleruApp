package org.styleru.styleruapp.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.styleru.styleruapp.R;

/**
 * Created by Пользователь on 27.03.2017.
 */

public class ProjectFragment extends Fragment {


    public ProjectFragment() {
        // Required empty public constructor
    }

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FRAG","myprofile destr");
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
