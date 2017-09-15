package controllers.admin;

import com.cloudinary.Cloudinary;
import models.Member;
import models.Project;
import models.ResearchArea;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;
import tools.MyCacheSystem;
import tools.cloudinary.CloudImgUploader;

import java.io.File;
import java.util.*;

/**
 * Created by aazzou on 10/18/14.
 */
public class ProjectAdminCtrl extends Controller {
    public static void details(Long id){
        Project project = MyCacheSystem.getProject(id);
        render(project);
    }


    public static void add(){
        List<ResearchArea> researchAreas = MyCacheSystem.getResearchAreas();
        List<Member> members = MyCacheSystem.getMembers();
        render(researchAreas,members);
    }


    public static void edit(Long id){
        Project project = MyCacheSystem.getProject(id);
        List<ResearchArea> researchAreas = MyCacheSystem.getResearchAreas();
        List<Member> members = MyCacheSystem.getMembers();
        render(project,researchAreas,members);
    }


    public static void save(Project project,
                            File img,
                            List<Long> memberIds,
                            Long researchAreaId){

        validation.valid(project);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            add();
        }

        if(Project.find("byName",project.name).first() != null){
            Validation.addError("name",project.name + " already exist !");
            params.flash();
            validation.keep();
            add();
        }


        project.researchArea = ResearchArea.findById(researchAreaId);
        project.members = new ArrayList<Member>();
        if(memberIds != null){
            for(Long id: memberIds){
                project.members.add((Member) Member.findById(id));
            }
        }else{
            project.members = null;
        }
        if(img != null){
            // Img processing
            CloudImgUploader uploader = new CloudImgUploader(img,null);
            project.imgURL = uploader.url();
            project.imgID = uploader.publicID();
        }
        project.save();
        MyCacheSystem.deleteProjectAttachedCache(project);
        ResearchAreaAdminCtrl.index();
    }


    public static void update(Project project,
                              File img,
                              List<Long> memberIds,
                              Long researchAreaId){
        validation.valid(project);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            edit(project.id);
        }

        Project pj = Project.find("byName",project.name).first();
        if(pj != null && pj.id != project.id){
            Validation.addError("name",project.name + " already exist !");
            params.flash();
            validation.keep();
           edit(project.id);
        }

        project.researchArea = ResearchArea.findById(researchAreaId);
        project.members = new ArrayList<Member>();
        if(memberIds != null){
            for(Long idMember: memberIds){
                project.members.add((Member) Member.findById(idMember));
            }
        }else{
            project.members = null;
        }

        if(img != null){
            Map parameters = null;
            if(project.imgID != null) parameters =  Cloudinary.asMap("public_id", project.imgID);
            CloudImgUploader uploader = new CloudImgUploader(img,parameters);
            project.imgURL = uploader.url();
            project.imgID = uploader.publicID();
        }
        project.save();
        MyCacheSystem.deleteProjectAttachedCache(project);
        ResearchAreaAdminCtrl.index();
    }
    public static void delete(Long id){
        Map<String, String> result = new HashMap<String, String>();
        Project project = Project.findById(id);
        try {
            project.members = null;
            project.delete();
            MyCacheSystem.deleteProjectAttachedCache(project);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",project.name + " deleted!");
        renderJSON(result);
    }

}
