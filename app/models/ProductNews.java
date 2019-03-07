package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name="tbProductNews")
public class ProductNews extends Model {
    @Id
private String number;
private String name,details,date,picture;

    public ProductNews() {
    }

    public ProductNews(String number, String name, String details, String date, String picture) {
        this.number = number;
        this.name = name;
        this.details = details;
        this.date = date;
        this.picture = picture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public static Finder<String,ProductNews> finder =new Finder<String,ProductNews>(String.class,ProductNews.class);
    public static List<ProductNews> showList(){
            return finder.all();
    }
    public static void add(ProductNews data){
            data.save();
    }
    public static void edit(ProductNews data){
        data.update();
    }
    public static void delect(ProductNews data){
        data.delete();
    }


}
