package controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import models.Member;
import models.MemberCategory;
import models.Project;
import models.ResearchArea;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;
import tools.MyCacheSystem;
import tools.cloudinary.CloudImgUploader;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aazzou on 10/18/14.
 */
public class ResearchAreaAdminCtrl extends Controller {
    public static void index(){
        List<ResearchArea> researchAreas = MyCacheSystem.getResearchAreas();
        List<Project> projects = MyCacheSystem.getProjects();
        Map<Long, Long> catCount = MyCacheSystem.getProjectsPerResAreaCount(researchAreas);
        render(researchAreas, projects,catCount);
    }
    public static void details(Long id){
        ResearchArea researchArea = MyCacheSystem.getResearchArea(id);
        render(researchArea);
    }
    public static void add(){
        render();
    }
    public static void save(ResearchArea researchArea){
        validation.valid(researchArea);
        if(validation.hasErrors()){
            params.flash();
            validation.keep();
            add();
        }

        if(ResearchArea.find("byName",researchArea.name).first() != null){
            Validation.addError("researchArea.name",researchArea.name + " already exist !");
            params.flash();
            validation.keep();
            add();
        }

        if(params.get("img",File.class) != null){
            // Img processing
            CloudImgUploader uploader = new CloudImgUploader(params.get("img",File.class),null);
            researchArea.imgURL = uploader.url();
            researchArea.imgID = uploader.publicID();
        }else researchArea.imgURL = null;

        researchArea.save();
        MyCacheSystem.deleteResearchAreaAttachedCache(researchArea);
        index();
    }

    public static void edit(Long id){
        ResearchArea researchArea = ResearchArea.findById(id);
        render(researchArea);
    }

    public static void update(ResearchArea researchArea){
        validation.valid(researchArea);
        if(validation.hasErrors()){
            params.flash();
            validation.keep();
            edit(researchArea.id);
        }

        ResearchArea res = ResearchArea.find("byName",researchArea.name).first();
        if(res != null && res.id != researchArea.id){
            Validation.addError("researchArea.name",researchArea.name + " already exist !");
            params.flash();
            validation.keep();
            edit(researchArea.id);
        }

        if(params.get("img",File.class) != null){
            // Img processing
            Map parameters = null;
            if(researchArea.imgID != null)
                parameters =  Cloudinary.asMap("public_id",researchArea.imgID);
            CloudImgUploader uploader = new CloudImgUploader(params.get("img",File.class),parameters);
            researchArea.imgURL = uploader.url();
            researchArea.imgID = uploader.publicID();
        }

        researchArea.save();
        MyCacheSystem.deleteResearchAreaAttachedCache(researchArea);
        index();
    }

    public static void delete(Long id){
        Map<String, String> result = new HashMap<String, String>();
        ResearchArea researchArea = ResearchArea.findById(id);
        try {
            researchArea.delete();
            MyCacheSystem.deleteResearchAreaAttachedCache(researchArea);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",researchArea.name + " deleted!");
        renderJSON(result);
    }
    private  static void deleteCache(){
        Cache.delete("researchAreas");
    }
}
