package controllers;

import models.New;
import models.Section;
import play.cache.Cache;
import play.mvc.Controller;
import tools.MyCacheSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aazzou on 10/16/14.
 */
public class NewCtrl extends Controller {
    public static void index(){
        List<Section> sections = MyCacheSystem.getSections();
        List<New> news = MyCacheSystem.getNews();
        render(news,sections);
    }
    public static void details(Long id){
        List<Section> sections = MyCacheSystem.getSections();
        New _new = MyCacheSystem.getNew(id);
        render(_new,sections);
    }
    public static void bySection(Long id){
        List<Section> sections = MyCacheSystem.getSections();
        Section selectedSection = MyCacheSystem.getSection(id);
        render(sections,selectedSection);
    }
}
