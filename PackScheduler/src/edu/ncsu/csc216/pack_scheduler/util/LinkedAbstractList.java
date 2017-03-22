package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;


public class LinkedAbstractList<E> extends AbstractList <E> {
	/** A ListNode of type E. */
	ListNode front;
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;


	
	
	public LinkedAbstractList(int capacity) {
		if(capacity < 0) throw new IllegalArgumentException("Capacity must be greater than 0");
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
	}

	/**
	 * Getter method for the LinkedList. The node at the specified
	 * index is returned.
	 * @param index Index of the specified node to retrieve. 
	 * @return E The node at the specified index.
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();
		if(index == 0) return this.front.data;
		//counter for index
		int counter = 0;
		//E node to keep track of the current node.
		ListNode current = front;
		//E node to keep track of the previous node.
		ListNode previous = null;
		//While look to transverse the list. 
		while(counter < index) {
			counter++;
			//make previous point to the current node
			previous = current;
			//make the current node point to the next node.
			current = current.next;
		}
		return current.data;
		
		//if the loop exits, then the index was found or the end was reached.
		//overwrite the previous.next to a new reference (previous.next was the pointer to the last compared node)
		
	}
	
	
	/**
	 * Adds an object of type E to this objects Array at the specified index value. 
	 * If the size of the Array is approaching the capacity, then the Array will 
	 * automatically double in capacity. IF a null value is passed in as an
	 * argument or if an index value is passed in that is outside of the 
	 * bounds, then an exception will be thrown.
	 * @param index The index value to insert the object into the ArrayList.
	 * @param e The object, of type E, to place at the specified index. 
	 * @throws IndexOutOfBoundsException if the index is outside of the array
	 * boundaries
	 * @throws IllegalArgumentException if the specified value is a duplicate already
	 * found within the list.
	 */
	@Override
	public void add(int index, E e){
		if(this.size == this.capacity) throw new IllegalArgumentException("Cannot add any more values");
		if(e == null) throw new NullPointerException();
		if(index > size()  || index < 0) throw new IndexOutOfBoundsException();
		if(index == 0 && size() == 0) {
			this.front = new ListNode(e);
			this.size++;
			return;
		}
		else if(index == 0 && size() > 0) {
			ListNode newHead = new ListNode(e, this.front);
			this.front = newHead;
			this.size++;
			return;
		}
		
		else {
			ListNode previous = null;
			ListNode current = this.front;
			int counter = 0;
			while(counter < index) {
				previous = current; //current is front -> data
				current = current.next;// is link -> next
				counter++;
			}
			previous.next = new ListNode(e, current); //point insert from previous=current -> current.next=link
			this.size++;
			return;
		}
	}
	
	@Override
	public E remove(int index) {
		if(index >= this.size || index < 0 ) throw new IndexOutOfBoundsException();
		//counter for index
		int counter = 0;
		//E node to keep track of the current node.
		ListNode current = front;
		if(index == 0) {
			this.front = front.next;
		}
		else {
			//E node to keep track of the previous node.
			ListNode previous = null;
			//While look to transverse the list. 
			while(counter != index) {
				//make previous point to the current node
				previous = current;
				//make the current node point to the next node.
				current = current.next;
				counter++;
			}
			//counter == index if exited, so set the previous to point to new and new to current.
			previous.next = current.next;
		}
		this.size--;
		return current.data;
	}

	protected int getCapacity() {
		return this.capacity;
	}
	

	@Override
	public E set(int index, E e){
		ListNode current = front;
		//if p==null, then the next node does not exist. Check for equality and continue to next node.
		while(current != null) {
			if(current.data.equals(e)) throw new IllegalArgumentException("Cannot add duplicate values");
			else current = current.next;
		}
		if(e == null) throw new NullPointerException();
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		if(index == 0 || size == 1) return this.front.data = e;
		else {
			//counter for index
			int counter = 0;
			//E node to keep track of the current node.
			current = front;
			//E node to keep track of the previous node.
			ListNode previous = null;
			//While look to transverse the list. 
			while(counter < index) {
				counter++;
				//make previous point to the current node
				previous = current;
				//make the current node point to the next node.
				current = current.next;
			}
			//current should point to the spot needing replacement
			//E oldValue = previous.next.data; //current.data;
			return current.data = e;
			//return oldValue;
		}
		
	}

	@Override
	public int size() {
		return this.size;
	}
	
	private class ListNode {
		
		private E data;
		private ListNode next;
		
		public ListNode(E data) {
			this.data = data;
		}
		
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
