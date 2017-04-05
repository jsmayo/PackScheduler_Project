package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {

	
	private LinkedAbstractList<E> queue;
	private int size = 0;
	private int capacity;
	
	public LinkedQueue(int capacity) {
		queue = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	
	@Override
	public void enqueue(E element) {
		//LinkedAbstractList should delegate if capacity == size, but put code in to remove checkstyle warning.
		if(this.size() == this.capacity) throw new IllegalArgumentException();
		if(isEmpty()) queue.add(0, element); //add to front if empty
		else queue.add(size(), element); //add to last position if not empty
		size++;
		
	}

	@Override
	public E dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		this.size--;
		return queue.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void setCapacity(int capacity) {
		queue.setCapacity(capacity); //delegated to LinkedAbstractList
		this.capacity = capacity;
		
	}

}
