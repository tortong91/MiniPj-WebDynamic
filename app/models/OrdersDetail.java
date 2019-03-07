package models;


        import play.db.ebean.Model;

        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.ManyToOne;
        import javax.persistence.Table;
        import java.util.List;
        import java.util.Random;

/**
 * Created by Admin on 29/10/2561.
 */
@Entity
@Table(name = "tbOrdersDetail")
public class OrdersDetail extends Model {
    @Id
    private String id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private ProductPhone product;
    private int amount;

    public OrdersDetail() {
        setId();
    }

    public OrdersDetail(String id, Orders orders, ProductPhone product, int amount) {
        setId();
        this.orders = orders;
        this.product = product;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int i;
        Random random =new Random();
        i = random.nextInt(100000)+1;
        id ="Cs-"+ Integer.toString(i);
    }

    public Orders getOrders() {
        return orders;
    }
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public ProductPhone getProduct() {
        return product;
    }

    public void setProduct(ProductPhone product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static Finder<String,OrdersDetail> finder = new Finder<String, OrdersDetail>(String.class,OrdersDetail.class);
    public static List<OrdersDetail> ordersDetailList (){return finder.all();}
    public static void insert (OrdersDetail ordersDetail){ordersDetail.save();}
    public static void update (OrdersDetail ordersDetail){ordersDetail.update();}
    public static void  delete (OrdersDetail ordersDetail){ordersDetail.delete();}

}