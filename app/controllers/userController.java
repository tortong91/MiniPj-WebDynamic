package controllers;

import play.Play;
import play.data.Form;
import play.mvc.Controller;
import models.User;
import play.mvc.Http;
import views.html.editregister;
import views.html.formLogin;
import views.html.registerForm;
import views.html.registerSuccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.Application.show;

public class userController extends Controller {
    public static String picPath = Play.application().configuration().getString("pathNews");
    public static Form<User> userForm = Form.form(User.class);
    public static List<User> userList = new ArrayList<User>();
    public static User data;

    public static play.mvc.Result showlist(){
        userList=User.userListuser();
        return show(registerSuccess.render(userList));
    }
    public static play.mvc.Result add(){
        userForm=Form.form(User.class);
        return ok(registerForm.render(userForm));
    }

    public static play.mvc.Result input(){
        Form<User> newUser = userForm.bindFromRequest();
        if (newUser.hasErrors()) {
            flash("msgError", "ใช้ข้อมูลไม่ถูกต้อง");
            return ok(registerForm.render(newUser));
        } else {
            data = newUser.get();
            User chk;
            chk = User.finder.byId(data.getId());
            if (chk != null) {
                flash("msgError", "ข้อมูลซ้ำกันในระบบ");
                return ok(registerForm.render(newUser));
            } else {
                User.add(data);
                return showlist();
            }
        }

        }


    public static play.mvc.Result edit(String id){
        Form<User> formUpdate = userForm.bindFromRequest();
        data =User.finder.byId(id);
        if(data==null){
            return showlist();
        }else{
            userForm = Form.form(User.class).fill(data);
            return show(editregister.render(userForm));
        }
    }
    public static play.mvc.Result update(){
        Form<User>formUpdate=userForm.bindFromRequest();
        if(formUpdate.hasErrors()){
            return show(editregister.render(formUpdate));
        }else{
            data = formUpdate.get();
            User.edit(data);
            return showlist();
        }
    }
    public static play.mvc.Result delete(String id){
        data = User.finder.byId(id);
        if(data==null){
            data.delete();
        }
        return showlist();
    }
}
