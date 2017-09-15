package controllers.admin;

import controllers.Security;
import models.*;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by aazzou on 10/17/14.
 */
public class CarouselImgCtrl extends Controller {
    public static void index(){
        session.clear();
       List<CarouselImg> imgs = CarouselImg.all().fetch();
       render(imgs);
    }



}
