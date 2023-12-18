package composite;

import java.util.ArrayList;
import java.util.List;


public class Drinks extends DrinkComponent {

	List<DrinkComponent> drinks = new ArrayList<DrinkComponent>();


	public Drinks(String name, String des) {
		super(name, des);
	}


	public void add(DrinkComponent component) {
		drinks.add(component);
	}


	public void remove(DrinkComponent component) {
		drinks.remove(component);
	}


	public String getName() {
		return super.getName();
	}


	public String getDes() {
		return super.getDes();
	}


	protected void print() {
		System.out.println("        --------------" + getName() + "--------------");
		//遍历
		for (DrinkComponent component : drinks) {
			component.print();
		}
	}

}
