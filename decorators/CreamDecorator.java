package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds cream to a cake.
 * 
 * <p>This decorator wraps a {@link Cake} instance and enhances it by adding
 * cream to the description and adding the cream cost to the total price.
 * 
 * <p>Example usage:
 * <pre>
 * Cake base = new ChocolateCake(1, "Large", 12.50);
 * Cake decorated = new CreamDecorator(base);
 * // Result: "Order #1: Chocolate Cake (Large) with Cream"
 * // Cost: 12.50 + 2.00 = 14.50
 * </pre>
 *
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class CreamDecorator extends CakeDecorator {
    
    /** The cost of adding cream to a cake */
    private static final double CREAM_COST = 2.0;

    /**
     * Constructs a new CreamDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with cream
     */
    public CreamDecorator(Cake decoratedCake) {
        super(decoratedCake, CREAM_COST);
    }

    /**
     * Returns the description of the decorated cake including the cream addition.
     * 
     * <p>Handles grammar properly:
     * <ul>
     *   <li>1st decoration: "Cake with Cream" (only "with")</li>
     *   <li>2nd decoration: "Cake with Cream and Skittles" (one "with", one "and")</li>
     *   <li>3+ decorations: "Cake with Cream, Skittles, and Chocolate Chips" (Oxford comma style)</li>
     * </ul>
     * 
     * @return A description of the cake with cream added
     */
    @Override
    public String describe() {
        String baseDescription = this.decoratedCake.describe();
        
        // If no "with" exists, this is the first decoration
        if (!baseDescription.contains("with")) {
            return baseDescription + " with Cream";
        }
        
        // If "with" exists but no ", " or " and ", this is the second decoration
        if (!baseDescription.contains(", ") && !baseDescription.contains(" and ")) {
            return baseDescription + " and Cream";
        }
        
        // If commas or "and" exist, this is third or later decoration (convert to Oxford comma style)
        int withIndex = baseDescription.indexOf(" with ");
        String beforeWith = baseDescription.substring(0, withIndex);
        String decorations = baseDescription.substring(withIndex + 6); // Everything after " with "
        
        // If it contains ", and ", replace it to add the new decoration
        if (decorations.contains(", and ")) {
            // Replace ", and X" with ", X, and Cream"
            int lastCommaAndIndex = decorations.lastIndexOf(", and ");
            String beforeLast = decorations.substring(0, lastCommaAndIndex);
            String afterLast = decorations.substring(lastCommaAndIndex + 6); // Skip ", and "
            return beforeWith + " with " + beforeLast + ", " + afterLast + ", and Cream";
        }
        
        // If it only has " and " (2nd decoration becoming 3rd), convert to Oxford comma style
        if (decorations.contains(" and ")) {
            // Replace "X and Y" with "X, Y, and Cream"
            int andIndex = decorations.lastIndexOf(" and ");
            String first = decorations.substring(0, andIndex);
            String second = decorations.substring(andIndex + 5); // Skip " and "
            return beforeWith + " with " + first + ", " + second + ", and Cream";
        }
        
        // Fallback: just add ", and Cream"
        return baseDescription + ", and Cream";
    }

}
