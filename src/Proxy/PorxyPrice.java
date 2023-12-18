package Proxy;

public class PorxyPrice implements ProductPrice {
    private ProductPrice productPrice;
    private String identity;

    public PorxyPrice(ProductPrice productPrice, String identity) {
        this.productPrice = productPrice;
        this.identity = identity;
    }

    public double getPrice() {
        double price = productPrice.getPrice();
        switch (identity){
            case "vip":
                price = price*0.88;
                break;
            default:
                break;
        }
        return price;
    }

}

