package edu.iastate.cs228.hw2;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Nick McCullough
 */

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public StoutList ( )
	{
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public StoutList (int nodeSize)
	{
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public StoutList (Node head, Node tail, int nodeSize, int size)
	{
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	@Override
	public int size( )
	{
		// TODO Auto-generated method stub
		return Math.min(size, Integer.MAX_VALUE);
	}

	@Override
	public boolean add(E item) throws NullPointerException
	{

		if (item == null) // base case exception handling
		{
			throw new NullPointerException();
		}

		add(size, item); // add the item at the end of the list

		return true;
	}

	@Override
	public void add(int pos, E item)
	{

		if (pos < 0 || pos > size) // out of bounds exception handling
		{
			throw new IndexOutOfBoundsException();
		}

		if (item == null) // base case exception handling
		{
			throw new NullPointerException();
		}

		StoutListNode nodeInfo = find(pos); // find the node
		add(nodeInfo.stoutNode, item, nodeInfo.nodeOffset); // add and offset item to the found node
	}

	@Override
	public E remove(int pos)
	{

		if (pos < 0 || pos >= size) // out of bounds exception handling
		{
			throw new IndexOutOfBoundsException();
		}

		StoutListNode nodeInfo = find(pos); // find the node

		return remove(nodeInfo); // remove node
	}

	/**
	 * Sort all elements in the stout list in the NON-DECREASING order. You may do
	 * the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by calling
	 * the insertionSort() method. (Note that sorting efficiency is not a concern
	 * for this project.) Finally, copy all elements from the array back to the
	 * stout list, creating new nodes for storage. After sorting, all nodes but
	 * (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort( )
	{
		// TODO
		E[] dataToSort = (E[]) new Comparable[size()];
		Iterator<E> newIterator = iterator();

		for (int i = 0; i < size; i++) // iterate
		{
			dataToSort[i] = newIterator.next(); // populate
		}

		head.next = tail;
		tail.previous = head;
		size = 0;
		insertionSort(dataToSort, new ObjectComparator()); // insertion sort
	}

	/**
	 * Sort all elements in the stout list in the NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse( )
	{
		// TODO
		E[] reverseDataToSort = (E[]) new Comparable[size];
		Iterator<E> iterator = iterator();

		for (int i = 0; i < size; i++) // iterate
		{
			reverseDataToSort[i] = iterator.next(); // populate
		}

		head.next = tail;
		tail.previous = head;
		size = 0;
		bubbleSort(reverseDataToSort); // bubble sort
		this.addAll(Arrays.asList(reverseDataToSort));
	}

	@Override
	public Iterator<E> iterator( )
	{
		// TODO Auto-generated method stub
		return listIterator();
	}

	@Override
	public ListIterator<E> listIterator( )
	{
		// TODO Auto-generated method stub
		return listIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		// TODO Auto-generated method stub
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}

		return new StoutListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal( )
	{
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter)
	{
		int count = 0;
		int position = -1;
		if (iter != null)
		{
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail)
		{
			sb.append('(');
			E data = current.data[0];
			if (data == null)
			{
				sb.append("-");
			}
			else
			{
				if (position == count)
				{
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i)
			{
				sb.append(", ");
				data = current.data[i];
				if (data == null)
				{
					sb.append("-");
				}
				else
				{
					if (position == count)
					{
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size)
					{
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node
	{
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset. Precondition: count
		 * < nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item)
		{
			if (count >= nodeSize)
			{
				return;
			}
			data[count++] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " + count + " to
			// node " + Arrays.toString(data));
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item)
		{
			if (count >= nodeSize)
			{
				return;
			}
			for (int i = count - 1; i >= offset; --i)
			{
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
			// useful for debugging
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset)
		{
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i)
			{
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	private class StoutListIterator implements ListIterator<E>
	{
		// constants you possibly use ...

		// instance variables ...

		/*
		 * Current index
		 */
		private int nodeIndex;

		/*
		 * Node that was accessed last, type StoutListNode
		 */
		private StoutListNode lastAccessedNode;

		/*
		 * Is this node removable or not?
		 */
		private boolean isNodeRemovable;

		/**
		 * Default constructor
		 */
		public StoutListIterator ( )
		{
			// TODO
			nodeIndex = 0; // initialize
			lastAccessedNode = null; // initialize
			isNodeRemovable = false; // initialize
		}

		/**
		 * Constructor that finds node at a given position.
		 * 
		 * @param pos
		 */
		public StoutListIterator (int pos)
		{
			// TODO
			if (pos < 0 || pos > size) // out of bounds exception handling
			{
				throw new IndexOutOfBoundsException();
			}

			nodeIndex = pos; // assign the specified position to the 'nodeIndex' variable
			lastAccessedNode = null;
			isNodeRemovable = false;
		}

		@Override
		public boolean hasNext( )
		{
			// TODO
			return (nodeIndex < size); // indicates that there is a next element in the StoutList
		}

		@Override
		public E next( )
		{
			// TODO
			if (hasNext())
			{
				StoutListNode newNode = find(nodeIndex++); // find the StoutListNode and increment
				lastAccessedNode = newNode; // last accessed node to the current node
				isNodeRemovable = true;
				return (newNode.stoutNode.data[newNode.nodeOffset]); // return the data element of the current node
			}
			throw new NoSuchElementException(); // throw a NoSuchElementException if there is no next element
		}

		@Override
		public void remove( )
		{
			// TODO
			if (!isNodeRemovable) // removable exception handling
			{
				throw new IllegalStateException();
			}

			StoutListNode currentNodeIndex = find(nodeIndex); // find the StoutListNode corresponding to current index

			if (lastAccessedNode.stoutNode != currentNodeIndex.stoutNode
					|| lastAccessedNode.nodeOffset < currentNodeIndex.nodeOffset)
			{
				nodeIndex--; // decrement the index
			}
			StoutList.this.remove(lastAccessedNode);
			lastAccessedNode = null;
			isNodeRemovable = false;
		}

		// Other methods you may want to add or override that could possibly facilitate
		// other operations, for instance, addition, access to the previous element,
		// etc.
		//
		// ...
		//

		/**
		 * Method to return the next index.
		 */
		@Override
		public int nextIndex( )
		{
			return nodeIndex;
		}

		/**
		 * Method to confirm there is actually a previous element.
		 */
		@Override
		public boolean hasPrevious( )
		{
			return nodeIndex > 0;
		}

		/**
		 * Method to return previous index.
		 */
		@Override
		public int previousIndex( )
		{
			return nodeIndex - 1;
		}

		/**
		 * Method to return previous element in the StoutList List using previous index.
		 */
		@Override
		public E previous( )
		{
			if (hasPrevious())
			{
				StoutListNode newNode; // new node

				if (nodeIndex == size)
				{
					newNode = find(--nodeIndex); // find the StoutListNode at the previous index and decrement

				}
				else
				{
					newNode = find(--nodeIndex); // find the StoutListNode at the previous index and decrement
					lastAccessedNode = newNode;
				}

				isNodeRemovable = true; // previous node is accessible
				return newNode.stoutNode.data[newNode.nodeOffset]; // Return the data element of the current node
			}

			throw new NoSuchElementException(); // Throw a NoSuchElementException if there is no previous element
		}

		/**
		 * Method to set the element at the current index.
		 */
		@Override
		public void set(E item)
		{
			if (item == null) // base case exception handling
			{
				throw new NullPointerException();
			}
			if (!isNodeRemovable)
			{
				throw new IllegalStateException();
			}
			lastAccessedNode.stoutNode.data[lastAccessedNode.nodeOffset] = item; // Set current index to the item
		}

		/**
		 * Method to add element.
		 */
		@Override
		public void add(E item)
		{
			if (item == null) // base case exception handling
			{
				throw new NullPointerException();
			}

			isNodeRemovable = false; // set the 'removable' flag to false
			StoutList.this.add(nodeIndex++, item); // add the item at the current index and increment the index
		}
	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp)
	{
		// TODO
		for (int i = 1; i < arr.length; i++) // iterate
		{
			E tempVariable = arr[i]; // store current element in a temp variable
			// Store the index of the previous element
			int prevIndex = i - 1;

			// shift elements greater than temp to the right
			// until we find the correct position for temp
			while (prevIndex > -1 && comp.compare(arr[prevIndex], tempVariable) > 0)
			{
				arr[prevIndex + 1] = arr[prevIndex]; // move previous element to next index
				prevIndex--; // decrement the index of previous element
			}

			arr[prevIndex + 1] = tempVariable; // insert current element into array
		}

		this.addAll(Arrays.asList(arr)); // add all sorted elements back to the list
	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr)
	{
		// TODO
		boolean swap = false; // were swaps made?

		for (int i = 1; i < arr.length; i++) // iterate
		{
			if (arr[i - 1].compareTo(arr[i]) < 0) // compare
			{
				bubbleSwap(arr, i - 1, i); // swap elements
				swap = true;
			}
		}

		if (swap == true)
		{
			bubbleSort(arr); // repeat sorting
		}
	}

	private void bubbleSwap(E[] arr, int i, int j)
	{
		E temporaryValue = arr[i]; // store the value of arr[i] in a temporary variable
		arr[i] = arr[j]; // assign the value of arr[j] to arr[i]
		arr[j] = temporaryValue; // assign the value of the temporary variable to arr[j]
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 ****************************** PRIVATE HELPER METHODS ******************************
	 *
	 *
	 *
	 *
	 */

	/*
	 * Class representing a node and its offset within the StoutList.
	 */
	private class StoutListNode
	{
		public Node stoutNode; // reference to the node
		public int nodeOffset; // offset within the node

		// constructor to initialize
		public StoutListNode (Node node, int offset)
		{
			this.stoutNode = node;
			this.nodeOffset = offset;
		}
	}

	/**
	 * Method for connecting two nodes.
	 */
	private void connectSomeNodes(Node currentNode, Node nextNode)
	{
		nextNode.previous = currentNode; // set the previous reference of nextNode to currentNode
		nextNode.next = currentNode.next; // set the next reference of nextNode to the node after currentNode
		currentNode.next.previous = nextNode; // update the previous reference of the node after currentNode to nextNode
		currentNode.next = nextNode; // update the next reference of currentNode to nextNode
	}

	/**
	 * Method for disconnecting a node.
	 */
	private void disconnectNode(Node currentNode)
	{
		currentNode.previous.next = currentNode.next; // update the next reference of the node
		currentNode.next.previous = currentNode.previous; // update the previous reference of the node
	}

	/**
	 * Method to find the StoutListNode (node and offset within node) corresponding
	 * to the given position in the list.
	 *
	 * @param position the position of the element in the list
	 * @return StoutListNode object containing the node and offset of the element
	 */
	private StoutListNode find(int position)
	{
		if (position == -1)
			return new StoutListNode(head, 0);

		if (position == size)
			return new StoutListNode(tail, 0);

		Node currentNode = head.next; // first node to begin searching
		int index = currentNode.count - 1; // initialize index

		while (currentNode != tail && position > index)
		{

			currentNode = currentNode.next; // Move to the next node

			// Increment the accumulated index by the count of elements in the current node
			index += currentNode.count;
		}
		int nodeOffset = currentNode.count + position - index - 1; // calculate offset
		return new StoutListNode(currentNode, nodeOffset);
	}

	/**
	 * Method to add elements to the given position.
	 */
	private StoutListNode add(Node stoutNode, E element, int nodeOffset)
	{
		if (element == null)
		{
			throw new NullPointerException();
		}

		StoutListNode newNode; // new node

		if (nodeOffset == 0)
		{
			if (size == 0)
			{
				Node newNodeData = new Node();
				newNodeData.addItem(element);
				connectSomeNodes(head, newNodeData); // connect the new node to the head node
				newNode = new StoutListNode(newNodeData, 0); // new StoutListNode representing the element
			}
			else if (stoutNode.previous.count < nodeSize && stoutNode.previous != head)
			{
				stoutNode.previous.addItem(element); // add the item to the previous node
				newNode = new StoutListNode(stoutNode.previous, stoutNode.previous.count - 1);
			}
			else if (stoutNode == tail && stoutNode.previous.count == nodeSize)
			{
				Node newNodeData = new Node();
				newNodeData.addItem(element); // add the item to the new node
				connectSomeNodes(tail.previous, newNodeData); // connect the new node to the node before the tail
				newNode = new StoutListNode(newNodeData, 0);
			}
			else
			{
				stoutNode.addItem(nodeOffset, element); // add the item to the current node at the specified offset
				newNode = new StoutListNode(stoutNode, nodeOffset);
			}
		}
		else
		{
			if (stoutNode.count < nodeSize)
			{
				stoutNode.addItem(nodeOffset, element); // add the item to the current node at the specified offset
				newNode = new StoutListNode(stoutNode, nodeOffset);
			}
			else
			{
				Node newNodeData = new Node();
				connectSomeNodes(stoutNode, newNodeData); // connect the current node to the new node

				for (int i = nodeSize - 1; i >= nodeSize - nodeSize / 2; i--)
				{
					newNodeData.addItem(0, stoutNode.data[i]);
					stoutNode.removeItem(i); // remove
				}

				if (nodeOffset <= nodeSize / 2)
				{
					stoutNode.addItem(nodeOffset, element); // add the item to the current node at the specified offset
					newNode = new StoutListNode(stoutNode, nodeOffset);
				}
				else
				{
					newNodeData.addItem(nodeOffset - nodeSize / 2, element); // add the item to the new node
					newNode = new StoutListNode(newNodeData, nodeOffset - nodeSize / 2);
				}
			}
		}

		size++; // increment size of the list
		return newNode;
	}

	/**
	 * Method to remove an element from StoutList at the given position.
	 */
	private E remove(StoutListNode node)
	{
		E item = node.stoutNode.data[node.nodeOffset]; // item to be removed

		if (node.stoutNode.next == tail && node.stoutNode.count == 1) // base case checking if second-to-last node
		{
			disconnectNode(node.stoutNode); // remove item from the node
		}

		else if (node.stoutNode.next == tail || node.stoutNode.count > nodeSize / 2)
		{
			node.stoutNode.removeItem(node.nodeOffset); // remove item from the node
		}

		else if (node.stoutNode.count <= nodeSize / 2)
		{
			node.stoutNode.removeItem(node.nodeOffset); // remove item from the node

			if (node.stoutNode.next.count > nodeSize / 2)
			{
				node.stoutNode.addItem(node.stoutNode.next.data[0]); // move item to the next node
				node.stoutNode.next.removeItem(0); // remove item from the next node
			}
			else // move all non-null items from the next node to the current node
			{

				for (E element : node.stoutNode.next.data)
				{
					if (element != null)
					{
						node.stoutNode.addItem(element);
					}
				}

				disconnectNode(node.stoutNode.next); // disconnect the next node from the list
			}
		}
		size--; // decrement the list
		return item;
	}

	/**
	 * Object comparator class to compare two Type E objects.
	 */
	private static class ObjectComparator<E extends Comparable<? super E>> implements Comparator<E>
	{
		@Override
		public int compare(E first, E second)
		{
			return first.compareTo(second);
		}
	}

}