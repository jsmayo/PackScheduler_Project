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

	
	/**
	 * Constructor for LinkedListRecursive
	 */
	public LinkedListRecursive() {
		this.size = 0;
		front = null;
	}
	
	/**
	 * Checks to see if LinkedListRecursive object is empty.
	 * @return True if no objects are contained within the LinkedList.
	 */
	public boolean isEmpty(){
		return (size() == 0); 
		
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
		if(contains(e)) throw new IllegalArgumentException();
		if(isEmpty()) { 
			front = new ListNode(e, null); // handles empty cases only.
			this.size++; //increase the size
			return true; //exit the call
		}
		else {
			return front.add(e); //else delegate recursive call to inner node
		}
	
	}
	
	/**
	 * Attempts to add the given element at the given index. 
	 * @param e element to add to the LinkedList.
	 * @param index Index to add the given element at.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public void add(int index, E e) {
		if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
		if(e.equals(null)) throw new NullPointerException();
		if(contains(e)) throw new IllegalArgumentException();
		if(isEmpty()) { 
			front = new ListNode(e, null);
			size++;
			return;
		}
		
		else if(index == 0 && !isEmpty() && index < size()) {
			front = new ListNode(e, front);
			size++;
			return;
		}
		else if(index == size() -1 ) {
			add(e);
			return;
		}
		else  
			front.add(index, e);
		
		//al = toArray(); // reset the array with correct ordering of elements. 
	}
	
	public ArrayList<ListNode> toArray() {
		ArrayList<ListNode> al = new ArrayList<ListNode>();
		if(front == null) return al; //handle empty case
		else {
			
			al.add(front); //add the front node and call inner node for recursive add.
			front.toArray(al);
		}
		return al;
	}


	/**
	 * Returns the element at the given index.
	 * @param index Index to retrieve the element from.
	 * @return e Element of type E at the given index.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E get(int index) {
		if(index < 0 || size == 0  || (size() > 0 && index > size())) throw new IndexOutOfBoundsException();
		//if(index == 0 && size() > 0) return front.data;
		
		return front.get(index); // delegate to recursive method
		
		
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
		if(index < 0 || size() == 0 || index > size()) throw new IndexOutOfBoundsException();
		if(index == 0 && size() == 1) {
			E replaced = front.data;
			front.data = e;
			return replaced; 
		}
		else return front.set(index, e);
	}
	
	/**
	 * Checks for an instance of the given element within the LinkedList.
	 * @param e Element to search the LinkedList for. 
	 * @return True if the given element is contained within the list.
	 */
	public boolean contains(E e) {
		if(isEmpty()) return false;
		return front.contains(e); //can call this because guaranteed size != 0
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
			if(next == null) { //when next == null, you want to add E, since adding to the end.
				next = new ListNode(e, null);
				size++;
				return true;
			}
			else  return next.add(e); //use next node to call recursively
			//return false; //should not return false
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
	
			ArrayList<ListNode> al = next.toArray(new ArrayList<ListNode>());
			ListNode previous =  al.get(index);
			//if(index != size) previous = al.get(index - 1);
			
//			if(previous.next == null) {
//				//this.data = e;
//				//this.next = null;
//				previous.next = new ListNode(e, null);
//				size++;
//				return;
//				//al.add(previous.next); //add new node to the end of AL
//			}
					
			 // know previous.next != null at this point.
				this.data = e;
				this.next = previous.next; //before the node at the current index
				previous.next = new ListNode(data, next); // overwrite old "next" with what you want inserted.
				size++;
				return;
//				for(int i = al.size(); i >= index; i--) { //reorder AL
//					al(i + 1) = al.get(i);
//				}
//				al[index] = previous.next; //put insertion in.
			
		}
		
		public ArrayList<ListNode> toArray(ArrayList<ListNode> al) {
			if(next == null) {
				al.add(new ListNode(data, null)); // without this, AL will not add the last node and you will get IOOBE
				return al;
			}
			if(next != null) {
				al.add(next); //add the element associated with front's node
				return next.toArray(al); //use inner next to recursively call toArray if next != null
			}
			
			return al;
		}
			
		/**
		 * Retrieves the data contained at the given index value.
		 * @param index Index value to retrieve the desired data from.
		 * @return Data contained at the given index. 
		 */
		public E get(int index) {
			if(index == 0) return data;
			else {
				ArrayList<ListNode> al = front.toArray(new ArrayList<ListNode>());
				ListNode a = al.get(index - 1 );
				return a.data;
			}
			
			
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
			//you are at >=1 element by this point
			E replaced = null;
			int i = 0;
			if(index == 1) {
				replaced = next.data;
				next.data = e;
				return replaced;
			}
			else {
				i = 1;
				while(i < index) { 
					next = next.next;
					i++;
				}
				replaced = next.data;
				next.data = e;
			}
				return replaced;			
			
		}
		
		/**
		 * Checks for an instance of the given element within the LinkedList.
		 * @param e Element to search the LinkedList for. 
		 * @return True if the given element is contained within the list.
		 */
		public boolean contains(E e) {
			if(data.equals(e)) return true;
			if(next != null) 
				return next.contains(e); //recursive call
			return (data.equals(e)); //when next == null, do the final call and return

		}

	}
		
}
