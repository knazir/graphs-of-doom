/*
 * Checks random values between START_X and MAX_X in order to
 * determine an approximation of the function's maximum value.
 * To compensate for the greater uncertainty for a larger range,
 * the number of iterations are increased. Therefore, using a
 * smaller range would not need as many iterations to get a better
 * approximation. Given an infinite number of iterations, the
 * Stochastic search becomes a brute force search and will return
 * the exact maximum.
 * 
 * Uncomment the sysout in main to see step-by-step breakdown.
 */

package optimization;

import java.util.Random;

public class StochasticSearch {
	
	private static final double START_X = 0;
	private static final double MAX_X = 2;
	private static final int ITERATIONS = 10;
	
	/** Function: -(x-1)^2 + 2 */
	public static double f(double x) {
		return -(x - 1) * (x - 1) + 2;
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		double startX = START_X;
		double max = f(startX);
		
		for (int i = 0; i < ITERATIONS; i++) {
			double index = MAX_X * random.nextDouble();
			//System.out.println("Evaluating: (" + index + ", " + f(index) + ")");
			if (f(index) > max) max = f(index);
		}
		System.out.println("Max = " + max);
	}

}
