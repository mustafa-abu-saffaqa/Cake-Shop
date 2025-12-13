package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds skittles to a cake.
 * 
 * <p>This decorator wraps a {@link Cake} instance and enhances it by adding
 * skittles to the description and adding the skittles cost to the total price.
 * 
 * <p>Example usage:
 * <pre>
 * Cake base = new AppleCake(2, "Medium", 10.00);
 * Cake decorated = new SkittlesDecorator(base);
 * // Result: "Order #2: Apple Cake (Medium) with Skittles"
 * // Cost: 10.00 + 1.50 = 11.50
 * </pre>
 * 
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class SkittlesDecorator extends CakeDecorator {
    
    /** The cost of adding skittles to a cake */
    private static final double SKITTLES_COST = 1.5;

    /**
     * Constructs a new SkittlesDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with skittles
     */
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake, SKITTLES_COST);
    }

    /**
     * Returns the description of the decorated cake including the skittles addition.
     * 
     * <p>Handles grammar properly:
     * <ul>
     *   <li>1st decoration: "Cake with Skittles" (only "with")</li>
     *   <li>2nd decoration: "Cake with Cream and Skittles" (one "with", one "and")</li>
     *   <li>3rd+ decorations: "Cake with Cream, Skittles, and Chocolate Chips" (Oxford comma style)</li>
     * </ul>
     * 
     * @return A description of the cake with skittles added
     */
    @Override
    public String describe() {
        String baseDescription = this.decoratedCake.describe();
        
        // If no "with" exists, this is the first decoration
        if (!baseDescription.contains("with")) {
            return baseDescription + " with Skittles";
        }
        
        // If "with" exists but no ", " or " and ", this is the second decoration
        if (!baseDescription.contains(", ") && !baseDescription.contains(" and ")) {
            return baseDescription + " and Skittles";
        }
        
        // If commas or "and" exist, this is third or later decoration (convert to Oxford comma style)
        int withIndex = baseDescription.indexOf(" with ");
        String beforeWith = baseDescription.substring(0, withIndex);
        String decorations = baseDescription.substring(withIndex + 6); // Everything after " with "
        
        // If it contains ", and ", replace it to add the new decoration
        if (decorations.contains(", and ")) {
            // Replace ", and X" with ", X, and Skittles"
            int lastCommaAndIndex = decorations.lastIndexOf(", and ");
            String beforeLast = decorations.substring(0, lastCommaAndIndex);
            String afterLast = decorations.substring(lastCommaAndIndex + 6); // Skip ", and "
            return beforeWith + " with " + beforeLast + ", " + afterLast + ", and Skittles";
        }
        
        // If it only has " and " (2nd decoration becoming 3rd), convert to Oxford comma style
        if (decorations.contains(" and ")) {
            // Replace "X and Y" with "X, Y, and Skittles"
            int andIndex = decorations.lastIndexOf(" and ");
            String first = decorations.substring(0, andIndex);
            String second = decorations.substring(andIndex + 5); // Skip " and "
            return beforeWith + " with " + first + ", " + second + ", and Skittles";
        }
        
        // Fallback: just add ", and Skittles"
        return baseDescription + ", and Skittles";
    }

}
