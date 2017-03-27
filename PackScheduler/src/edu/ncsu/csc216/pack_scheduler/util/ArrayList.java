package edu.ncsu.csc216.pack_scheduler.util;

import java.util.Arrays;

/**
 * Generic version of the AbstractList class.
 * 
 * @param <E> the element of the value being placed inside the ArrayList.
 * @author Steven Mayo
 */
public class ArrayList<E> extends java.util.AbstractList<E> {
	
	/** Constant value for initializing the list size. */
	public static final int INIT_SIZE = 10;
	/** Array of generic type for construction of the ArrayList. */
	private E[] list;
	/** Size of the ArrayList */
	private int size;
	//private ArrayList<E> arrayList;
	private int capacity;
	
	/**
	 * Constructor for the ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		this.size = 0;
		this.capacity = 10;
		//arrayList = new ArrayList<E>();
		
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
		if(e == null) throw new NullPointerException();
		if(index > this.size || index < 0 ) throw new IndexOutOfBoundsException();
		for(int i = 0; i <= this.size; i++) if(e.equals(list[i])) throw new IllegalArgumentException("Cannot add duplicate values.");
		
		
		//size is last place of value (NOT INDEXED).
		
		if(this.size == 0 && index == 0) {
			list[0] = e;
		}
		if(index == 0 && this.size > 0) {
			//Capacity is doubled when approaching the limit at size + 1 >= capacity
			//shift everything right
			for(int i = this.size; i >= 0; i--) list[i + 1] = list[i];
			list[0] = e;
		}
		else if (index > 0 && this.size > 0) {
				for(int i = this.size; i >= index; i--) list[i + 1] = list[i];
				list[index] =  e;
		}
		this.size++;
		//handle capacity checks
		//if the size of the array is reaching the capacity, double the capacity.	
		if(this.size + 1 >= this.capacity) {
			this.capacity *= 2;
			this.list = Arrays.copyOf(this.list, this.capacity);
		}
		
	}
	
	
	/**
	 * Removes the value assigned to the specified index value.
	 * @param index The index to remove the value from.
	 * @return E the object removed from the specified index. 
	 * @throws IndexOutOfBoundsException if the specified index is out
	 * of the the boundary limit (0 - size()).
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		E e = list[index]; //value to be returned before removal.
		//if the index is the last position of the list, then just set it to null and decrease the size.
		//if(index == this.size-1) { 
			//list[index] = null;
			//this.size--;
		//}
		//else {
			for(int i = index; i <= this.size - 1; i++) list[i] = list[i + 1];
			list[size - 1] = null; //set last value to null to remove the reference (works with size = 1)
			this.size--; //decrement the size variable
		//}
		return e;
	}
	
	/**
	 * Sets the specified index to the corresponding specified value. 
	 * @param index The index value to place the object into the ArrayList.
	 * @param e The object, of type E, to place at the specified index. 
	 * @throws IndexOutOfBoundsException if the specified index is outside of
	 * the array boundaries
	 * @throws IllegalArgumentException if the specified value is a duplicate already
	 * found within the list.
	 */
	@Override
	public E set(int index, E e) {
		if(e == null) throw new NullPointerException();
		if((this.size == 0 && index == 0) || index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		//Hash set to add values that are not duplicates
		//HashSet<E> set = new HashSet<>();
		for(int i = 0; i < this.size; i++) if(list[i].equals(e)) throw new IllegalArgumentException("Cannot add duplicate values"); //if(!set.add(arrayElement))
		E overWrite = this.list[index];
		this.list[index] = e;
		return overWrite;
		
		
	}		

	
	/**
	 * Returns the value, of type E, located at the specified index value. 
	 * @return E Object stored at the specified index value. 
	 * @throws IndexOutOfBoundsException if the specified index is out
	 * of the array boundaries.
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		return list[index];
		//return arrayList.get(index);
	}
	
	/**
	 * Returns the size, which corresponds to the number of elements
	 * contained within the ArrayList object.
	 * @return int Size of the ArrayList. 
	 */
	@Override
	public int size() {
		return this.size;
		//return arrayList.size();
	}
	
	/**
	 * Getter method that returns the capacity of the list. 
	 * Mainly used to ensure proper functionality:
	 * Initial capacity is 10 and automatically doubled when capacity and size
	 * are equivalent. 
	 * @return int Capacity of the list. 
	 */
	public int capacity(){
		return this.capacity;
	}

}