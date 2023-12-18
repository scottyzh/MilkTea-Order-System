package composite;

public abstract class DrinkComponent {

	private String name; // 名字
	private String des; // 说明

	public void add(DrinkComponent component) {

	}

	//用于删除某个饮品时调用
	public void remove(DrinkComponent component) {

	}

	//构造器
	public DrinkComponent(String name, String des) {
		super();
		this.name = name;
		this.des = des;
	}

	public String getName() {
		return name;
	}


	public String getDes() {
		return des;
	}

	//子类实现打印办法
	protected abstract void print();

}
