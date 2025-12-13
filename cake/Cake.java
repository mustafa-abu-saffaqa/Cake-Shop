public abstract class Cake{
    protected int orderID;
    protected String baseName;
    protected String size;
    protected double basePrice;

    /**
     * Constructor for Cake class.
     * Initializes all fields for a cake order.
     * 
     * @param orderID The unique identifier for this order
     * @param baseName The name of the base cake type (e.g., "Apple Cake", "Cheese Cake")
     * @param size The size of the cake
     * @param basePrice The base price of the cake before decorations
     */
    public Cake(int orderID, String baseName, String size, double basePrice) {
        this.orderID = orderID;
        this.baseName = baseName;
        this.size = size;
        this.basePrice = basePrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public abstract String describe();

    public abstract double getCost();
}
