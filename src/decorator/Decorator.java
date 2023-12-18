package decorator;

public class Decorator extends Drink {
	private Drink obj;
	
	public Decorator(Drink obj) {
		this.obj = obj;
	}
	

	public float cost() {
		return super.getPrice() + obj.cost();
	}

	public String getDes() {
		return obj.getDes() + "+" + des;
	}
	
}
