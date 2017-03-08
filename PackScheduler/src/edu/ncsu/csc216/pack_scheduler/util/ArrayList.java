package edu.ncsu.csc216.pack_scheduler.util;

import java.util.Arrays;
import java.util.Collections;

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
	private ArrayList<E> arrayList;
	
	/**
	 * Constructor for the ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[10];
		arrayList = new ArrayList<E>();
		Collections.addAll(arrayList, list);
		
	}

	@Override
	public E get(int index) {
		//return list[index];
		return arrayList.get(index);
	}

	@Override
	public int size() {
		return arrayList.size();
	}

}
