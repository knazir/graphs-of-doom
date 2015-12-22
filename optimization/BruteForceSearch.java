/*
 * Uses brute force and just iterates through the entire specified
 * domain to find the max value in O(n) time complexity where n is
 * the size of the domain.
 * 
 * Uncomment the sysout in main to see step-by-step breakdown.
 */


package optimization;

public class BruteForceSearch {
	
	private static final double START_X = -2;
	private static final double END_X = 2;
	private static final double DX = 0.1;
	
	/** Function: f(x) = -x^2 + 2 */
	public static double f(double x) {
		return -(x * x) + 2;
	}
	
	public static void main(String[] args) {
		double x = START_X;
		double max = f(x);
		
		for (double i = x; i <= END_X; i += DX) {
			//System.out.println("Evaluating: (" + i + ", " + f(i) + ")");
			if (f(i) > max) {
				max = f(i);
				x = i;
			}
		}
		System.out.println("Max @ (" + x + ", " + max);
	}
	
}
