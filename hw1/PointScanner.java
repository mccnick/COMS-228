package hw1;

import java.io.File;

/**
 * 
 * @author Nick McCullough
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner
{
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	private String fileName;

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner (Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) // base case checking
		{
			throw new IllegalArgumentException();
		}
		else
		{
			points = pts; // copy the points to the instance variable
			sortingAlgorithm = algo; // assign sorting algo
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner (String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		int newInt = 0; // initialize
		sortingAlgorithm = algo; // sorting algorithm
		fileName = inputFileName; // input file name

		try (Scanner newScanner = new Scanner(new File(inputFileName)))
		{
			while (newScanner.hasNext())
			{
				newScanner.nextInt(); // read an integer from the file
				newInt++; // increment the count
			}
			if (newInt % 2 != 0)
			{
				throw new InputMismatchException(); // exception if the count is odd
			}
			points = new Point[newInt / 2]; // create new array of points
			newScanner.close(); // close scanner
		}

		try (Scanner anotherNewScanner = new Scanner(new File(inputFileName)))
		{
			int anotherNewInt = 0; // initialize
			while (anotherNewScanner.hasNext())
			{
				int x = anotherNewScanner.nextInt(); // x-coordinate from file
				int y = anotherNewScanner.nextInt(); // y-coordinate from file
				points[anotherNewInt] = new Point(x, y); // create new point and store it in array
				anotherNewInt++; // increment the index
			}
		} catch (FileNotFoundException e)
		{
			throw new FileNotFoundException(); // exception if the file is not found
		}
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan( )
	{
		AbstractSorter newAbstractSorter = null; // create a variable to hold the sorter object

		if (sortingAlgorithm == Algorithm.MergeSort)
		{
			newAbstractSorter = new MergeSorter(points); // MergeSort
		}
		if (sortingAlgorithm == Algorithm.InsertionSort)
		{
			newAbstractSorter = new InsertionSorter(points); // InsertionSort
		}
		if (sortingAlgorithm == Algorithm.SelectionSort)
		{
			newAbstractSorter = new SelectionSorter(points); // SelectionSort
		}
		if (sortingAlgorithm == Algorithm.QuickSort)
		{
			newAbstractSorter = new QuickSorter(points); // QuickSort
		}

		long begin;
		long stop;

		// Compare by X
		newAbstractSorter.setComparator(0); // set the comparator to compare by X-coordinate
		begin = System.nanoTime(); // start time
		newAbstractSorter.sort(); // sorting
		stop = System.nanoTime(); // end time
		long xtime = stop - begin; // time taken for X-sorting

		int x = newAbstractSorter.getMedian().getX(); // get the median X-coordinate

		// Compare by Y
		newAbstractSorter.setComparator(1); // set the comparator to compare by Y-coordinate
		begin = System.nanoTime(); // start time
		newAbstractSorter.sort(); // sorting
		stop = System.nanoTime(); // end time
		long ytime = stop - begin; // time taken for Y-sorting
		scanTime = xtime + ytime; // total scan time
		int y = newAbstractSorter.getMedian().getY(); // Y-coordinate
		medianCoordinatePoint = new Point(x, y); // create the medianCoordinatePoint
	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats( )
	{
		String stats = null; // initialize

		if (sortingAlgorithm == Algorithm.MergeSort)
		{
			stats = "merge sort!!" + "         " + points.length + "  " + scanTime; // MergeSort
		}
		if (sortingAlgorithm == Algorithm.InsertionSort)
		{
			stats = "insertion sort!!" + "     " + points.length + "  " + scanTime; // InsertionSort
		}
		if (sortingAlgorithm == Algorithm.SelectionSort)
		{
			stats = "selection sort!!" + "     " + points.length + "  " + scanTime; // SelectionSort
		}
		if (sortingAlgorithm == Algorithm.QuickSort)
		{
			stats = "quick sort!!" + "         " + points.length + "  " + scanTime; // QuickSort
		}

		return stats;
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString( )
	{
		return medianCoordinatePoint.toString();
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile( ) throws FileNotFoundException
	{
		try
		{
			FileWriter file = new FileWriter(fileName); // filewriter
			file.write(toString()); // write
			file.close(); // close
		} catch (Exception e)
		{
			System.out.println(e.toString()); // print exception if any
		}
	}

}
