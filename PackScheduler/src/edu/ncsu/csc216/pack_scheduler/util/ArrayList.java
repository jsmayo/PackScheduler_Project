package edu.ncsu.csc216.pack_scheduler.util;

import java.util.Arrays;


/**
 * Generic version of the AbstractList class.
 * @param <E> the element of the value being placed inside the arraylist.
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
	 * @throws IllegalArgumnetException if the specified value is a duplicate already
	 * found within the list.
	 */
	@Override
	public void add(int index, E e){
		if(e == null) throw new NullPointerException();
		if(index > this.size || index < 0 ) throw new IndexOutOfBoundsException();
		for(int i = 0; i < this.size; i++) if(e.equals(list[i])) throw new IllegalArgumentException("duplicate found");
		//size is last place of value (NOT INDEXED).
		if(this.size == 0) list[0] = e;
		//if the size is 0, just add the value.
		else {
			if(index == 0) {
				for(int i = this.size(); i > 0; i--) list[i] = list[i-1];
				list[0] = e;
			}
			else {
				for(int i = this.size; i >= index; i--) list[i] = list[i - 1];
				list[index] =  e;
			}
		}
		this.size++;
		//if the size of the array is reaching the capacity, double the capacity.
		if(this.size >= this.capacity) {
			this.capacity *= 2;
			this.list = Arrays.copyOf(this.list, this.capacity);
			//E[] tempArray = this.list;
			//this.list = (E[]) new Object[this.capacity * 2];
			//for(int i = 0; i < tempArray.length; i++) this.list[i] = tempArray[i];
			
			//this.size = (this.capacity) / 2;
			//TODO: Why out of bounds at 10?
			//IS this 
		}
		
	}
	
	/**
	 * Returns the value, of type E, located at the specified index value. 
	 * @return E Object stored at the specified index value. 
	 */
	@Override
	public E get(int index) {
		return list[index];
		//return arrayList.get(index);
	}
	
	/**
	 * Returns the size of the list.
	 * @return int Size of the list. 
	 */
	@Override
	public int size() {
		return this.size;
		//return arrayList.size();
	}
	
	/**
	 * Returns the capacity of the list. Mainly used to ensure proper functionality.
	 * @return int Capacity of the list. 
	 */
	public int capacity(){
		return this.capacity;
	}

}
