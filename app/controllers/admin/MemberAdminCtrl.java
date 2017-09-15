package controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import models.Member;
import models.MemberCategory;
import play.cache.Cache;
import play.data.validation.Validation;
import play.db.jpa.Model;
import play.mvc.Controller;
import tools.MyCacheSystem;
import tools.cloudinary.CloudImgUploader;
import tools.cloudinary.CloudUploaderParams;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by aazzou on 10/17/14.
 *
 */
public class MemberAdminCtrl extends Controller {
    public static void index(){
        List<Member> members = MyCacheSystem.getMembers();
        List<MemberCategory> categories = MyCacheSystem.getMemberCategories();
        Map<Long, Long> catCount = MyCacheSystem.getMemberCategoryCount(categories);
        render(members, categories, catCount);
    }
    public static void details(Long id){
        Member member = MyCacheSystem.getMember(id);
        render(member);
    }
    public static void add(){
        List<MemberCategory> categories = MyCacheSystem.getMemberCategories();
        render(categories);
    }
    public static void save(Member member){
        validation.valid(member);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            add();
        }
        if(Member.find("byFullName",member.fullName).first() != null){
            Validation.addError("member.fullName",member.fullName + " already exist");
            params.flash();
            validation.keep();
            add();
        }
        // if everything is OK about the object
        if(params.get("member.category") != null)
               member.category = MemberCategory.findById(params.get("member.category",Long.class));
        if(params.get("photo",File.class) != null) {
            // Img processing
            CloudImgUploader uploader = new CloudImgUploader(params.get("photo",File.class),null);
            member.photoURL = uploader.url();
            member.photoID = uploader.publicID();
        }else{
            member.photoURL = CloudUploaderParams.DEFAULT_PDP_URL;
        }
        member.save();
        // Delete cache
        MyCacheSystem.deleteMemberAttachedCache(member);
        index();
    }

    public static void edit(Long id){
        List<MemberCategory> categories = MyCacheSystem.getMemberCategories();
        Member member = MyCacheSystem.getMember(id);
        render(member,categories);
    }

    public static void update(Member member){
        validation.valid(member);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            edit(member.id);
        }

        Member mb = Member.find("byFullName",member.fullName).first();
        if(mb != null && mb.id != member.id){
            Validation.addError("member.fullName",member.fullName + " already exist !");
            params.flash();
            validation.keep();
            edit(member.id);
        }

        if(params.get("photo",File.class) != null) {
            // Img processing
            Map parameters = null;
            if(member.photoID != null) parameters =  Cloudinary.asMap("public_id",member.photoID);
            CloudImgUploader uploader = new CloudImgUploader(params.get("photo",File.class),parameters);
            member.photoURL = uploader.url();
            member.photoID = uploader.publicID();
        }
        if(params.get("member.category") != null)
            member.category = MemberCategory.findById(params.get("member.category",Long.class));

        member.save();
        // Delete cache
        MyCacheSystem.deleteMemberAttachedCache(member);
        index();
    }

    public static void delete(Long id){
        Map<String, String> result = new HashMap<String, String>();
        Member member = Member.findById(id);
        try {
            member.delete();
            // Delete cache
            MyCacheSystem.deleteMemberAttachedCache(member);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",member.fullName + " deleted!");
        renderJSON(result);
    }

    // Member Category Controller
    public static void addCategory(){
        render();
    }

    public static void editCategory(Long id){
        MemberCategory category = MyCacheSystem.getMemberCategory(id);
        render(category);
    }

    public static void saveCategory(MemberCategory category){
        validation.valid(category);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            addCategory();
        }

        if((MemberCategory.find("byName",category.name).first() != null)){
            Validation.addError("category.name",category.name + " already exist !");
            params.flash();
            validation.keep();
            editCategory(category.id);
        }

        category.save();
        // Delete cache
        MyCacheSystem.deleteMemberCategoryAttachedCache(category);
        index();
    }

    public static void updateCategory(MemberCategory category){
        validation.valid(category);
        if(validation.hasErrors()) {
            params.flash();
            validation.keep();
            editCategory(category.id);
        }
        MemberCategory cat = MemberCategory.find("byName",category.name).first();
        if(cat != null && cat.id != category.id){
            Validation.addError("category.name",category.name + " already exist !");
            params.flash();
            validation.keep();
            editCategory(category.id);
        }

        category.save();


        // Delete cache
        MyCacheSystem.deleteMemberCategoryAttachedCache(category);
        index();
    }

    public static void deleteCategory(Long id){
        Map<String, String> result = new HashMap<String, String>();
        MemberCategory category = MemberCategory.findById(id);
        try {
            category.delete();
            MyCacheSystem.deleteMemberCategoryAttachedCache(category);
        }catch (Exception e){
            result.put("status","error");
            result.put("error_msg","Error : Check the integrity constraint");
            renderJSON(result);
        }
        result.put("status","success");
        result.put("success_msg",category.name + " deleted!");
        renderJSON(result);
    }

}
