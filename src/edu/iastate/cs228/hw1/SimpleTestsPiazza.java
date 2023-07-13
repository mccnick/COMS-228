package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;

public class SimpleTestsPiazza
{

	Point[] points;
	Boolean sorted;

	@Test
	public void selectionSortX( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (selectionSorter.points[i - 1].compareTo(selectionSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
		;
	}

	@Test
	public void selectionSortY( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = false;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.sort();
		for (int i = 1; i < points.length; i++)
		{

			if (selectionSorter.points[i - 1].compareTo(selectionSorter.points[i]) == 1)
			{

				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void selectionSortMCP( )
	{
		Random rand = new Random(100);
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.sort();
		int x = selectionSorter.getMedian().getX();
		Point.xORy = false;
		selectionSorter = new SelectionSorter(points);
		selectionSorter.sort();
		int y = selectionSorter.getMedian().getY();
		assertEquals(x, 6);
		assertEquals(y, 11);
		;
	}

	@Test
	public void insertionSortX( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		InsertionSorter insertionSorter = new InsertionSorter(points);
		insertionSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (insertionSorter.points[i - 1].compareTo(insertionSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void insertionSortY( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = false;
		InsertionSorter insertionSorter = new InsertionSorter(points);
		insertionSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (insertionSorter.points[i - 1].compareTo(insertionSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void insertionSortMCP( )
	{
		Random rand = new Random(100);
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		InsertionSorter insertionSorter = new InsertionSorter(points);
		insertionSorter.sort();
		int x = insertionSorter.getMedian().getX();
		Point.xORy = false;
		insertionSorter = new InsertionSorter(points);
		insertionSorter.sort();
		int y = insertionSorter.getMedian().getY();
		assertEquals(x, 6);
		assertEquals(y, 11);
		;
	}

	@Test
	public void mergeSortX( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		MergeSorter mergeSorter = new MergeSorter(points);
		mergeSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (mergeSorter.points[i - 1].compareTo(mergeSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void mergeSortY( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = false;
		MergeSorter mergeSorter = new MergeSorter(points);
		mergeSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (mergeSorter.points[i - 1].compareTo(mergeSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void mergeSortMCP( )
	{
		Random rand = new Random(100);
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		MergeSorter mergeSorter = new MergeSorter(points);
		mergeSorter.sort();
		int x = mergeSorter.getMedian().getX();
		Point.xORy = false;
		mergeSorter = new MergeSorter(points);
		mergeSorter.sort();
		int y = mergeSorter.getMedian().getY();
		assertEquals(x, 6);
		assertEquals(y, 11);
	}

	@Test
	public void quickSortX( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (quickSorter.points[i - 1].compareTo(quickSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void quickSortY( )
	{
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = false;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.sort();
		for (int i = 1; i < points.length; i++)
		{
			if (quickSorter.points[i - 1].compareTo(quickSorter.points[i]) == 1)
			{
				sorted = false;
			}
		}
		assertEquals(true, sorted);
	}

	@Test
	public void quickSortMCP( )
	{
		Random rand = new Random(100);
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		QuickSorter quickSorter = new QuickSorter(points);
		quickSorter.sort();
		int x = quickSorter.getMedian().getX();
		Point.xORy = false;
		quickSorter = new QuickSorter(points);
		quickSorter.sort();
		int y = quickSorter.getMedian().getY();
		assertEquals(x, 6);
		assertEquals(y, 11);
		;
	}
}