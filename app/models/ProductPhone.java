package models;

import javafx.scene.text.Text;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tbProductPhone")
public class ProductPhone extends Model {
    @Id
    @GeneratedValue
    private String id;
    private String name,price,picture;
    private String details,details1,details2,details3;
    private String camera,display,ram,langue;



    @ManyToOne()
    private Brand brand;

    public ProductPhone() {
    }

    public ProductPhone(String id, String name, String price, String picture, String details, String details1, String details2, String details3, String camera, String display, String ram, String langue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.details = details;
        this.details1 = details1;
        this.details2 = details2;
        this.camera = camera;
        this.display = display;
        this.ram = ram;
        this.langue = langue;
        this.details3 = details3;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails1() {
        return details1;
    }

    public void setDetails1(String details1) {
        this.details1 = details1;
    }

    public String getDetails2() {
        return details2;
    }

    public void setDetails2(String details2) {
        this.details2 = details2;
    }

    public String getDetails3() {
        return details3;
    }

    public void setDetails3(String details3) {
        this.details3 = details3;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }



    public void setPicture(String picture) {
        this.picture = picture;
    }
    public static Finder<String, ProductPhone> finder =new Finder<String, ProductPhone>(String.class, ProductPhone.class);
    public static List<ProductPhone> showList(){
        return finder.all();
    }
    public static void add(ProductPhone data){
        data.save();
    }
    public static void edit(ProductPhone data){
        data.update();
    }
    public static void delect(ProductPhone data){
        data.delete();
    }
    public static List<ProductPhone> brandList (String id){
        return finder.where().eq("brand.id",id).findList();
    }
}
