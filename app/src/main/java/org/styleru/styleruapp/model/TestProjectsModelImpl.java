package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.dto.ProjectsItem;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by tetawex on 29.03.17.
 */

public class TestProjectsModelImpl implements ProjectsModel {
    private TestProjectsItemFactory testProjectsItemFactory;

    public TestProjectsModelImpl() {
        testProjectsItemFactory = new TestProjectsItemFactory();
    }

    @Override
    public Observable<ProjectsResponse> getData(ProjectsRequest request) {
        if(request.getOffset()==0)
            testProjectsItemFactory.reset();
        ProjectsResponse projectsResponse=new ProjectsResponse();
        List<ProjectsItem> list=new ArrayList<ProjectsItem>();
        for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
            list.add(testProjectsItemFactory.generateRandomItem(i));
        projectsResponse.setData(list);
        Log.d("test",projectsResponse.getData().size()+"");
        return Observable.just(projectsResponse);
    }
    private class TestProjectsItemFactory{
        private Random random=new Random(12345);
        public void reset(){
            random=new Random(12345);
        }
        private ProjectsItem generateRandomItem(int id){
            ProjectsItem item=new ProjectsItem();
            if(random.nextBoolean())
                item.setName("БИ-WEB");
            else
                item.setName("StyleruApp");
            if(random.nextBoolean())
                item.setName("Сергей Мосяков");
            else
                item.setName("Ринат Зубаидулин");
            item.setId(id);

            item.setVacantPlaces(random.nextBoolean());
            item.setEndDateTime("2017-"+(random.nextInt(6)+3)+"-23T"+(random.nextInt(9)+10)+":00:43.511Z");

            return item;

        }
    }
}
