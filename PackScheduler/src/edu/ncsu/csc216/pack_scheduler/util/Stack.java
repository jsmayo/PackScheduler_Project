package edu.ncsu.csc216.pack_scheduler.util;

public interface Stack<E> {

	/**
	 * Adds the element to the top of the Stack, if there is no room,
	 * then an illegalArgumentException will be thrown.
	 * @param element Element to add to the stack
	 * @throws IllegalArgumentException if the capacity of the stack
	 * has been reached.
	 */
	public void push(E element);

	
	/**
	 * Removes and returns the elements at the top of the Stack.
	 * If the stack is empty, then an EmptyStackException will be
	 * thrown.
	 * @throws EmptyStackException thrown when the stack is empty and
	 * a pop() method call is attempted.
	 */
	public E pop();
	
	/**
	 * Checks to see if the Stack is empty
	 * @return True if the Stack does not contain any elements.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack.
	 * @return Number of elements within the stack
	 */
	public int size();
	
	/**
	 * Set's the capacity of the stack. If the actual parameter is negative or
	 * less than the size of the current stack, then an IllegalArgumentException
	 * is thrown.
	 * @param capacity capacity of the stack.
	 * @throws IllegalArgumentException if the capacity parameter is given a 
	 * negative value or attempted to be set to a value less than the current
	 * stack size.
	 */
	public void setCapacity(int capacity);
	
	
}
