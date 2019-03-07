package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="tbUser")
public class User extends Model {
    @Id
    private String id;
    private String name,lastname,age,address,tel,username,password;
            private String position="User";

    public User() {
    }

    public User(String id, String name, String address, String tel, String lastname,String position ,String age, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.position = position;
        this.address=address;
        this.tel=tel;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public static Finder<String, User> finder = new Finder(String.class,User.class);

    public static List<User> userListuser (){
        return finder.all();
    }
    public static void add(User data){
        data.save();
    }
    public static void edit(User data){
        data.update();
    }
    public static void delete(User data){
        data.delete();
    }
    public static User user1 (String user ,String pass){
        return finder.where().and(Expr.eq("username",user),Expr.eq("password",pass)).findUnique();
    }
}
