package controllers.admin;

import models.Member;
import models.Project;
import models.Publication;
import models.ResearchArea;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;
import tools.MyCacheSystem;

import java.util.*;

/**
 * Created by aazzou on 10/18/14.
 */
public class PublicationAdminCtrl extends Controller {
    public static void index(){
        List<Publication> publications = MyCacheSystem.getPublications();
        render(publications);
    }


    public static void details(Long id){
        Publication publication = MyCacheSystem.getPublication(id);
        render(publication);
    }

    public static void add(){
        List<Member> members = MyCacheSystem.getMembers();
        render(members);
    }


    public static void edit(Long id){
        Publication publication = MyCacheSystem.getPublication(id);
        List<Member> members = MyCacheSystem.getMembers();
        render(publication,members);
    }


    public static void save(Publication publication,
                            List<Long> memberIds){
        validation.valid(publication);
        Validation.required("memberIds",memberIds).message("The publication must have researchers");

        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            add();
        }

        if(Publication.find("byTitle",publication.title).first() != null){
            Validation.addError("title",publication.title + " already exist!");
            params.flash();
            validation.keep();
            add();
        }

        publication.members = new ArrayList<Member>();

        if(memberIds != null){
            for(Long id: memberIds){
                publication.members.add((Member) Member.findById(id));
            }
        }else{
            publication.members = null;
        }
        publication.save();
        MyCacheSystem.deletePublicationAttachedCache(publication);
        index();
    }


    public static void update(Publication publication,
                              List<Long> memberIds){
        validation.valid(publication);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            edit(publication.id);
        }
        Publication p2 = Publication.find("byTitle",publication.title).first();
        if(p2!=null && p2.id != publication.id){
            Validation.addError("title",publication.title + " already exist!");
            params.flash();
            validation.keep();
            edit(publication.id);
        }

        publication.members = new ArrayList<Member>();

        if(memberIds != null){
            for(Long idMember: memberIds){
                publication.members.add((Member) Member.findById(idMember));
            }
        }else{
            publication.members = null;
        }
        publication.save();
        MyCacheSystem.deletePublicationAttachedCache(publication);
        index();
    }
    public static void delete(Long id){
        Map<String, String> result = new HashMap<String, String>();
        Publication publication = Publication.findById(id);
        try {
            publication.members = null;
            publication.delete();
            MyCacheSystem.deletePublicationAttachedCache(publication);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",publication.title + " deleted!");
        renderJSON(result);
    }

}
