package edu.ncsu.csc216.pack_scheduler.util;

public class ArrayStack<E> implements Stack<E> {
	/**ArrayList to hold elements */
	ArrayList<E> stack = new ArrayList<>();
	/** Number of elements in the stack. */
	int size;
	/** Capacity of the stack */
	int capacity;
	
	/**
	 * Constructor for the ArrayStack object. 
	 * @param capacity Capacity of the ArrayStack that's used to set
	 * the initial maximum number of elements that the stack can contain.
	 */
	public ArrayStack(int capacity) {
		setCapacity(capacity);
		this.size = 0;
	}
	
	/**
	 * Adds elements to the top of the stack.
	 * @param element Element to add to the top of the stack.
	 */
	@Override
	public void push(E element) {
		stack.add(element);
		this.size++;
		
	}

	/**
	 * Removes the top element from the stack.
	 * @return E the element at the top of the stack. 
	 */
	@Override
	public E pop() {
		E popped = stack.get(size - 1);
		stack.remove(this.size() - 1);
		this.size--;
		return popped;
	}

	/**
	 * Checks to see if elements are contained within the list.
	 * @return true if no elements exist within the stack and the
	 * size is zero.
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns the number of elements within the list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Sets the capacity of the stack. An IllegalArgumentException is
	 * thrown if the capacity is negative or less than the current
	 * stack size.
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) throw new IllegalArgumentException();
		this.capacity = capacity;
	}

}
