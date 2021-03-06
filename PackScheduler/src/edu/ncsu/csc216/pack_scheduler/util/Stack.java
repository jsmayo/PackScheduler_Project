package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface used to declare the behavior that all classes must define
 * when implementing Stack as one of their container classes.
 * @author Steven Mayo
 *
 * @param <E> Generic of Type E.
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the Stack, if there is no room,
	 * then an IllegalArgumentException will be thrown.
	 * @param element Element to add to the stack
	 */
	public void push(E element);

	
	/**
	 * Removes and returns the elements at the top of the Stack.
	 * If the stack is empty, then an EmptyStackException will be
	 * thrown.
	 * @return E Value of the element at the top of the Stack.
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
