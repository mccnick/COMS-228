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
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter
{
	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public QuickSorter (Point[] pts)
	{
		super(pts); // invokes superclass constructor with the input array points
		super.algo = "quicksort"; // sets 'algo' in superclass to "quicksort"
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 * 
	 */
	@Override
	public void sort( )
	{
		quickSortRec(0, points.length - 1); // quick sorts array using recursive method
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first >= last) // base case
		{
			return;
		}
		int newPartition = partition(first, last); // partition the subarray and get the pivot index
		quickSortRec(first, newPartition - 1); // recursive sort left partition before pivot
		quickSortRec(newPartition + 1, last); // recursive sort right partition after pivot
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		int newInt = first - 1; // keeping track of the index of the smaller element
		int pivotInt = last; // set pivot element as the last element
		for (int i = first; i < last; i++) // iterate
		{
			if (points[i].compareTo(points[pivotInt]) <= 0) // compare current index with pivot
			{
				newInt++; // increment
				swap(newInt, i); // swap current index with newInt
			}
		}
		swap(newInt + 1, last); // swap to place in correct postion
		return newInt + 1; // returns index of pivot
	}

}
