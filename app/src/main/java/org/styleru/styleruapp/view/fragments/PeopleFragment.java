package org.styleru.styleruapp.view.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.view.adapter.tab.ViewPagerAdapter2;

public class PeopleFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    ProfileFragmentTabOverall frag2;
    FragmentTransaction fTrans;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter2 viewPagerAdapter;
    private OnFragmentInteractionListener mListener;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance(String param1, String param2) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
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

        View view = inflater.inflate(R.layout.fragment_people, container, false);
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle("Люди");
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here

        Log.d("FRAME","1");
        MainActivity activity = (MainActivity) getActivity();
        MenuInflater inflater1 = activity.getMenuInflater();
        Log.d("FRAME","1");
        inflater1.inflate(R.menu.menu_activity_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        Log.d("FRAME","1");
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //Да, именно так и нужно работать с лэйаутами в андроиде
        searchView.setMaxWidth(40000);
        SearchManager searchManager = (SearchManager) getActivity()
                .getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getActivity().getComponentName()) );
        super.onCreateOptionsMenu(menu, inflater);
    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("FRAG","desview2");
    //    getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    //    tabLayout.setupWithViewPager(viewPager);

    //
    }


//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
