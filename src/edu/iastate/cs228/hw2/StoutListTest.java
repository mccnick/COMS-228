package edu.iastate.cs228.hw2;

import edu.iastate.cs228.hw2.StoutList;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.ListIterator;
import static org.junit.Assert.*;

public class StoutListTest
{
	private StoutList<Character> st;

	@Before
	public void initStoutList( )
	{
		st = new StoutList<>(4);
	}

	@Test
	public void testSimpleAdd( )
	{
		st.add('A');
		st.add('B');
		st.add('C');
		st.add('D');
		st.add('E');
		st.add('F');
		st.add('G');
		st.add('H');
		st.add('I');

		assertEquals("[(A, B, C, D), (E, F, G, H), (I, -, -, -)]", st.toStringInternal());
	}

	@Test
	public void testSplittingAdd( )
	{
		st.add('A');
		st.add('B');
		st.add('C');
		st.add('D');
		st.add('E');
		st.add('V');

		st.add(2, 'X');
		st.add(2, 'Y');
		st.add(2, 'Z');

		assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, -, -), (E, V, -, -)]", st.toStringInternal());
	}

	@Test
	public void testCollectionAddAll( )
	{
		st.add('A');
		st.add('B');
		st.addAll(Arrays.asList('C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'));

		assertEquals("[(A, B, C, D), (E, F, G, H), (I, J, K, L)]", st.toStringInternal());
	}

	@Test
	public void testCollectionAddAll2( )
	{
		StoutList<Character> st2 = new StoutList<>();
		st2.add('I');
		st2.add('J');
		st2.add('K');

		st.add('A');
		st.add('B');
		st.add('C');
		st.add('D');
		st.add(2, 'X');
		st.addAll(Arrays.asList(st2.toArray(Character[]::new)));

		assertEquals("[(A, B, X, -), (C, D, I, J), (K, -, -, -)]", st.toStringInternal());
	}

	@Test
	public void testSimpleRemove( )
	{
		st.add('A');
		st.add('B');
		st.add('C');
		st.remove(1);
		assertEquals("[(A, C, -, -)]", st.toStringInternal());
	}

	@Test
	public void testMergingRemove( )
	{
		st.add('A');
		st.add('B');
		st.add('#');
		st.add('#');
		st.add('C');
		st.add('D');
		st.add('E');
		st.remove(2);
		st.remove(2);
		st.add('V');
		st.add('W');
		st.add(2, 'X');
		st.add(2, 'Y');
		st.add(2, 'Z');
		st.remove(9);
		st.remove(3);
		st.remove(3);
		st.remove(5);
		st.remove(3);

		assertEquals("[(A, B, Z, -), (D, V, -, -)]", st.toStringInternal());
	}

	@Test
	public void testSize( )
	{
		st.add('C');
		st.add('D');
		st.add('E');
		st.remove(2);
		st.add('G');
		st.add('H');
		st.remove(3);
		st.add('I');
		st.add(2, 'Z');
		st.add(4, 'Y');
		st.add('V');
		st.add('W');
		st.remove(5);
		st.add(2, 'K');
		st.remove(3);
		assertEquals(7, st.size());
	}

	@Test
	public void testContains( )
	{
		st.add('A');
		st.add('B');
		st.add('C');
		st.add('D');
		st.add('E');
		assertTrue(st.contains('A'));
		assertTrue(st.contains('D'));
		assertTrue(st.contains('E'));
	}

	@Test
	public void testListIteratorForward( )
	{
		st.add('G');
		st.add('H');
		st.add('I');
		st.add('J');
		st.add('K');
		st.add('L');
		ListIterator<Character> iterator = st.listIterator();

		for (int i = 0; i < 6; i++)
		{
			assertTrue(iterator.hasNext());
			assertEquals((char) (71 + i), iterator.next().charValue());
		}
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testListIteratorBackward( )
	{
		st.addAll(Arrays.asList('P', 'Q', 'R', 'S', 'T', 'U', 'V'));
		ListIterator<Character> iterator = st.listIterator(st.size());

		for (int i = 6; i >= 0; i--)
		{
			assertTrue(iterator.hasPrevious());
			assertEquals((char) (80 + i), iterator.previous().charValue());
		}
		assertFalse(iterator.hasPrevious());
	}

	@Test
	public void testListIteratorAdd( )
	{
		st.add('A');
		st.add('B');
		ListIterator<Character> iterator = st.listIterator();
		iterator.next();
		iterator.previous();
		iterator.next();
		iterator.add('C');
		assertEquals("[(A, C, B, -)]", st.toStringInternal());
		assertEquals('B', iterator.next().charValue());
		assertEquals('B', iterator.previous().charValue());
		assertEquals('C', iterator.previous().charValue());
		iterator.add('X');
		iterator.add('Y');
		assertEquals("[(A, X, Y, -), (C, B, -, -)]", st.toStringInternal());
	}

	@Test
	public void testListIteratorRemove( )
	{
		st.add('X');
		st.add('Y');
		st.add('#');
		st.add('Z');
		st.addAll(Arrays.asList('P', 'Q', 'R', 'S', 'T', 'U', 'V'));
		assertEquals("[(X, Y, #, Z), (P, Q, R, S), (T, U, V, -)]", st.toStringInternal());
		ListIterator<Character> iterator = st.listIterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.previous();
		assertEquals('#', iterator.next().charValue());
		iterator.remove();
		assertEquals("[(X, Y, Z, -), (P, Q, R, S), (T, U, V, -)]", st.toStringInternal());
		assertEquals('Z', iterator.next().charValue());
		iterator.remove();
		assertEquals("[(X, Y, -, -), (P, Q, R, S), (T, U, V, -)]", st.toStringInternal());
		iterator.next();
		iterator.next();
		iterator.next();
		assertEquals('R', iterator.previous().charValue());
		iterator.remove();
		assertEquals("[(X, Y, -, -), (P, Q, S, -), (T, U, V, -)]", st.toStringInternal());
		assertEquals('S', iterator.next().charValue());
		iterator.remove();
		assertEquals("[(X, Y, -, -), (P, Q, -, -), (T, U, V, -)]", st.toStringInternal());
		assertEquals('Q', iterator.previous().charValue());
		iterator.previous();
		iterator.previous();
		iterator.remove();
		assertEquals("[(X, P, Q, -), (T, U, V, -)]", st.toStringInternal());

	}

	@Test
	public void testListIteratorSet( )
	{
		st.add('I');
		st.add('J');
		st.add('K');
		ListIterator<Character> iterator = st.listIterator();
		iterator.next();
		iterator.next();
		iterator.set('W');
		assertEquals("[(I, W, K, -)]", st.toStringInternal());
		iterator.next();
		iterator.set('X');
		assertEquals("[(I, W, X, -)]", st.toStringInternal());
		assertFalse(iterator.hasNext());
		assertEquals('X', iterator.previous().charValue());
		assertEquals('W', iterator.previous().charValue());
		assertEquals('I', iterator.previous().charValue());
		iterator.set('#');
		assertFalse(iterator.hasPrevious());
		assertEquals("[(#, W, X, -)]", st.toStringInternal());
	}

	@Test
	public void testInsertionSorting( )
	{
		st.add('A');
		st.add('B');
		st.add('D');
		st.add(2, 'Z');
		st.add('C');
		st.add('J');
		st.remove(4);
		st.add('C');
		st.add('E');
		st.remove(2);
		st.add('V');
		st.add(2, 'X');
		st.add(2, 'Y');
		st.remove(2);
		st.add('W');
		st.remove(5);
		st.remove(3);
		st.remove(3);
		st.remove(3);
		st.sort();

		assertEquals("[(A, B, V, W), (X, -, -, -)]", st.toStringInternal());
	}

	@Test
	public void testBubbleSorting( )
	{
		st.add('A');
		st.add('B');
		st.add('C');
		st.add('D');
		st.add('E');
		st.add('F');
		st.remove(2);
		st.add('G');
		st.add('H');
		st.remove(3);
		st.add('I');
		st.add('J');
		st.add(2, 'X');
		st.add(2, 'Y');
		st.add('V');
		st.remove(9);
		st.add('W');
		st.remove(5);
		st.add(2, 'Z');
		st.remove(3);
		st.sortReverse();

		assertEquals("[(Z, X, W, V), (I, H, G, D), (B, A, -, -)]", st.toStringInternal());
	}
}
