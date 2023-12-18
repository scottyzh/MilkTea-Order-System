package entity;

import memento.Memento;

import java.io.Serializable;

public class Order  implements Serializable {
     Integer OrderNum;
     String dev;
     double price;
     String userIdentity;
     String state;

    public int getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(int num) {
        this.OrderNum = num;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Order(int orderNum, String dev, double price, String userIdentity, String state) {
        OrderNum = orderNum;
        this.dev = dev;
        this.price = price;
        this.userIdentity = userIdentity;
        this.state = state;
    }
}
