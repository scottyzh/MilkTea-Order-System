package decorator;

public class Sugar extends Decorator{

	public Sugar(Drink obj) {
		super(obj);
		setDes("糖分");
		setPrice(2.0f);
	}

}
