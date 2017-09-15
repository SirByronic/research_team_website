package controllers;

import models.Member;
import models.Publication;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by aazzou on 10/16/14.
 */
public class PublicationCtrl extends Controller {
    public static void index(){
        List<Publication> publications = Publication.all().fetch();
        List<Member> members = Member.all().fetch();
        render(publications,members);
    }

   public static void byMember(Long id){
       Member selectedMember = Member.findById(id);
       List<Member> members = Member.all().fetch();
       render(selectedMember,members);
   }
}
