package edu.ncsu.csc216.pack_scheduler.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Linked List that has been custom tailored for the PackScheduler application.
 * @author Steven Mayo
 *
 * @param <E> Generic element of Type E to be added as a ListNode elmeent of LinkedList. 
 */
public class LinkedList<E> extends java.util.AbstractSequentialList<E> {

	/**Front of the LinkedList */
	private ListNode front;
	/**Last node of the LinkedList */
	private ListNode back;
	/**Size of the LinkedList */
	private int size;
	
	/**
	 * Constructor for the LinkedList object. Two ListNode objects are
	 * initialized with null data and used to represent the outer bounds
	 * of the LinkedList. The size is initialized to 0;
	 */
	public LinkedList() {
		this.size = 0;
		this.front = new ListNode(null); //null data on front
		front.prev = null; // null node to previous
		this.back = new ListNode(null); //null data on back
		this.front.next = this.back; //pointer to back data with empty list
		this.back.prev = this.front; //points to data on front
		this.back.next = null; //points to null with next call on back
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
		return new LinkedListIterator(index);
		
	}
	
	
	@Override
	public void add(int index, E element) {
		if(element == null) throw new NullPointerException();
		if(this.contains(element)) throw new IllegalArgumentException();
		if(index < 0 || (size == 0 && index !=0) || (size() > 0 && index > size())) throw new IndexOutOfBoundsException();
		ListIterator<E> itor = listIterator(index);
		itor.add(element); 
		//LinkedList needs to call LinkedListIterator's add method
		//b/c it's already positioned to add in the correct spot and cannot check for duplicates without losing it's spot. 
	}
//
//	@Override
//	public void add(int index, E element) {
//		if(this.contains(element)) throw new IllegalArgumentException();
//		super.add(index, element);
//	}

	@Override
	public int size() {
		return this.size;
		
	}
	

	
	
	@Override
	public E set(int index, E element) {
		if(element == null) throw new NullPointerException();
		if(this.contains(element)) throw new IllegalArgumentException();
		ListIterator<E> itor = listIterator(index); //in position to call next
		E replaced = itor.next(); //get E by calling next
		itor.set(element); //replace the last call, which should be the E returned.
		return replaced;
	}



/**
 * Iterator for the LinkedList class.
 * @author Steven
 *
 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/**ListNode that is returned on previous() method calls. */
		private ListNode previous;
		/**ListNode that is returned on next() method calls. */
		private ListNode next;
		/**Integer that is returned on previousIndex() method calls. */
		public int previousIndex;
		/**Integer that is returned on nextIndex() method calls. */
		public int nextIndex;
		/**ListNode that represents the last ListNode returned via the ListIterator. */
		private ListNode lastRetrieved;
		
		/**
		 * Constructor for the LinkedListIterator. This method accepts an
		 * integer parameter that represents the index to position the 
		 * iterator
		 * @param index Index to position the iterator.
		 */
		public LinkedListIterator(int index) { 
			if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
			//counter for index
			int counter = 0;
			//E node to keep track of the current node.
			previous = front;
			//E node to keep track of the previous node.
			next = front.next;
			//While look to transverse the list. 
			while(counter < index) {
				//make previous point to the current node
				previous = next;
				previousIndex = counter;
				//make the current node point to the next node.
				next = next.next;
				nextIndex = counter + 1;
				counter++;
				
			}
			this.lastRetrieved = null;
		}
			   
			

		/**
		 * Checks to see if there is another element on the linked list.
		 * @return true if the list is not empty. 
		 */
		@Override
		public boolean hasNext() {
			if(next != null) return true; // if next node is not null
			return false;
		}

		/**
		 * Returns the next elment on the LinkedList.
		 * @return Next Element on the linked list.
		 */
		@Override
		public E next() {
			if(hasNext()) {
				previous = next; //move
				previousIndex++;
				next = next.next; //move
				nextIndex++;
				lastRetrieved = previous; //get data;
				return lastRetrieved.data;
			}//should be the data at INDEX from iterator call.
			else throw new NoSuchElementException();
		}

