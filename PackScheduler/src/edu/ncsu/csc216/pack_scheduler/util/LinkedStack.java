package edu.ncsu.csc216.pack_scheduler.util;

public class LinkedStack<E> implements Stack<E> {

	LinkedAbstractList<E> stack;
	int capacity;
	int size = 0;
	
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<>(capacity);
		
	}
	
	@Override
	public void push(E element) {
		if(isEmpty()) stack.add(0, element);
		else stack.add(size(), element); //size is next empty index;
		this.size++;
		
	}

	@Override
	public E pop() {
		if(isEmpty()) throw new IllegalArgumentException();
		E popped = stack.get(size() - 1);
		stack.remove(size() - 1);
		size--;
		return popped;
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
		this.capacity = capacity;
		
	}

}
