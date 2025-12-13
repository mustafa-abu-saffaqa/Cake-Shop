/**
 * Concrete ChocolateCake class extending the abstract Cake base.
 */
public class ChocolateCake extends Cake {

	public ChocolateCake(int orderID, String size, double basePrice) {
		super(orderID, "Chocolate Cake", size, basePrice);
	}

	@Override
	public String describe() {
		return "Order #" + getOrderID() + ": " + getBaseName() + " (" + getSize() + ")";
	}

	@Override
	public double getCost() {
		return getBasePrice();
	}
}

