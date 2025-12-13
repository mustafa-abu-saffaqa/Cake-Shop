package decorators;

import cakes.Cake;

/**
 * Abstract base class for cake decorators following the Decorator pattern.
 * 
 * <p>This class wraps a {@link Cake} instance and delegates method calls to it,
 * allowing concrete decorators to enhance the cake's description and cost without
 * modifying the original cake object.
 * 
 * <p>Concrete decorators (e.g., ChocolateChips, Cream, Skittles) extend this
 * class and override {@link #describe()} to add their specific features. The cost
 * calculation is centralized here and uses the {@link #decorationCost} field set
 * by each concrete decorator's constructor.
 * 
 * @author Amer Abuyaqob
 * @version 1.0
 */
public abstract class CakeDecorator extends Cake {
    protected Cake decoratedCake;
    protected double decorationCost;

    /**
     * Constructs a new CakeDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated
     * @param decorationCost The cost of this decoration to be added to the total
     */
    public CakeDecorator(Cake decoratedCake, double decorationCost) {
        super(decoratedCake.getOrderID(), decoratedCake.getBaseName(), 
              decoratedCake.getSize(), decoratedCake.getBasePrice());
        this.decoratedCake = decoratedCake;
        this.decorationCost = decorationCost;
    }

    /**
     * Returns the description of the decorated cake.
     * Concrete decorators should override this to append their own description.
     * 
     * @return A description of the cake with all decorations
     */
    @Override
    public String describe() {
        return this.decoratedCake.describe();
    }

    /**
     * Returns the total cost of the decorated cake.
     * 
     * <p>This method is centralized in the base class and adds the decoration cost
     * to the wrapped cake's total cost. Concrete decorators pass their cost via the
     * constructor and do not need to override this method.
     * 
     * @return The total cost including all decorations
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + this.decorationCost;
    }
}
