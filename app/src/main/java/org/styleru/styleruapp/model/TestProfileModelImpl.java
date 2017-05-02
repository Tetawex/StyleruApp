package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProfileProjectsItem;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileRequest;

import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */
//
//public class TestProfileModelImpl implements ProfileModel {
//    private TestProfileProjectsItemFactory testProfileProjectsItemFactory;
//
//    public TestProfileModelImpl() {
//        testProfileProjectsItemFactory = new TestProfileProjectsItemFactory();
//    }
//
////    @Override
////    public Observable<ProfileProjectsResponse> getData(ProfileRequest request) {
////        if(request.getOffset()==0)
////            testProfileProjectsItemFactory.reset();
////        ProfileProjectsResponse profileProjectsResponse =new ProfileProjectsResponse();
////        List<ProfileProjectsItem> list=new ArrayList<ProfileProjectsItem>();
////        for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
////            list.add(testProfileProjectsItemFactory.generateRandomItem(i));
////        profileProjectsResponse.setData(list);
////        Log.d("test", profileProjectsResponse.getData().size()+"");
////        return Observable.just(profileProjectsResponse);
////    }
//
////    @Override
////    public Observable<ProfileProjectsResponse> getData(int offset, int batchSize) {
////        return null;
////    }
//
//    @Override
//    public Observable<ProfileProjectsResponse> getData(ProfileRequest request) {
//        return null;
//    }
//
//
//    private class TestProfileProjectsItemFactory{
//        private Random random=new Random(12345);
//        public void reset(){
//            random=new Random(12345);
//        }
//        private ProfileProjectsItem generateRandomItem(int id){
//            ProfileProjectsItem item=new ProfileProjectsItem();
//            item.setTitle("Это БИ-WEB");
//            item.setRole("Дизайнер");
//            item.setStatus("Активно");
//            item.setDate_finish("25.06.12");
//            return item;
//
//        }
//    }
//}