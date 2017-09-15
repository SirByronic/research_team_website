package controllers.admin;

import com.cloudinary.Cloudinary;
import models.*;
import org.h2.value.ValueStringIgnoreCase;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;
import tools.MyCacheSystem;
import tools.cloudinary.CloudImgUploader;

import javax.jws.WebMethod;
import java.io.File;
import java.util.*;

/**
 * Created by aazzou on 10/18/14.
 */
public class NewAdminCtrl extends Controller{
    public static void index(){
        List<New> news = MyCacheSystem.getNews();
        List<Section> sections = MyCacheSystem.getSections();
        render(news, sections);
    }

    public static void details(Long id){
        New _new = MyCacheSystem.getNew(id);
        render(_new);
    }

    public static void add(){
        List<Section> sections = MyCacheSystem.getSections();
        render(sections);
    }

    public static void edit(Long id){
       New _new = MyCacheSystem.getNew(id);
        List<Section> sections = MyCacheSystem.getSections();
        render(_new,sections);
    }


    public static void save(New _new,
                            File img,
                            List<Long> sectionIds){

        validation.valid(_new);
        Validation.required("sectionIds",sectionIds).message("Section is required");
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            add();
        }

        if(New.find("byTitle",_new.title).first() != null){
            Validation.addError("_new.title",_new.title + " already exist!");
            params.flash();
            validation.keep();
            add();
        }

        if(img != null){
            CloudImgUploader uploader = new CloudImgUploader(img,null);
            _new.imgURL = uploader.url();
            _new.imgID = uploader.publicID();
        }
        _new.sections = new ArrayList<Section>();
        for(Long idSection: sectionIds){
            _new.sections.add((Section) Section.findById(idSection));
        }
        _new.datePublication = new Date();

        _new.save();
        // delete cache
        MyCacheSystem.deleteNewAttachedCache(_new);
        index();
    }


    public static void update(New _new,
                              File img,
                              List<Long> sectionIds){
        validation.valid(_new);
        Validation.required("sectionIds",sectionIds).message("Section is required");
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            edit(_new.id);
        }

        New _new2 = New.find("byTitle",_new.title).first();

        if(_new2 != null && _new.id != _new2.id){
            Validation.addError("_new.title",_new.title + " already exist!");
            params.flash();
            validation.keep();
            edit(_new.id);
        }
        if(img != null){
            Map parameters = null;
            if(_new.imgID != null) parameters =  Cloudinary.asMap("public_id", _new.imgID);
            CloudImgUploader uploader = new CloudImgUploader(img,parameters);
            _new.imgURL = uploader.url();
            _new.imgID = uploader.publicID();
        }

        _new.save();
        MyCacheSystem.deleteNewAttachedCache(_new);
        index();
    }

    public static void delete(Long id){
        Map<String, String> result = new HashMap<String, String>();
        New _new = New.findById(id);
        try {
            _new.sections = null;
            _new.delete();
            MyCacheSystem.deleteNewAttachedCache(_new);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",_new.title + " deleted!");
        renderJSON(result);
    }

    public static void addSection(){
        render();
    }

    public static void saveSection(Section section){
        validation.valid(section);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            addSection();
        }
        if(Section.find("byName",section.name).first() != null){
            Validation.addError("section.name",section.name + " already exist!");
            params.flash();
            validation.keep();
            addSection();
        }
        section.save();
        MyCacheSystem.deleteSectionAttachedCache(section);
        index();
    }

    public static void editSection(Long id){
        Section section = MyCacheSystem.getSection(id);
        render(section);
    }



    public static void updateSection(Section section){
        validation.valid(section);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            editSection(section.id);
        }
        Section sect = Section.find("byName",section.name).first();
        if(sect != null && sect.id != section.id){
            Validation.addError("section.name",section.name + " already exist!");
            params.flash();
            validation.keep();
            editSection(section.id);
        }

        section.save();
        MyCacheSystem.deleteSectionAttachedCache(section);
        index();
    }

    @WebMethod(action = "DELETE")
    public static void deleteSection(Long id){
        Map<String, String> result = new HashMap<String, String>();
        Section section = Section.findById(id);
        try {
            section.delete();
            MyCacheSystem.deleteSectionAttachedCache(section);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",section.name+ " deleted!");
        renderJSON(result);
    }

}
