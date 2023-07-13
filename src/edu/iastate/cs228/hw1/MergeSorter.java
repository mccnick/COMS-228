package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *  
 * @author Nick McCullough
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter (Point[] pts)
	{
		super(pts); // invokes superclass constructor with the input array points
		super.algo = "mergesort"; // sets 'algo' in superclass to "mergesort"
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort( )
	{
		mergeSortRec(points); // recursive merge sort
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. It recursively splits the array into two halves, sorts them, and
	 * merges them back into the original array.
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length <= 1)
			return; // Base case: array of size 0 or 1 is already sorted

		int mid = pts.length / 2;
		Point[] left = Arrays.copyOfRange(pts, 0, mid); // Copy the left half of pts
		Point[] right = Arrays.copyOfRange(pts, mid, pts.length); // Copy the right half of pts
		mergeSortRec(left); // Recursively sort the left half
		mergeSortRec(right); // Recursively sort the right half
		mergeSortRec(pts, left, right); // Merge the sorted halves back into the original array
	}

	/**
	 * Merges two sorted subarrays, ptsLeft and ptsRight, into a single sorted
	 * array, pts.
	 * 
	 * @param pts         the original array
	 * @param leftPoints  the left subarray
	 * @param rightPoints the right subarray
	 */
	private void mergeSortRec(Point[] pts, Point[] leftPoints, Point[] rightPoints)
	{
		// pointers for Merge Sort
		int a = 0;
		int b = 0;
		int c = 0;

		// compare elements from left & right, merge them into pts in sorted order
		while (a < leftPoints.length && b < rightPoints.length)
		{
			if (leftPoints[a].compareTo(rightPoints[b]) < 0)
			{
				pts[c] = leftPoints[a]; // store the smaller element from leftPoints into pts
				a++; // move the pointer of leftPoints to next element
			}
			else
			{
				pts[c] = rightPoints[b]; // store the smaller element from rightPoints into pts
				b++; // move the pointer of rightPoints to next element
			}
			c++; // move the pointer of pts to the next position
		}

		while (b < rightPoints.length) // copy the remaining elements from ptsRight, if any
		{
			pts[c] = rightPoints[b]; // store the remaining elements from rightPoints into pts
			b++; // move the pointer of rightPoints to the next element
			c++; // move the pointer of pts to the next position
		}

		// copy the remaining elements from ptsLeft, if any
		while (a < leftPoints.length)
		{
			pts[c] = leftPoints[a]; // store the remaining elements from leftPoints into pts
			a++; // move the pointer of ptsLeft to the next element
			c++; // move the pointer of pts to the next position
		}
	}

}
