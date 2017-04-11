package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Container class that incorporates the behavior of a LinkedList with that
 * of a Stack. 
 * 
 * @author Steven Mayo
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

	LinkedAbstractList<E> stack;
	int capacity;
	int size = 0;
	
	/**
	 * Constructor for LinkedStack. 
	 * @param capacity Capacity of the Stack.
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<>(capacity);
		
	}
	
	/**
	 * Adds an element to the top the Stack.
	 * @param element Element to add to the top of the Stack.
	 */
	@Override
	public void push(E element) {
		if(isEmpty()) stack.add(0, element);
		else stack.add(size(), element); //size is next empty index;
		this.size++;
		
	}

	/**
	 * Removes an element from the top of the Stack.
	 * @throws IllegalArgumentException if an empty Stack attempts to
	 * remove an element.
	 * @return Element from the top of the Stack.
	 */
	@Override
	public E pop() {
		if(isEmpty()) throw new IllegalArgumentException();
		E popped = stack.get(size() - 1);
		stack.remove(size() - 1);
		size--;
		return popped;
	}

	/**
	 * Checks to see if there are any items within the Stack.
	 * @return True if the stack is empty. 
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Returns the size of the current Stack.
	 * @return Number of elements contained within the Stack.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Sets the capacity of the Stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
		this.capacity = capacity;
		
	}

}
