package org.styleru.styleruapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DepartmentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DepartmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DepartmentsFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;

    private OnFragmentInteractionListener mListener;

    public DepartmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DirectionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DepartmentsFragment newInstance(String param1, String param2) {
        DepartmentsFragment fragment = new DepartmentsFragment();
        Bundle args = new Bundle();
        args  .putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        View view = inflater.inflate(R.layout.fragment_departments, container, false);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar) ;
////        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Направления");
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//
//        activity.setSupportActionBar(toolbarInstance);
        Log.d("FRAME","2");
        View view = inflater.inflate(R.layout.fragment_departments, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().show();


        toolbar.setTitle("Направления");
        Log.d("FRAME","3");
        //set toolbar appearance

        //for crate home button
        Log.d("FRAME","4");




        return view;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//@Override
//public boolean onOptionsItemSelected(MenuItem item) {
//
//    return super.onOptionsItemSelected(item);
//}
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {

//    switch (item.getItemId()) {
//        case R.id.:
//            return true;
//
//
//
//        case R.id.action_search:
//            return true;
//

//    }

    return super.onOptionsItemSelected(item);
}


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

Log.d("FRAME","1");
//        MainActivity activity = (MainActivity) getActivity();
//        MenuInflater inflater1 = activity.getMenuInflater();;
//        Log.d("FRAME","1");
//        inflater1.inflate(R.menu.main, menu);
////        MenuItem searchItem = menu.findItem(R.id.action_search);
//        Log.d("FRAME","1");
////        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
////        searchView.setQueryHint("Поиск");
        super.onCreateOptionsMenu(menu, inflater);
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getActivity().getMenuInflater();
//        inflater.inflate(R.menu.menu_activity_main, menu);
//        return true;
//    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
