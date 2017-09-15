package controllers.admin;

import models.About;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;
import tools.MyCacheSystem;

/**
 * Created by aazzou on 10/23/14.
 */
public class AboutAdminCtrl extends Controller {
    public static void index(){
        if(About.count() == 0) add();
        else{
            About about = MyCacheSystem.getAbout();
            render(about);
        }
    }
    public static void add(){
        if(About.count() > 0) index();
        else render();
    }
    public static void save(About about){
        validation.valid(about);
        if(validation.hasErrors()){
            validation.keep();
            params.flash();
            add();
        }
        about.save();
        MyCacheSystem.deleteCacheAbout();
        index();
    }

    public static void update(About about){
        validation.valid(about);
        if(validation.hasErrors()){
            validation.keep();
            params.flash();
            add();
        }
        about.save();
        MyCacheSystem.deleteCacheAbout();
        index();
    }
}
