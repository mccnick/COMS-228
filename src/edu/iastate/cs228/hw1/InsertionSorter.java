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
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter
{
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public InsertionSorter (Point[] pts)
	{
		super(pts); // invokes superclass constructor with the input array points
		super.algo = "insertion sort"; // sets 'algo' in superclass to "insertion sort"
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort( )
	{
		for (int i = 1; i < points.length; i++) // iterate
		{
			Point currentIndex = points[i]; // store current index being inserted
			int j = i - 1; // index value j for previous index

			while (j >= 0 && points[j].compareTo(currentIndex) > 0)
			{
				points[j + 1] = points[j]; // shift index right
				j--; // move to previous index
			}
			points[j + 1] = currentIndex; // place current index in insertionKey
		}
	}

}
