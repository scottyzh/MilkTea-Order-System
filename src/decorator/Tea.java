package decorator;

public class Tea extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
