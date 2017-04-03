package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.dto.ProfileProjectsItem;
import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class TestProfileProjectsModelImpl implements ProfileProjectsModel {
    private TestProfileProjectsItemFactory testProfileProjectsItemFactory;

    public TestProfileProjectsModelImpl() {
        testProfileProjectsItemFactory = new TestProfileProjectsItemFactory();
    }

    @Override
    public Observable<ProfileProjectsResponse> getData(ProfileProjectsRequest request) {
        if(request.getOffset()==0)
            testProfileProjectsItemFactory.reset();
        ProfileProjectsResponse profileProjectsResponse=new ProfileProjectsResponse();
        List<ProfileProjectsItem> list=new ArrayList<ProfileProjectsItem>();
        for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
            list.add(testProfileProjectsItemFactory.generateRandomItem(i));
        profileProjectsResponse.setData(list);
        Log.d("test",profileProjectsResponse.getData().size()+"");
        return Observable.just(profileProjectsResponse);
    }

    @Override
    public Observable<ProfileProjectsStateChangeResponse> getChangedState(ProfileProjectsStateChangeRequest request) {
        return null;
    }

    private class TestProfileProjectsItemFactory{
        private Random random=new Random(12345);
        public void reset(){
            random=new Random(12345);
        }
        private ProfileProjectsItem generateRandomItem(int id){
            ProfileProjectsItem item=new ProfileProjectsItem();
            item.setTitle("Это БИ-WEB");
            item.setRole("Дизайнер");
            item.setStatus("Активно");

            return item;

        }
    }
}