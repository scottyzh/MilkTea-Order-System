package composite;

public class Menu {

	public static void show(){

		DrinkComponent drink = new Drinks("饮品", "菜单");

		//创建饮品品种
		DrinkComponent coffee = new DrinkTypes("咖啡", "咖啡品种");
		DrinkComponent tea = new DrinkTypes("茶", "茶品种");
		DrinkComponent ingredient = new DrinkTypes("配料", "配料品种");

		//创建各个品种下的子类
		coffee.add(new SpecificTypes("无因咖啡", "10"));
		coffee.add(new SpecificTypes("速溶咖啡", "10"));

		tea.add(new SpecificTypes("白茶", "8"));
		tea.add(new SpecificTypes("红茶", "8"));

		ingredient.add(new SpecificTypes("糖分","2"));
		ingredient.add(new SpecificTypes("牛奶","2"));
		ingredient.add(new SpecificTypes("豆浆","2"));

		//将饮品品种加入到饮品菜单中
		drink.add(coffee);
		drink.add(tea);
		drink.add(ingredient);

		drink.print();
	}

}
