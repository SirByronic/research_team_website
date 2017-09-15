package controllers;

import models.*;
import play.mvc.*;
import tools.MyCacheSystem;

import java.util.List;


public class Application extends Controller {

    public static void index() {
        List<Member> members = MyCacheSystem.getLastMembers(3);
        List<Project> projects = MyCacheSystem.getLastProjects(3);
        List<New> news = MyCacheSystem.getLastNews(3);
        List<Publication> publications = MyCacheSystem.getLastPublications(3);
        About about = MyCacheSystem.getAbout();
        // render args
        renderArgs.put("members",members);
        renderArgs.put("projects",projects);
        renderArgs.put("news",news);
        renderArgs.put("publications",publications);
        renderArgs.put("about",about);

        render();
    }

    public static void about(){
        About about = About.all().first();
        if(about == null) index();
        render(about);
    }
}