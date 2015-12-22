/*
 * The hill climibing algorithm begins at a given point and finds
 * the local maximum by gradually "climbing the hill" until the
 * subsequent value is less than the current.
 * 
 * Implemented a "safety net" feature as the hill climbing algorithm
 * can become stuck if a function tends towards infinity. This could
 * cause the hill climbing to miss the maximum however, so constants
 * must be adjusted accordingly.
 * 
 * For more information on an algorithm that overcome this weakness,
 * see the TabuSearch class.
 * 
 * Uncomment the sysout in main to see step-by-step breakdown.
 */

package optimization;

public class HillClimbing {
	
	private static final int SAFETY_NET = 50000;
	private static final double START_X = -2;
	private static final double DX = 0.1;
	
	/** Function: -(x-1)^2 + 2 */
	public static double f(double x) {
		return -(x - 1) * (x - 1) + 2;
	}
	
	public static void main(String[] args) {
		int iterations = 0;	// safety net in case function increases to infinity
		double currentX = START_X;
		double max = f(currentX);
		
		while (f(currentX + DX) > max && iterations < SAFETY_NET) {
			max = f(currentX + DX);
			//System.out.println("Evaluating: (" + currentX + ", " + f(currentX) + ")");
			currentX += DX;
			iterations++;
		}
		if (iterations == SAFETY_NET) 
			System.out.println("NOTE: Maximum iterations reached, result may be invalid");
		System.out.println("Max @ (" + currentX + ", " + max);
	}

}
