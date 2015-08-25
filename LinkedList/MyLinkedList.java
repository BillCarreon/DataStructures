
import java.util.Collection;
import java.util.Iterator;

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

/*
 * This file provides skeleton code for a linked list implementation. This class
 * implements the MyList<E> interface, which is a subset of the java List<E>
 * interface. See method descriptions in the MyList.java file.
 *
 * It is up to you whether to use singly-linked or doubly-linked nodes. Doubly-
 * linked nodes make the code easier, however they take up more memory. There is
 * a solution for both doubly- and singly-linked nodes.
 *
 * Hint: Implement the Node class and the iterator methods first.
 */
public class MyLinkedList<E> implements MyList<E>, Iterable<E> {

	private class Node {
		//data, next, previous
		E data;
		Node next;
		Node previous;
	}

	/**
	 * Implements the java Iterator interface.
	 */
	private class MyIterator implements Iterator<E> {

		Node pos;
		boolean call_remove = false;
		/**
		 * Constructor
		 *
		 * O(1)
		 */
		public MyIterator() {
			pos = head;
		}

		/**
		 * Returns true if the iteration has more elements. In other words,
		 * returns true if next() would return an element rather than throwing an exception.
		 *
		 * O(1)
		 */
		public boolean hasNext() {
			return pos.next != tail;
		}

		/**
		 * Returns the next element in the iteration and advances the iterator.
		 *
		 * Throws NoSuchElementException if the iteration has no more elements.
		 *
		 * O(1)
		 */
		public E next() {
			if(hasNext()){
				call_remove = true;
				pos = pos.next;
				return pos.data;
			}
			throw new NoSuchElementException();
		}

		/**
		 * Removes from the underlying collection the last element returned by
		 * this iterator. This method can be called only once per call to next().
		 * The behavior of an iterator is unspecified if the underlying collection
		 * is modified while the iteration is in progress in any way other than
		 * by calling this method.
		 *
		 * Throws IllegalStateException if the next method has not yet been
		 * called, or the remove method has already been called after the last
		 * call to the next method.
		 *
		 * O(1)
		 */
		public void remove() {
			if(!call_remove){
				throw new IllegalStateException();
			}
			call_remove = false;
			pos.previous.next = pos.next;
			pos.next.previous = pos.previous;
			pos = pos.previous;

		}

	}

	private Node head;
	private Node tail;
	private int size;
	private Node pos;

