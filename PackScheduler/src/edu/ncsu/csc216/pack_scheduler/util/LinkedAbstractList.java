package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * LinkedAbstractList represents a customized LinkedList to be used
 * within the PackScheduler application. More specifically, a LinkedList
 * will be used to contain the CourseRoll Object of each Course Object within
 * the CourseCatalog class.
 * 
 * @author Steven Mayo
 * @param <E> Generic parameter of type E.
 */
public class LinkedAbstractList<E> extends AbstractList <E> {
	/** A ListNode of type E. */
	ListNode front;
	/** A ListNode of Type E */
	ListNode back;
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;


	/**
	 * Constructor for the LinkedList Object that's used to represent
	 * the course roll of each Course within the Course Catalog. 	
	 * @param capacity The maximum number of objects that the LinkedList
	 * can contain.
	 * @throws IllegalArgumentException if the specified capacity is less than zero.
	 */
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
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
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
		return previous.next.data;

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
		for(int i = 0; i < this.size(); i++)  
			if(this.get(i).equals(e)) throw new IllegalArgumentException("Cannot add duplicate values");
		if(index == 0 && size() == 0) {
			this.front = new ListNode(e);
			front.next = null;
			this.back = front.next; //back references the last in the list.
			this.size++;
			return;
		}	
		//this will run as long as index != 0, but that's taken care of above.
		ListNode previous = null;
		ListNode current = this.front;
		while(current != null) {
			previous = current;
			current = current.next;
		}
		this.back = previous; //make back point to last node;

		if(index == 0 && size() > 0) {
			ListNode newHead = new ListNode(e, this.front);
			this.front = newHead;
			this.size++;
			return;
		}

		else {
			previous = null;
			current = this.front;
			int counter = 0;
			while(counter < index) {
				previous = current; //current is front -> data
				current = current.next; // is link -> next
				counter++;
			}
			previous.next = new ListNode(e, current); //point insert from previous=current -> current.next=link
			this.size++;
			return;
		}
	}

	/**
	 * Removes an object from the LinkedList using the specified index
	 * parameter.
	 * @param index Index of the object to remove.
	 * @throws IndexOutOfBoundsException if the index is outside of the 
	 * LinkedList boundary.
	 */
	@Override
	public E remove(int index) {
		if(index >= this.size || index < 0 ) throw new IndexOutOfBoundsException();
		//counter for index
		int counter = 0;
		//E node to keep track of the current node.	
		//this will run as long as index != 0, but that's taken care of above.
		ListNode previous = null;
		ListNode current = this.front;
		while(current != null) {
			previous = current;
			current = current.next;
		}
		this.back = previous; //make back point to last node;

		current = front;
		if(index == 0) {
			this.front = front.next;
		}
		else {
			//E node to keep track of the previous node.
			 previous = null;
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

	/**
	 * Returns the capacity of the LinkedList Object. 
	 * @return Capacity of the LinkedList.
	 */
	protected int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < this.size()) throw new IllegalArgumentException();
		this.capacity = capacity;
	}

	/**
	 * Set's the specified index of the LinkedList to that of the Object
	 * passed in as the second parameter.
	 * @param index Index of the Object to overwrite within the LinkedList.
	 * @param e Object to overwrite the current Object at the specified index
	 * with.
	 * @throws IndexOutOfBoundsException if the specified index is outside of the
	 * LinkedList boundaries.
	 * @throws IllegalArgumentException if the specified object is a duplicate of 
	 * an object already within the LinkedList.
	 */
	@Override
	public E set(int index, E e){
		if(e == null) throw new NullPointerException();
		if(index >= size()  || index < 0) throw new IndexOutOfBoundsException();
		for(int i = 0; i < this.size(); i++) 
			if(this.get(i).equals(e)) throw new IllegalArgumentException("Cannot add duplicate values");

		//have to be less than size, so at least 1 node in list by this point.
		if(index == 0) { 
			E replaced = this.get(0);
			front = new ListNode(e, front.next);
			return replaced;
		}

		else {
			ListNode previous = null;
			ListNode current = this.front;
			int counter = 0;
			while(counter < index) {
				previous = current; //current is front -> data
				current = current.next; // is link -> next
				counter++;
			}
			E replaced = this.get(index); // = current.next.data;

			previous.next = new ListNode(e, current.next);
			//previous.next = new ListNode(e, current); //point insert from previous=current -> current.next=link

			return replaced;
		}
	}

	/**
	 * Returns the number of objects currently within the LinkedList.
	 * @return The number of objects within the LinkedList.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Private class representing an individual Node within the LinkedList.
	 * 
	 * @author Steven Mayo
	 */
	private class ListNode {

		/** Object to insert into the LinkedList */
		private E data;
		/** Link to the next Object within the LinkedList */
		private ListNode next;

		/**
		 * Constructor for the first ListNode within the LinkedList.
		 * @param data Object to insert into the LinkedList.
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * Constructor for a ListNode Object with reference to the next
		 * ListNode within the LinkedList.
		 * @param data Object to insert into the LinkedList.
		 * @param next Reference to the next LinkedList Object within the
		 * LinkedList.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
