package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
	/**ArrayList to use as queue */
	private ArrayList<E> queue;
	private int size = 0;
	private int capacity;
	
	public ArrayQueue(int capacity){
		queue = new ArrayList<>();
		this.size = 0;
		setCapacity(capacity);
	}

	@Override
	public void enqueue(E element) {
		if(size() == capacity) throw new IllegalArgumentException();
		queue.add(element);
		this.size++;		
	}

	@Override
	public E dequeue() {
		if(size() == 0) throw new NoSuchElementException();
		this.size--;
		return queue.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) throw new IllegalArgumentException();
		this.capacity = capacity;
	
	}

}
