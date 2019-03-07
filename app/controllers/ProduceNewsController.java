package controllers;

import models.ProductNews;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import views.html.formProduct_news;
import views.html.main;

import javax.xml.transform.Result;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static controllers.Application.show;


public class ProduceNewsController extends Controller {
    public static String picPath = Play.application().configuration().getString("pathNews");
    public static Form<ProductNews> pnForm = Form.form(ProductNews.class);
    public static List<ProductNews> pnList = new ArrayList<ProductNews>();
    public static ProductNews data;

    public static play.mvc.Result showlist(){
        pnList=ProductNews.showList();
        return show(main.render(pnList));
    }
    public static play.mvc.Result add(){
        pnForm=Form.form(ProductNews.class);
        return show(formProduct_news.render(pnForm));
    }

    public static play.mvc.Result input(){

        Form<ProductNews> myForm = pnForm.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        if (myForm.hasErrors()) {
            flash("Errorcow", "ข้อมูลซ้ำกันในระบบ");
            return show(formProduct_news.render(myForm));
        }else{
            data = myForm.get();
            ProductNews chk1;
            chk1 = ProductNews.finder.byId(data.getNumber());
            if (chk1 != null) {
                flash("Errorcow", "ข้อมูล Primarykey ซ้ำกันในระบบ");
                return show(formProduct_news.render(myForm));

            }else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return show(formProduct_news.render(myForm));
                    }
                    data = myForm.get();
                    fileName = data.getNumber() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(picPath, fileName));
                    data.setPicture(fileName);
                    pnList.add(data);
                    ProductNews.add(data);

                }
                return showlist();
            }
        }


}
    public static play.mvc.Result edit(String id){
        Form<ProductNews> formUpdate = pnForm.bindFromRequest();
        data =ProductNews.finder.byId(id);
        if(data==null){
            return showlist();
        }else{
            pnForm = Form.form(ProductNews.class).fill(data);
            return show(formProduct_news.render(pnForm));
        }
    }
    public static play.mvc.Result update(){
        Form<ProductNews>formUpdate=pnForm.bindFromRequest();
        if(formUpdate.hasErrors()){
            return show(formProduct_news.render(formUpdate));
        }else{
            data = formUpdate.get();
            ProductNews.edit(data);
            return showlist();
        }
    }
    public static play.mvc.Result delete(String id){
        data = ProductNews.finder.byId(id);
        if(data==null){
            data.delete();
        }
        return showlist();
    }
}
