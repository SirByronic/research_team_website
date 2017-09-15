package controllers.admin;

import controllers.Secure;
import models.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;
import tools.MyCacheSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aazzou on 10/17/14.
 */
@With(Secure.class)
public class AdminCtrl extends Controller {
    public static void index(){
        // 5 last members (researchers)
        List<Member> lastMembers = MyCacheSystem.getLastMembers(5);
        // 5 last research areas
        List<ResearchArea> lastResearchAreas = MyCacheSystem.getLastResearchAreas(5);
        // 5 last projects
        List<Project> lastProjects = MyCacheSystem.getLastProjects(5);
        // 5 last publications

        List<Publication> lastPublications = MyCacheSystem.getLastPublications(5);
        // 5 last news
        List<New> lastNews = MyCacheSystem.getLastNews(5);
        // 5 last events
        List<Event> lastEvents = MyCacheSystem.getLastEvents(5);
        // Statistics
        Map<String, Long> statistics = MyCacheSystem.getStatistics();

        renderArgs.put("lastMembers",lastMembers);
        renderArgs.put("lastResearchAreas",lastResearchAreas);
        renderArgs.put("lastProjects",lastProjects);
        renderArgs.put("lastPublications",lastPublications);
        renderArgs.put("lastNews",lastNews);
        renderArgs.put("lastEvents",lastEvents);

        renderArgs.put("statistics",statistics);
        render();
    }



}
