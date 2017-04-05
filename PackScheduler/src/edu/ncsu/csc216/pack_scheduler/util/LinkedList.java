package edu.ncsu.csc216.pack_scheduler.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList.ListNode;

//TODO: TEST AND FIX TYPE CAST ERRORS

public class LinkedList<E> extends java.util.AbstractSequentialList<E> {

	/**Front of the LinkedList */
	private ListNode<E> front;
	/**Last node of the LinkedList */
	private ListNode<E> back;
	/**Size of the LinkedList */
	private int size;
	
	/**
	 * Constructor for the LinkedList object. Two ListNode objects are
	 * initialized with null data and used to represent the outer bounds
	 * of the LinkedList. The size is initialized to 0;
	 */
	public LinkedList() {
		this.size = 0;
		this.front = new ListNode<>(null);
		this.back = new ListNode<>(null);
		this.front.next = this.back;
		this.back.prev = this.front;
	}
	
	/**
	 * Constructs an Iterator that will return the element at the
	 * specified index when a call to retreive the next element is 
	 * made.
	 * @param index Index to position the iterator behind.
	 * @return ListIterator The list iterator used to transverse the 
	 * LinkedList. 
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator<>(index);
		
	}

	@Override
	public int size() {
		return this.size();
	}
	
	
	private class LinkedListIterator<E> implements ListIterator<E> {
		
		/**ListNode that is returned on previous() method calls. */
		private ListNode<E> previous;
		/**ListNode that is returned on next() method calls. */
		private ListNode<E> next;
		/**Integer that is returned on previousIndex() method calls. */
		private int previousIndex;
		/**Integer that is returned on nextIndex() method calls. */
		private int nextIndex;
		/**ListNode that represents the last ListNode returned via the ListIterator. */
		private ListNode<E> lastRetrieved;
		
		/**
		 * Constructor for the LinkedListIterator. This method accepts an
		 * integer parameter that represents the index to position the 
		 * iterator
		 * @param index Index to position the iterator.
		 */
		public LinkedListIterator(int index) { 
			if(index < 0 || index > size()) throw new IllegalArgumentException();
			//counter for index
			int counter = 0;
			//E node to keep track of the current node.
			this.next = front;
			//E node to keep track of the previous node.
			this.previous = null;
			//While look to transverse the list. 
			while(counter < index) {
				counter++;
				//make previous point to the current node
				this.previous = this.next;
				this.previousIndex = counter - 1;
				//make the current node point to the next node.
				this.next = this.next.next;
				nextIndex = counter + 1;
			}
			this.lastRetrieved = null;
		}
			   
			

		@Override
		public boolean hasNext() {
			if(this.next.next != null) return true; // if next node is not null
			return false;
		}

		@Override
		public E next() {
			if(hasNext()) {
				this.lastRetrieved = next.next;
				return this.lastRetrieved.data;
			}//should be the data at INDEX from iterator call.
			else throw new NoSuchElementException();
		}

		@Override
		public boolean hasPrevious() {
			if(next != null) return true;
			return false;
			
		}

		@Override
		public E previous() {
			if(hasPrevious()) { //TODO check to see if this is right.. going off of the assumption next = index - 1 (from iterator)
				this.lastRetrieved = previous;
				this.previousIndex--; //update positions by moving backwards
				this.nextIndex--; //move iterator back (API)
				return this.lastRetrieved.data;
			}
			else throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		@Override
		public int previousIndex() {
			return this.previousIndex - 1;
		}

		@Override
		public void remove() {
			if(this.lastRetrieved == previous) //remove NEXT when last call was previous
				previous.next = next.next;
				//this.previous.prev.next = this.previous.next(); //change the link to skip over previous
			if(this.lastRetrieved == next) 
				this.previous.prev.next = this.previous.next;        
		}

		@Override
		public void set(E e) { 
			if(this.lastRetrieved == previous) //should be safe to work with next node if calling previous last.
				this.next.prev.data = e;
			if(this.lastRetrieved == next) //should be safe to work with previous node if next was called last
				this.previous.next.data = e;
			
		}

		@Override
		public void add(E e) {
		//either stop before null or before size()
			previous = front;
			next = front.next; // at index -1
			for(int i = 0; i < size(); i++){
				previous = next;
				next = next.next;
			}
			//should stop right before size, so next points to size
			previous.next = new ListNode(e, previous, previous.next);
			//change the link to previous.next to reference the new node.
			//new node should point to previous and previous.next. 
			
		}
		
	}
	
	
	//BEGIN LISTNODE CLASS
	private class ListNode<E> {
		
		private E data;
		private ListNode<E> next;
		private ListNode<E> prev;
		
		public ListNode(E data) {
			this.data = data;
		}
		
		public ListNode(E data, ListNode<E> prev, ListNode<E> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}	
	}
	//END LISTNODE CLASS

}
