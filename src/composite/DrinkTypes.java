package composite;

import java.util.ArrayList;
import java.util.List;

public class DrinkTypes extends DrinkComponent {

	List<DrinkComponent> drinks = new ArrayList<DrinkComponent>();

	// 构造器
	public DrinkTypes(String name, String des) {
		super(name, des);
	}

	public  void add(DrinkComponent component) {
		drinks.add(component);
	}

	public  void remove(DrinkComponent component) {
		drinks.remove(component);
	}

	public String getName() {
		return super.getName();
	}

	public String getDes() {
		return super.getDes();
	}

	protected void print() {
		System.out.println("\t"+getName() + "\t\t");
		//遍历
		for (DrinkComponent component : drinks) {
			component.print();
		}
	}


}
