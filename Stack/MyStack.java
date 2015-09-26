
import java.util.ArrayList;
import java.util.EmptyStackException;


public class MyStack<E> implements MyStackInterface<E> {

	private ArrayList<E> arr;

	/**
	 * Constructor
	 */
	public MyStack() {
		arr = new ArrayList<E>();
	}

	/**
	 * Constructor
	 * @param arr - array of elements to be added to this stack. arr[0] is the first element to be pushed.
	 */
	public MyStack(E[] arr) {
		for(E element : arr) {
			this.arr.add(element);
		}
	}

	/**
	 * Tests if this stack is empty.
	 * @return true if and only if this stack contains no items; false otherwise.
	 *
	 * O(1)
	 */
	public boolean isEmpty() {
		return arr.size() <= 0;
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the stack.
	 * @return the object at the top of this stack.
	 * @throw EmptyStackException if this stack is empty.
	 *
	 * O(1)
	 */
	public E peek() {
		if(arr.isEmpty()){
			throw new EmptyStackException();
		}
		return arr.get(arr.size()-1);
	}

	/**
	 * Removes and returns the object at the top of this stack.
	 * @return the object at the top of this stack.
	 * @throw EmptyStackException if this stack is empty.
	 *
	 * O(1)
	 */
	public E pop() {
		if(arr.isEmpty()){
			throw new EmptyStackException();
		}
		
		return arr.remove(arr.size()-1);
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * @param item - the item to be pushed onto this stack.
	 * @return the item argument.
	 *
	 * O(1)
	 */
	public E push(E item) {

		arr.add(item);

		return item;
	}

}