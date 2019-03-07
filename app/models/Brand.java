package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="tbBarnd")
public class Brand extends Model {
    @Id
    private String id;
    private String name,picture;

    @OneToMany(mappedBy = "brand" , cascade = CascadeType.ALL)
    private List<ProductPhone>pdList=new ArrayList<ProductPhone>();
    public Brand() {
    }

    public Brand(String id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public void setPdList(List<ProductPhone> pdList) {
        this.pdList = pdList;
    }

    public static Model.Finder<String, Brand> finder =new Model.Finder<String, Brand>(String.class, Brand.class);
    public static List<Brand> showList(){
        return finder.all();
    }
    public static void add(Brand data){
        data.save();
    }
    public static void edit(Brand data){
        data.update();
    }
    public static void delect(Brand data){
        data.delete();
    }
}
