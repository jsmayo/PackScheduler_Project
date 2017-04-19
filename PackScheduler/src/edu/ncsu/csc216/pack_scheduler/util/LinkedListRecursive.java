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
		if(front == null) return 0;
		else return front.size();
	}
	
	/**
	 * Attempts to add the element e of type E at the end of the LinkedList.
	 * @param e Element to add to end of the LinkedList.
	 * @return True if the element was added successfully. 
	 */
	public boolean add(E e) {
		if(contains(e)) throw new IllegalArgumentException();
		if(isEmpty() || size() == 0) { 
			front = new ListNode(e, front); // handles empty cases only.
			size = size(); //increase the size
			return true; //exit the call
		}
		else {
			boolean b = front.add(e); //else delegate recursive call to inner node
			size = size();
			return b;
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
		
		//add to front
		if(index == 0) {
			//empty list
				front = new ListNode(e, front);
				size = size();
				return;
			}
		else if(index == size()) {
			boolean b = front.add(e); //add to end
			if(!b) throw new IllegalArgumentException();
			size = size();
			return;
		}
		else if(index > 0 && index < size()) {
			front.add(index, e);
			size = size();
			return;
		}
		else throw new IllegalArgumentException();
			
	}
	
//	public ArrayList<ListNode> toArray() {
//		ArrayList<ListNode> al = new ArrayList<ListNode>();
//		if(front == null) return al; //handle empty case
//		else {
//			
//			al.add(front); //add the front node and call inner node for recursive add.
//			front.toArray(al);
//		}
//		return al;
//	}


	/**
	 * Returns the element at the given index.
	 * @param index Index to retrieve the element from.
	 * @return e Element of type E at the given index.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E get(int index) {
		if(index < 0 || front == null  || index >= size()) throw new IndexOutOfBoundsException();	
		return front.get(index); // delegate to recursive method
	}
	
	/**
	 * Attempts to remove the given element from the LinkedList.
	 * @param e Element to remove.
	 * @return True if the element was removed, null if not found.
	 */
	public boolean remove(E e){
		if(e == null) throw new NullPointerException();
		if(front == null) return false;
		if(front.data.equals(e)) {
			front = front.next;
			size = size();
			return true;
		}
		else if(front.remove(e)) {
			size = size();
			return true;
		}
		else return false;	
		
	}
	
	/**
	 * Attempts to remove the given element from the LinkedList at the given index.
	 * @param index Index to remove the LinkedList element from. 
	 * @return removed Element removed or null if not contained within the list.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or 
	 * greater than the size of the LinkedList.
	 */
	public E remove(int index){
		if(index >= size() || index < 0 || front == null) throw new IndexOutOfBoundsException();
		E e = null;
		if(index == 0) { //know front != null from first check
			e = front.data;
			front = front.next;
			size = size();
			return e;
		}
		else {
			e = front.remove(index);
			size = size();
			return e;
		}
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
		if(index < 0 || front == null || index >= size()) throw new IndexOutOfBoundsException();
		if(e == null) throw new NullPointerException();
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
		
		public int size() {
			if(next == null) return 1;
			else return 1 + next.size();
		}
		
		/**
		 * Adds the given element to the end of the list. 
		 * @param e element to add to the end of the LinkedList.
		 */
		public boolean add(E e) {
			if(next == null) { //when next == null, you want to add E, since adding to the end.
				next = new ListNode(e, null);
				return true;
			}
			else return next.add(e); //use next node to call recursively
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
			//index > 0 and index < size
			int step = 1; //constant stepsize
			if(index == 1){ //if next index is the target, add and return.
				next = new ListNode(e, next);
				return;
			}
			else { //if not at the target, recursive call and update an index reference.
				if(index - step != 0) {
					next.add(index - step, e);
				}
			}
		}

//		public ArrayList<ListNode> toArray(ArrayList<ListNode> al) {
//			if(next == null) {
//				al.add(new ListNode(data, null)); // without this, AL will not add the last node and you will get IOOBE
//				return al;
//			}
//			if(next != null) {
//				al.add(next); //add the element associated with front's node
//				return next.toArray(al); //use inner next to recursively call toArray if next != null
//			}
//			
//			return al;
//		}
			
		/**
		 * Retrieves the data contained at the given index value.
		 * @param index Index value to retrieve the desired data from.
		 * @return Data contained at the given index. 
		 */
		public E get(int index) {
			//know front is not null at this point, also index < size
			if(index == 0) return data;
			else return next.get(index - 1); 		
		}
			
		/**
		 * Attempts to remove the given element from the LinkedList.
		 * @param e Element to remove.
		 * @return True if the element was removed, null if not found.
		 */
		public boolean remove(E e){
			if(next == null) return false;
			if(next.data.equals(e)){
				//E r = next.data;
				next = next.next;
				return true;
			}
			else return next.remove(e);
			}
		
				
		
		/**
		 * Attempts to remove the given element from the LinkedList at the given index.
		 * @param index Index to remove the LinkedList element from. 
		 * @return removed Element removed or null if not contained within the list.
		 * @throws IndexOutOfBoundsException if the index is less than 0 or 
		 * greater than the size of the LinkedList.
		 */
		public E remove(int index){
			if(index == 1) {
				E r = next.data;
				next = next.next;
				return r;
			}
			else {
				if(next == null) return null;
				return next.remove(index - 1);
			}
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
			if(index == 0) {
				E replaced = data;
				data = e;
				return replaced;
			}
			else return (next.set(index - 1, e));
		}
		
		/**
		 * Checks for an instance of the given element within the LinkedList.
		 * @param e Element to search the LinkedList for. 
		 * @return True if the given element is contained within the list.
		 */
		public boolean contains(E e) {
			if(data.equals(e)) return true;
			else {
				if(next == null)  return false;
				else return next.contains(e); //recursive call
			}

		}
		
	}
}

