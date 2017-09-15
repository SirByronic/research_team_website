package controllers;

import models.Project;
import models.ResearchArea;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by aazzou on 10/14/14.
 */
public class ResearchAreaCtrl extends Controller {
    public static void index(){
        List<ResearchArea> researchAreas = ResearchArea.all().fetch();
        renderArgs.put("researchAreas",researchAreas);
        render();
    }


    public static void details(Long id){
        ResearchArea researchArea = ResearchArea.findById(id);
        List<Project> projects = Project.find("byResearchArea",researchArea).fetch();
        renderArgs.put("researchArea",researchArea);
        renderArgs.put("projects",projects);
        render();
    }
}
