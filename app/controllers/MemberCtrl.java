package controllers;

import models.Member;
import models.MemberCategory;
import play.cache.Cache;
import play.mvc.Controller;
import tools.MyCacheSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aazzou on 10/15/14.
 */
public class MemberCtrl extends Controller {
    public static void index(){
        List<Member> members = MyCacheSystem.getMembers();
        List<MemberCategory> categories = MyCacheSystem.getMemberCategories();
        render(members,categories);
    }
    public static void byCategory(Long id){
        MemberCategory selectedCategory = MyCacheSystem.getMemberCategory(id);
        List<MemberCategory> categories = MyCacheSystem.getMemberCategories();
        List<Member> members = MyCacheSystem.getMembers(id, selectedCategory);
        render(members,categories,selectedCategory);
    }
    public static void details(Long id){
        Member member = MyCacheSystem.getMember(id);
        render(member);
    }
}
