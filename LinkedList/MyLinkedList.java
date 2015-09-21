
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
		boolean can_remove = false;
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
				can_remove = true;
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
			if(!can_remove){
				throw new IllegalStateException();
			}
			can_remove = false;
			pos.previous.next = pos.next;
			pos.next.previous = pos.previous;
			pos = pos.previous;

			--size;

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
		
		add(size(),element);

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
		if(index < 0 || size() < index){
			throw new IndexOutOfBoundsException();
		}


		if(index < size()/2){
			pos = head;

			for(int i = 0; i < index; ++i){
				pos = pos.next;
		 	}
		}

		else{
			pos = tail.previous;

		 	for(int i = size(); i > index; --i){
		 		pos = pos.previous;
		 	}
		}

		Node new_node = new Node();

		new_node.data = element;
		new_node.next = pos.next;
		new_node.previous = pos;
		new_node.previous.next = new_node;
		new_node.next.previous = new_node;

		++size;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator (optional operation). The behavior of this operation
	 * is undefined if the specified collection is modified while the operation
	 * is in progress.
	 *
	 * Returns true if the list has changed. 
	 *
	 * O(c) where c is the number of elements in the Collection
	 */
	public boolean addAll(Collection<? extends E> c) {
		if(c.size() <= 0){
			return false;
		}

		for(E element : c){
			add(element);
		}

		return true;
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
		size = 0;
	}

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * O(n)
	 */
	public boolean contains(Object obj) {
		// return indexOf(obj) != -1;
		for(E element: this) {
			if(element.equals(obj)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the
	 * specified collection.
	 *
	 * O(n^2) time, O(1) space
	 */
	public boolean containsAll(Collection<?> c) {
		
		for(Object element : c){
			if(!contains(element)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the element at the specified pos in this list.
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 *
	 * O(n)
	 */
	public E get(int index) {
		if(index < 0 || size() <= index){
			throw new IndexOutOfBoundsException();
		}
		
		pos = head.next;

		for(int i = 0; i < index; ++i){
			pos = pos.next;
		}

		return pos.data;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 *
	 * O(n)
	 */
	public int indexOf(Object obj) {
		
		pos = head.next;

		for(int i = 0; i < size(); ++i){
			if(pos.data.equals(obj)){
				return i;
			}
			pos = pos.next;
		}
		return -1;
	}

	/**
	 * Returns true if this list contains no elements, false otherwise.
	 *
	 * O(1)
	 */
	public boolean isEmpty() {
		
		return size() == 0;
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
		
		pos = tail.previous;

		for(int i = size()-1; i >= 0; --i){
			if(pos.data.equals(obj)){
				return i;
			}
			pos = pos.previous;
		}
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

		int idx = indexOf(obj);

		if(-1 != idx){
			remove(idx);
		}

		return -1 != idx;


		//Iterator itr = iterator();
		//for(int i = 0; i < size(); ++i){
		//	if(itr.next().equals(obj)){
		//		itr.remove();
		//		return true;
		//	}
		//}
		//return false;
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
		
		boolean changed = false;

		Iterator itr = iterator();

		while(itr.hasNext()){
			if(c.contains(itr.next())){
				itr.remove();
				changed = true;
			}
		}

		return changed;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified
	 * collection. In other words, removes from this list all of its elements
	 * that are not contained in the specified collection.
	 *
	 * same complexity as removeAll()
	 */
	public boolean retainAll(Collection<?> c) {
		
		boolean changed = false;

		Iterator itr = iterator();

		while(itr.hasNext()){
			if(!c.contains(itr.next())){
				itr.remove();
				changed = true;
			}
		}

		return changed;
	}

	/**
	 * Replaces the element at the specified pos in this list with the specified element.
	 * Throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 *
	 * O(n)
	 */
	public E set(int index, E element) {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}

		if(index < size()/2){
			pos = head.next;
			for(int i = 0; i < index; ++i){
				pos = pos.next;
		 	}
		}

		else{
			pos = tail.previous;
		 	for(int i = size()-1; i > index; --i){
		 		pos = pos.previous;
		 	}
		}

		E old = pos.data;
		pos.data = element;

		return old;
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
		
		Object[] arr = new Object[size()];

		int counter = 0;

		for(E element: this){
			arr[counter] = element;
			++counter;
		}

		return arr;
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
	    	result.append(element/*.toString()*/ + ", ");
	    }
	    result.append("]");
	    return result.toString();
	}
}