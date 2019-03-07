package controllers;

import models.Brand;
import models.ProductPhone;
import net.sf.ehcache.search.aggregator.Sum;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controllers.Application.show;

public class productPhoneController extends Controller {
    public static String picPath = Play.application().configuration().getString("sumsung");
    public static Form<ProductPhone> pFormform = Form.form(ProductPhone.class);
    public static List<ProductPhone> pList = new ArrayList<ProductPhone>();
    public static List<Brand> bList = new ArrayList<Brand>();
    public static ProductPhone data;

    public static play.mvc.Result showlist(){
        pList = ProductPhone.showList();
        return show(productShow.render(pList));
    }
    public static play.mvc.Result add(){
        bList = Brand.showList();
        pFormform=Form.form(ProductPhone.class);
        return show(productPhoneForm.render(pFormform,bList));
    }
    public static play.mvc.Result input(){
        Form<ProductPhone> myForm = pFormform.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Error", "ข้อมูลซ้ำกันในระบบ");
            return show(productPhoneForm.render(myForm,bList));
        }else{
            data = myForm.get();
            ProductPhone chk1;
            chk1 = ProductPhone.finder.byId(data.getId());
            if (chk1 != null) {
                flash("Error", "ข้อมูล Primarykey ซ้ำกันในระบบ");
                return show(productPhoneForm.render(myForm,bList));

            }else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return show(productPhoneForm.render(myForm,bList));
                    }
                    data = myForm.get();
                    fileName = data.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(picPath, fileName));
                    data.setPicture(fileName);
                    pList.add(data);
                    ProductPhone.add(data);

                }
                return showlist();
            }
        }
    }
    public static play.mvc.Result edit(String id){
        Form<ProductPhone> updateForm= pFormform.bindFromRequest();
        data=ProductPhone.finder.byId(id);
        if(data==null){
        return showlist();
        }else{
            bList = Brand.showList();
            pFormform=Form.form(ProductPhone.class).fill(data);
            return show(productPhoneEdit.render(pFormform,bList));
        }
    }
    public static play.mvc.Result update(){
        Form<ProductPhone>updateForm=pFormform.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        if(updateForm.hasErrors()){
            return show(productPhoneEdit.render(updateForm,bList));
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
            ProductPhone.edit(data);
            return showlist();
        }
    }
    public static play.mvc.Result delete(String id){
        data=ProductPhone.finder.byId(id);
        if(data!=null){

                File temp = new File("public/images/path_sumsung/" + data.getPicture());
                temp.delete();
            data.delete();
        }
        return showlist();
    }
    public static Result detailsp(String id) {
        int i;
        for (i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)){
                break;
            }
        }
        return ok(productDetails.render(pList.get(i)));
    }



    public static play.mvc.Result brandList (String id ){
                pList = ProductPhone.brandList(id);
                if(pList != null){
                    return show(productShow.render(pList));
                }else {
                    pList = ProductPhone.showList();
                    return show(productShow.render(pList));
                }
    }
}
