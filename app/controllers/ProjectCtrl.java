package controllers;

import models.Project;
import models.ResearchArea;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by aazzou on 10/15/14.
 */
public class ProjectCtrl extends Controller{
    public static void index(){
        List<Project> projects = Project.all().fetch();
        List<ResearchArea> researchAreas = ResearchArea.all().fetch();
        renderArgs.put("projects",projects);
        renderArgs.put("researchAreas",researchAreas);
        render();
    }

    public static void byResearchArea(Long id){
        ResearchArea selectedResearchArea = ResearchArea.findById(id);
        List<ResearchArea> researchAreas = ResearchArea.all().fetch();
        List<Project> projects = Project.find("byResearchArea",selectedResearchArea).fetch();

        render(selectedResearchArea,projects,researchAreas);
    }
    public static void details(Long id){
        Project project = Project.findById(id);

        renderArgs.put("project",project);
        render();
    }
}
