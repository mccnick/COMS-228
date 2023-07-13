package edu.iastate.cs228.hw1;

/**
 *  
 * @author Nick McCullough
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

import edu.iastate.cs228.hw1.Point;

/**
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort,
 * and QuickSort. It stores the input (later the sorted) sequence.
 */
public abstract class AbstractSorter
{
	protected Point[] points;// points array
	protected String algo = null; // initialize
	protected Comparator<Point> pointComparator = null; // initialize

	protected AbstractSorter ( )
	{
		// No implementation needed. Provides a default super constructor to subclasses.
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter,
		// and QuickSorter.
	}

	/**
	 * This constructor accepts an array of points as input. Copy the points into
	 * the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter (Point[] pts) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) // check if null or empty
		{
			throw new IllegalArgumentException();
		}
		else
		{
			points = new Point[pts.length]; // initialize points array
			getPoints(pts); // call getPoints, copy points from input array to the points[] array
		}
	}

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order ==
	 * 0, by y-coordinate if order == 1. Assign the comparator to the variable
	 * pointComparator.
	 * 
	 * @param order 0 by x-coordinate 1 by y-coordinate
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		if (order < 0 || order > 1)
		{
			throw new IllegalArgumentException("Order needs to be 0 or 1");
		}
		else
		{
			if (order == 0) // comparing with X or Y depending on order 0 or 1
			{
				Point.setXorY(true); // set to X
				pointComparator = new Comparator<Point>()
				{
					@Override
					public int compare(Point p1, Point p2)
					{
						return p1.compareTo(p2); // comparing based on X being set
					}
				};
			}
			else if (order == 1) // comparing with X or Y depending on order 0 or 1
			{
				Point.setXorY(false); // set to Y
				pointComparator = new Comparator<Point>()
				{
					@Override
					public int compare(Point p1, Point p2)
					{
						return p1.compareTo(p2); // comparing based on Y being set
					}
				};
			}
		}
	}

	/**
	 * Use the created pointComparator to conduct sorting.
	 * 
	 * Should be protected. Made public for testing.
	 */
	public abstract void sort( );

	/**
	 * Obtain the point in the array points[] that has median index
	 * 
	 * @return median point
	 */
	public Point getMedian( )
	{
		return points[points.length / 2];
	}

	/**
	 * Copys the array points[] onto the array pts[].
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		points = new Point[pts.length]; // copy
		for (int i = 0; i < pts.length; i++) // iterate
		{
			points[i] = pts[i]; // set copy of points to pts (indexes)
		}
	}

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[].
	 *
	 * @param i index of the first element to swap
	 * @param j index of the second element to swap
	 */
	protected void swap(int i, int j)
	{
		Point swappingPoints = points[i]; // temp Point variable for current index of points
		points[i] = points[j]; // swap indexes
		points[j] = swappingPoints; // set swapped index equal to temp Point variable
	}

}