		/**
		 * Checks to see if there is a previous element on the LinkedList.
		 * @return true if the previous element is not empty.
		 */
		@Override
		public boolean hasPrevious() {
			if(previous != null) return true;
			return false;
			
		}

		/**
		 * Returns the previous element on the LinkedList.
		 * @return E Previous element on the LinkedList.
		 */
		@Override
		public E previous() {
			if(hasPrevious()) { //TODO check to see if this is right.. going off of the assumption next = index - 1 (from iterator)
				next = previous; //move next back one node
				nextIndex--;
				previous = previous.prev; //move previous back one node
				previousIndex--;
				lastRetrieved = next; //set LR to the "previous call"
				return lastRetrieved.data; //return the data
			}
			else throw new NoSuchElementException();
		}

		/**
		 * Returns the next index number of the list iterator. 
		 * @return next index number
		 */
		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		/**
		 * Returns the previous index number for the list iterator.
		 * @return previous index number.
		 */
		@Override
		public int previousIndex() {
			return this.previousIndex;
		}

		/**
		 * Removes the last element called via next() or previous() from the LinkedList.
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) throw new IllegalStateException();
			if(lastRetrieved == previous) {
				previous.prev.next = previous.next;
				next.prev = previous.prev;
			}
			else if(lastRetrieved == next) {
				previous.next = next.next; //point last node to infront of last called
				next.next.prev = next.prev;  //point the node after skipped, to the previous.
			}
//			previous.next = lastRetrieved.next;
//			next.prev = lastRetrieved.prev;
			size--;
			nextIndex--;
			
			
				        
		}

		/**
		 * Sets the value of the Element on the LinkedList at the index of the ListIterator
		 */
		@Override
		public void set(E e) { 
			if(e == null) throw new NullPointerException();
			if(lastRetrieved == null) throw new IllegalStateException();
			else lastRetrieved.data = e;
//		
//			if(this.lastRetrieved == previous) //should be safe to work with next node if calling previous last.
//				this.next.prev.data = e;
//			if(this.lastRetrieved == next) //should be safe to work with previous node if next was called last
//				this.previous.next.data = e;
			
		}

		/**
		 * Adds an element to the linked list.
		 * @throws NullPointerException if passed in parameter is null.
		 * 
		 */
		@Override
		public void add(E e) {
			if(e == null) throw new NullPointerException();
//			if(size() == 0 ) {
//				ListNode newNode = new ListNode(e, front, front.next);
//				front.next = newNode;
//				back.prev = newNode;
//				size++;
//				nextIndex++;
//			}
//			else {
				ListNode newNode = new ListNode(e, previous, previous.next);
				previous.next = newNode;
				next.prev = newNode;
				lastRetrieved = null;
				size++;	
				nextIndex++;
			}
		
		//Previous attempt: 
//		@Override
//		public void add(E e) {
//			if(e == null) throw new NullPointerException();
//
//		else {
//				ListNode newNode = new ListNode(e, next.prev, next);
//				next = newNode.next;
//				nextIndex++;
//				next.prev = newNode;
//				previousIndex++;
//				size++;
//		}
//		}
//	}
				
				
		
		
//			ListNode addToBack = new ListNode(e, back.prev, back); //new node pointing to back's current prev link and to back
//			back.prev.next = new ListNode(e, back.prev, back); //change link of back.prev.next
//			back.prev = addToBack;
//			size++;
			
		
		

	}
	
	
	//BEGIN LISTNODE CLASS
	/**
	 * ListNode class that provides structure for each element within the LinkedList.
	 * @author Steven Mayo
	 *
	 */
	private class ListNode{
		
		/** data of the node */
		private E data;
		/** Next Element in the LinkedList */
		private ListNode next;
		/** Previous Element in the LinkedList */
		private ListNode prev;
		
		/**
		 * Null constructor for a ListNode
		 * @param data data value of the list node.
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * Constructor for a list node with links to next and previous nodes.
		 * @param data data of the node.
		 * @param prev Previous node in the LinkedList.
		 * @param next  Next node in the LinkedList.
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}	
	}
	//END LISTNODE CLASS

}
