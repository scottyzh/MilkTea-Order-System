package memento;


import entity.Order;

import java.util.ArrayList;

public class Memento {
	private ArrayList<Order> orderList;

	public Memento(ArrayList orderList) {
		super();
		this.orderList = orderList;
	}

	public ArrayList getOrders() {
		return orderList;
	}

}
