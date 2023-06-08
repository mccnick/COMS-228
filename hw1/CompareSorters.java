package hw1;

/**
 *  
 * @author Nick McCullough
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		PointScanner[] newScanner = new PointScanner[4]; // create an array to hold the four PointScanner objects

		System.out.println("Performances of Four Sorting Algorithms in Point Sorting");
		System.out.println();
		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
		Scanner scan = new Scanner(System.in); // create a scanner to read user input
		int whichTrial = 0; // initialize the trial number to 0
		while (whichTrial++ != -1)
		{
			System.out.print("Trial " + whichTrial + ": ");
			int userSelection = scan.nextInt(); // read the user selection

			if (userSelection == 3)
			{
				whichTrial = -1; // stop the program if the option is 3
			}
			else if (userSelection == 1 || userSelection == 2)
			{
				if (userSelection == 1)
				{
					System.out.print("Hello. Please enter number of random points you wish to sort: ");
					int numberOfRandomPoints = scan.nextInt(); // read the number of random points
					Point[] pts = generateRandomPoints(numberOfRandomPoints, new Random()); // generate random points
					newScanner[0] = new PointScanner(pts, Algorithm.SelectionSort); // PointScanner with SelectionSort
					newScanner[1] = new PointScanner(pts, Algorithm.InsertionSort); // PointScanner with InsertionSort
					newScanner[2] = new PointScanner(pts, Algorithm.MergeSort); // PointScanner with MergeSort
					newScanner[3] = new PointScanner(pts, Algorithm.QuickSort); // PointScanner with QuickSort

				}
				else
				{
					System.out.println("Points from a file of your choosing.");
					System.out.print("Please enter the file name: ");
					String fileName = scan.next(); // read the file name
					newScanner[0] = new PointScanner(fileName, Algorithm.SelectionSort); // SelectionSort
					newScanner[1] = new PointScanner(fileName, Algorithm.InsertionSort); // InsertionSort
					newScanner[2] = new PointScanner(fileName, Algorithm.MergeSort); // MergeSort
					newScanner[3] = new PointScanner(fileName, Algorithm.QuickSort); // QuickSort
				}

				System.out.println("\n algorithm   size  time (ns)");
				System.out.println("----------------------------------");
				for (PointScanner s : newScanner)
				{
					s.scan(); // perform the scan operation
					System.out.println(s.stats()); // print the performance statistics
				}
				System.out.println("----------------------------------\n");
			}
		}
		scan.close(); // close the scanner
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] × [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 *
	 * Ought to be private. Made public for testing.
	 *
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1)
		{
			throw new IllegalArgumentException("Number of points must be at least 1.");
		}

		Point[] newPoint = new Point[numPts]; // create an array to hold the random points
		for (int i = 0; i < numPts; i++)
		{
			int x = rand.nextInt(101) - 50; // random x coordinate within the range [-50, 50]
			int y = rand.nextInt(101) - 50; // random y coordinate within the range [-50, 50]
			newPoint[i] = new Point(x, y); // new Point object with the generated coordinates, assigned
		}
		return newPoint; // return new array with random points
	}

}
