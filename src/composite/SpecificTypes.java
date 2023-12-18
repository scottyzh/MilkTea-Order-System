package composite;

public class SpecificTypes extends DrinkComponent {

	//没有集合
	public SpecificTypes(String name, String des) {
		super(name, des);
	}

	public String getName() {
		return super.getName();
	}

	public String getDes() {
		return super.getDes();
	}

	protected void print() {
		System.out.println("           "+getName()+"-------------"+getDes()+"元");
	}

}
