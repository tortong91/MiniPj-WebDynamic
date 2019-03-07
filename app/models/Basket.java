package models;

/**
 * Created by Admin on 29/10/2561.
 */
public class Basket {
    private ProductPhone product;
    private int amount;

    public Basket() {
    }

    public Basket(ProductPhone product, int amount) {
        this.product = product;
        this.amount = amount;
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

}

