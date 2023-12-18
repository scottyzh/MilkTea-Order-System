package entity;

import java.util.ArrayList;

import memento.Memento;

public class OrderList {
    ArrayList<Order> orderList;

    public ArrayList getOrders(){
        return orderList;
    }

    public Memento saveOrdersMemento(){
        return new Memento(orderList);
    }


    public void getOrdersFromMemento(Memento memento){
    	orderList = memento.getOrders();
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orderList = orders;
    }
}
