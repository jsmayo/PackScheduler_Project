package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom implementation of a recursive linked list 
 * that doesn't allow for null elements or duplicate elements as 
 * defined by {@link #equals()}.
 * 
 * @author Steven Mayo
 * @param <E> Generic Object of Type E contained within the LinkedList 
 * implementation.
 */
public class LinkedListRecursive<E> {
	
	/** First ListNode of the LinkedList */
	ListNode front;
	/** Size of the LinkedList */
	private int size;
	/** LinkedList object */
	//LinkedList<E> list;
	
	
	/**
	 * Constructor for LinkedListRecursive
	 */
	public LinkedListRecursive() {
		//list = new LinkedList<>();
		this.size = 0;
		front = null;
	}
	
	/**
	 * Checks to see if LinkedListRecursive object is empty.
	 * @return True if no objects are contained within the LinkedList.
	 */
	public boolean isEmpty(){
		return front == null; //if front is null, then the first element was never set and no elements exist.
		
	}

	/**
	 *  Returns the number of elements within the LinkedList.
	 *  @return size Size of the LinkedList; Number of elements within the 
	 *  LinkedList.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Attempts to add the element e of type E at the end of the LinkedList.
	 * @param e Element to add to end of the LinkedList.
	 * @return True if the element was added successfully. 
	 */
	public boolean add(E e) {
		if(!isEmpty()) { // delegate if not empty
			if(front.data.equals(e))  throw new IllegalArgumentException("duplicate found");
			return front.add(e); //inner LN class handles this.
		}
		else front = new ListNode(e, front.next);
		this.size++;
		return true;
	}
	
	/**
	 * Attempts to add the given element at the given index. 
	 * @param e element to add to the LinkedList.
	 * @param index Index to add the given element at.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public void add(int index, E e) {
		if(index < 0 || index >= size() || (isEmpty() && index != 0) )throw new IndexOutOfBoundsException();
		if(e == null) throw new NullPointerException();
		if(index == 0) {
			front = new ListNode(e, front);
			size++;
		}
		else {
			front.add(index, e);
		}
	}

	/**
	 * Returns the element at the given index.
	 * @param index Index to retrieve the element from.
	 * @return e Element of type E at the given index.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return front.get(index);
	}
	
	/**
	 * Attempts to remove the given element from the LinkedList.
	 * @param e Element to remove.
	 * @return True if the element was removed, null if not found.
	 */
	public boolean remove(E e){
		if(isEmpty()) return false;
		if(front.data.equals(e)) { //handle the first match aka base case
			front = new ListNode(front.next.data, front.next.next); //skip if matched.
			this.size--;
			return true;
		}
		else return front.remove(e);
	}
	
	/**
	 * Attempts to remove the given element from the LinkedList at the given index.
	 * @param index Index to remove the LinkedList element from. 
	 * @return removed Element removed or null if not contained within the list.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E remove(int index){
		if(index < 0 || index >= size() || (size() == 0 && index != 0)) throw new IndexOutOfBoundsException();
		if(index == 0) {
			E removed = front.data;
			front = new ListNode(front.next.data, front.next.next);
			size--;
			return removed;
		}
		return front.remove(index);
	}
	
	/**
	 * Sets the element at the given index to the value of the given element.
	 * @param index Index to set the new value.
	 * @param e Element to assigned to the given index.
	 * @return replaced The value replaced at the given index. 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E set(int index, E e) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		else return front.set(index, e);
	}
	
	/**
	 * Checks for an instance of the given element within the LinkedList.
	 * @param e Element to search the LinkedList for. 
	 * @return True if the given element is contained within the list.
	 */
	public boolean contains(E e) {
		if(!isEmpty()) { //if not empty on the first instance, let the instance call itself
			return front.contains(e);
			}
		return false;
	}
	
	
	
	
	//###################] Start ListNode Class [#################################
	
	
	
	/**
	 * Constructor for the inner ListNode class. 
	 * @author Steven Mayo
	 */
	private class ListNode {
		
		/** Data of the Node. */
		public E data;
		/** Next Node in the LinkedList */
		public ListNode next;
		
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the given element to the end of the list. 
		 * @param e element to add to the end of the LinkedList.
		 */
		public boolean add(E e) {
			while(this.next != null) { 
				this.next = next.next;
			} 
			//Stop when the next node is the end, so make the current next point to new node,
			//Then point that node to the end. 
			this.next = new ListNode(e, next.next); 
			size++; //increase size
			return true;
		}
		
		/**
		 * Checks to see if the given element is contained within the list,
		 * recursively. 
		 * @param e element to add to the end of the LinkedList.
		 * @param index Index to add the given element at.
		 * @throws IndexOutOfBoundsException if the index is less than 0 or 
		 * greater than the size of the LinkedList.
		 */
		public void add(int index, E e) {
			int i = 0;
			while(this.next != null && i != index) { 
				this.next = next.next;
			}
			//stop before the index, so set the NEXT to the new node.
			this.next = new ListNode(e, next.next); 
			size++; // increase size.
		}
			
		/**
		 * Retrieves the data contained at the given index value.
		 * @param index Index value to retrieve the desired data from.
		 * @return Data contained at the given index. 
		 */
		public E get(int index) {
			int counter = 0;
			while(counter < index){
				this.next = next.next;
				counter++;
			}
			//when exiting, next is the index you want, so next.data
			return next.data;
		}
			
		/**
		 * Attempts to remove the given element from the LinkedList.
		 * @param e Element to remove.
		 * @return True if the element was removed, null if not found.
		 */
		public boolean remove(E e){
			while(this.next != null && !this.next.data.equals(e)) { 
				// While the next element is not null (end of list)
				// and while the next elements data does not match the given, continue checking.
				this.next = next.next;
			}
			//either null or matched when exiting
			if(this.next == null) return false; //if next is null and no match was found, then return false
			else  this.next = next.next;
			size--; // reduce the size variable 
			return true;
		}
				
		
		/**
		 * Attempts to remove the given element from the LinkedList at the given index.
		 * @param index Index to remove the LinkedList element from. 
		 * @return removed Element removed or null if not contained within the list.
		 * @throws IndexOutOfBoundsException if the index is less than 0 or 
		 * greater than the size of the LinkedList.
		 */
		public E remove(int index){
			int counter = 0;
			while(counter < index) {
				this.next = next.next;
			}
			// this.next now points to the index you want removed.
			E removed = next.data;
			this.next = new ListNode(next.next.data, next.next.next); //skip over the data you want removed
			size--;
			return removed;
		}
		
		/**
		 * Sets the element at the given index to the value of the given element.
		 * @param index Index to set the new value.
		 * @param e Element to assigned to the given index.
		 * @return replaced The value replaced at the given index. 
		 * @throws IndexOutOfBoundsException if the index is less than 0 or 
		 * greater than the size of the LinkedList.
		 */
		public E set(int index, E e) {
			int counter = 0;
			while(counter < index) {
				this.next = next.next; //focus on where you exit the loop
			}
			// you exit with the next index being what you want set.
			E replaced = next.data;
			next.data = e;
			return replaced;
		}
		
		/**
		 * Checks for an instance of the given element within the LinkedList.
		 * @param e Element to search the LinkedList for. 
		 * @return True if the given element is contained within the list.
		 */
		public boolean contains(E e) {
			while(this.next != null) { //while the next node is not the end.
				if(this.data.equals(e)) return true; //if first node matches (SINCE YOU ARE NOT NULL AT THIS POINT)
				this.next = next.next; //next internal LN is the next.next LN
			}
			return false; //if you get out with no match, then return false.
		}

	}
		
}
