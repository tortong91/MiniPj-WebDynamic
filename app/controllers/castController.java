package controllers;

import models.*;
import play.api.mvc.Result;
import play.cache.Cache;
import play.mvc.Controller;
import views.html.checkBill;
import views.html.index;
import views.html.productShow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static controllers.Application.show;
public class castController extends Controller {
    public static ProductPhone product;
    public static List<OrdersDetail> basketList = new ArrayList<OrdersDetail>();


    public static play.mvc.Result basketSell(){
        List<Basket> basketList = (List<Basket>) Cache.get("basketList");
        return show(views.html.basketList.render(basketList));
    }

    public static play.mvc.Result addOrder(String id){
        List<Basket> basketList = new ArrayList<Basket>();

        boolean found = false;
        if (Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for (int
                 i =0; i<basketList.size(); i++){
                if (basketList.get(i).getProduct().getId().equals(id)){
                    int amount = basketList.get(i).getAmount();
                    basketList.get(i).setAmount(amount +1);
                    found = true;
                    break;
                }
            }
        }
        if (found == false){
            product = ProductPhone.finder.byId(id);
            basketList.add(new Basket(product,1));
        }

        Cache.set("basketList",basketList);
        return redirect("/basketSell");
    }

    public static play.mvc.Result removeItem(String id){
        List<Basket> basketList = new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for (int i = 0; i < basketList.size(); i++){
                if (basketList.get(i).getProduct().getId().equals(id)){
                    basketList.remove(i);
                    break;
                }
            }
        }
        Cache.set("basketList",basketList);
        return redirect("/basketSell");
    }

    public static User ulogin;
    public static play.mvc.Result checkBill(){
        ulogin = User.finder.byId(session().get("usr"));
        List<Basket> basketList =new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            basketList = (List<Basket>) Cache.get("basketList");
        }
        return show(checkBill.render(basketList, ulogin));
    }
    public static List<ProductPhone> productList = new ArrayList<ProductPhone>();
    public static  play.mvc.Result saveBill(){
        List<Basket> basketList = new ArrayList<Basket>();
        if (Cache.get("basketList") != null){
            Orders orders = new Orders();
            User ulogin = User.finder.byId(session().get("usr"));
            orders.setDate(new Date());
            orders.setUlogin(ulogin);
            orders.setStatus("");
            Orders.insert(orders);
            basketList = (List<Basket>) Cache.get("basketList");
            for (int i = 0; i< basketList.size(); i++){
                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setOrders(orders);
                ordersDetail.setProduct(basketList.get(i).getProduct());
                ordersDetail.setAmount(basketList.get(i).getAmount());
                OrdersDetail.insert(ordersDetail);
            }
        }
        productList = ProductPhone.showList();
        Cache.remove("basketList");
        return show(productShow.render(productList));
    }

}
