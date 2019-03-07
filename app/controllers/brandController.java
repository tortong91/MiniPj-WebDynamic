package controllers;

import models.Brand;
import models.ProductPhone;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.Application.show;

public class brandController extends Controller {
    public static String picPath = Play.application().configuration().getString("brand");
    public static Form<Brand> pFormform = Form.form(Brand.class);
    public static List<Brand> pList = new ArrayList<Brand>();
    public static Brand data;

    public static play.mvc.Result showlist(){
        pList = Brand.showList();
        return show(brandShow.render(pList));
    }
    public static play.mvc.Result add(){
        pFormform=Form.form(Brand.class);
        return show(brandForm.render(pFormform));
    }
    public static play.mvc.Result input(){
        Form<Brand> myForm = pFormform.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorbrand", "ข้อมูลซ้ำกันในระบบ");
            return show(brandForm.render(myForm));
        }else{
            data = myForm.get();
            Brand chk1;
            chk1 = Brand.finder.byId(data.getId());
            if (chk1 != null) {
                flash("Errorbrand", "ข้อมูล Primarykey ซ้ำกันในระบบ");
                return show(brandForm.render(myForm));

            }else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return show(brandForm.render(myForm));
                    }
                    data = myForm.get();
                    fileName = data.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(picPath, fileName));
                    data.setPicture(fileName);
                    pList.add(data);
                    Brand.add(data);

                }
                return showlist();
            }
        }
    }
    public static play.mvc.Result edit(String id){
        Form<Brand> updateForm= pFormform.bindFromRequest();
        data=Brand.finder.byId(id);
        if(data==null){
            return showlist();
        }else{
            pFormform=Form.form(Brand.class).fill(data);
            return show(brandEdit.render(pFormform));
        }
    }
    public static play.mvc.Result update(){
        Form<Brand>updateForm=pFormform.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        if(updateForm.hasErrors()){
            return show(brandEdit.render(updateForm));
        }else{
            data=updateForm.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = data.getId() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/path_sumsung/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/path_sumsung/" + realName));
                data.setPicture(realName);
            }
            Brand.edit(data);
            return showlist();
        }
    }
    public static play.mvc.Result delete(String id){
        data=Brand.finder.byId(id);
        if(data!=null){

            File temp = new File("public/images/path_sumsung/" + data.getPicture());
            temp.delete();
            data.delete();
        }
        return showlist();
    }
}
