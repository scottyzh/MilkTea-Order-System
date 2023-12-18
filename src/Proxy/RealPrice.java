package Proxy;

public class RealPrice implements ProductPrice{
    private double price;

    public RealPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
