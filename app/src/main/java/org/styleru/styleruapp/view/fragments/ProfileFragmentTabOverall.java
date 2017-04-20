package org.styleru.styleruapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.presenter.SingleProfilePresenter;
import org.styleru.styleruapp.presenter.SingleProfilePresenterImpl;

public class ProfileFragmentTabOverall extends Fragment {


    public ProfileFragmentTabOverall() {
        // Required empty public constructor
    }
    private SingleProfilePresenter presenter;
    Button btn;
    SingleProfilePresenterImpl item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_overall, container, false);

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        viewPager.setPadding(0,0,0,0);


        ImageView imageView = (ImageView) view.findViewById(R.id.image3);

        UserInfo info = Singletons.getUserInfo();


//        name.setText(info.getLastName());
//        presenter.onSingleProfileAppend(info.getToken(),info.getUserId());
//        surname.setText(item.surname);
//        name.setText(item.name);

//Отсюда
        //        TextView surname = (TextView) view.findViewById(R.id.name);
//        TextView name = (TextView) view.findViewById(R.id.surname);
//        Glide
//                .with(this)
//                .load(info.getImageUrl())
//                .asBitmap().centerCrop()
//                .placeholder(R.drawable.placeholder_loading_circled)
//                .into(imageView);

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("2","pause1");
//        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
//        viewPager.setPadding(0,0,0,0);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("2","resume1");
//        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
//        viewPager.setPadding(0,84,0,0);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FRAG","myprofile destr");
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
