package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Nick McCullough
 *
 */

/**
 * 
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public SelectionSorter (Point[] pts)
	{
		super(pts); // invokes superclass constructor with the input array points
		super.algo = "selection sort"; // sets 'algo' in superclass to "selection sort"
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort( )
	{
		for (int i = 0; i < points.length; i++) // iterate
		{
			int minimumValue = i; // minimum int placeholder for current index
			for (int j = i + 1; j < points.length; j++) // iterate remaining unsorted values
			{
				if (points[j].compareTo(points[minimumValue]) < 0) // current element smaller than the assumed minimum?
				{
					minimumValue = j; // set new index equal to minimumValue
				}
			}
			swap(i, minimumValue); // swap the current element with the minimum element found
		}
	}
}
