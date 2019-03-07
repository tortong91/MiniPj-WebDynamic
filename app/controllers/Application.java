package controllers;

import models.User;
import play.*;
import play.api.mvc.Result;
import play.data.Form;
import play.mvc.*;
import play.api.templates.Html;
import views.html.*;

import static controllers.productPhoneController.showlist;

public class Application extends Controller {
    public static play.mvc.Result index(){
        return showlist();
    }
    public static play.mvc.Result show(Html show) {
        return ok(index.render(show));
    }
    public static play.mvc.Result contect(){
        return show(contect.render());
    }
    public static play.mvc.Result about(){
        return show(about.render());
    }




public static play.mvc.Result loginForm(){
        return show(formLogin.render());
}

    public static play.mvc.Result login() {
        String user = Form.form().bindFromRequest().get("user");
        String pass = Form.form().bindFromRequest().get("pass");
        User datauser = User.user1(user, pass);
        String position = datauser.getPosition();
        if (datauser != null) {
            session("usr",datauser.getId());
            session("name", datauser.getName());
            session("position", datauser.getPosition());
        }
        return index();
    }

    public static play.mvc.Result logout(){
        session().clear();
        return show(formLogin.render());
    }

}