	/**
	 * Constructor
	 *
	 * O(1)
	 */
	public MyLinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
	}

	/**
	 * Constructor
	 *
	 * O(n)
	 */
	public MyLinkedList(E[] elements) {
		this();  // calls the first constructor
		size = 0;
		for(int i = 0; i < elements.length; ++i) {
			add(elements[i]);
		}
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * O(1)
	 */
	public boolean add(E element) {
		//int size = size();
		//add(int size,E element);
		pos = tail.previous;

		Node new_node = new Node();
		new_node.next = pos.next;
		new_node.previous = pos.next.previous;

		new_node.previous.next = new_node;
		new_node.next.previous = new_node;
		//setPointers(new_node);
		++size;
		return true;
	}

	/**
	 * Inserts the specified element at the specified pos in this list.
	 * Shifts the element currently at that pos (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 *
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
	 *
	 * O(n)
	 */
	public void add(int index, E element) {
		 if(index < 0 || index >= size()){
		 	throw new IndexOutOfBoundsException();
		 }
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator (optional operation). The behavior of this operation
	 * is undefined if the specified collection is modified while the operation
	 * is in progress.
	 *
	 * O(c) where c is the number of elements in the Collection
	 */
	public boolean addAll(Collection<? extends E> c) {
		// your code here
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * The list will be empty after this call returns.
	 *
	 * O(1)
	 */
	public void clear() {
		head.next = tail;
		tail.previous = head;
	}

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * O(n)
	 */
	public boolean contains(Object obj) {
		/*
		 *Iterator<E> iterator = iterator();
		 *pos = head;
		 *while(pos.next != tail){
		 *	if(pos.data == element){
		 *		return true;
		 *	}
		 *	iterator.next();
		 *}
		 */
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the
	 * specified collection.
	 *
	 * O(n^2) time, O(1) space
	 */
	public boolean containsAll(Collection<?> c) {
		// your code here
		return false;
	}

	/**
	 * Returns the element at the specified pos in this list.
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 *
	 * O(n)
	 */
	public E get(int index) {
		/* if(index < 0 || index >= size()){
		 * 	throw new IndexOutOfBoundsException();
		 * }
		 *
		 * Iterator<E> itr = iterator();
		 * while(true){
		 * 	if(itr)
		 * 	itr.next();
		 * }
		 */
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 *
	 * O(n)
	 */
	public int indexOf(Object obj) {
		// your code here
		return -1;
	}

	/**
	 * Returns true if this list contains no elements, false otherwise.
	 *
	 * O(1)
	 */
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 *
	 * O(1)
	 */
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * O(n)
	 */
	public int lastIndexOf(Object obj) {
		// your code here
		return -1;
	}

	/**
	 * Removes the element at the specified pos in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 *
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 *
	 * O(n)
	 */
	public E remove(int index) {

		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}

		Iterator<E> itr = iterator();
		E removed;
		
		for(int i = 0; i < index; ++i){
			itr.next();
		}

		removed = itr.next();
		itr.remove();
		
		return removed;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If this list does not contain the element, it is unchanged.
	 * Returns true if this list contained the specified element (or equivalently,
	 * if this list changed as a result of the call).
	 *
	 * O(n)
	 */
	public boolean remove(Object obj) {
		// your code here
		return false;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection.
	 *
	 * Complexity of removeAll depends on complexity of c.contians().
	 * Suppose Collection c has C elements.
	 * If c.contains() is O(1), then removeAll() is O(n).			Ex. HashSet
	 * If c.contains() is O(C), then removeAll() is O(n*C).			Ex. LinkedList or unsorted ArrayList
	 * If c.contains() is O(log C) then removeAll() is O(n*log C)	Ex. Sorted ArrayList or binary search tree
	 */
	public boolean removeAll(Collection<?> c) {
		// your code here
		return false;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified
	 * collection. In other words, removes from this list all of its elements
	 * that are not contained in the specified collection.
	 *
	 * same complexity as removeAll()
	 */
	public boolean retainAll(Collection<?> c) {
		// your code here
		return false;
	}

	/**
	 * Replaces the element at the specified pos in this list with the specified element.
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 *
	 * O(n)
	 */
	public E set(int index, E element) {
		// your code here
		return null;
	}

	/*
	 *Sets the "previous" and "next" pointers of the New node and repalces like pointers of 
	 *the Previous and Next nodes.
	 */
	public void setPointers(Node new_node){
		new_node.next = pos.next;
		new_node.previous = pos.next.previous;
		new_node.previous.next = new_node;
		new_node.next.previous = new_node;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * O(1)
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). The returned array will be "safe"
	 * in that no references to it are maintained by this list. (In other words,
	 * this method must allocate a new array even if this list is backed by an
	 * array). The caller is thus free to modify the returned array.
	 *
	 * O(n)
	 */
	public Object[] toArray() {
		// your code here
		return null;
	}

	/**
	 * Compares the specified object with this list for equality. Returns true
	 * if and only if the specified object is also a list, both lists have the
	 * same size, and all corresponding pairs of elements in the two lists are equal.
	 * In other words, two lists are defined to be equal if they contain the
	 * same elements in the same order.
	 *
	 * This definition ensures that the equals method works properly across
	 * different implementations of the MyList interface.
	 *
	 * O(n)
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof MyList)) {
			return false;
		}

		MyList other = (MyList<?>)obj;
		if(other.size() != size()) {
			return false;
		}

		Iterator<E> iter1 = iterator();
		Iterator<?> iter2 = other.iterator();
		while(iter1.hasNext()) {
			if(!(iter1.next().equals(iter2.next()))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns a String representation of this list.
	 *
	 * O(n)
	 */
	public String toString() {
	    StringBuilder result = new StringBuilder(size() + 2);
	    result.append("[");
	    for(E element : this) {
	    	result.append(element.toString() + ", ");
	    }
	    result.append("]");
	    return result.toString();
	}
}