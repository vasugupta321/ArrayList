import java.util.Arrays;

/**
 * @author Vasu Gupta
 * Course: EECS 2011
 * Date: June 19, 2017
 * Description: Implementing a generic array list, similar to the ArrayList method by Java 
 * 
 * @param <E> - E represents the type of element to be in the doubly linked list
 */

public class A1ArrayList<E> {
	 
	/** 
	 * Data fields
	 */
	
	private E[] elements;
	private int size = 0;
	private int capacity = 0;
	
	/**
	 * Constants
	 */
	
	private static final int INIT_CAPACITY = 10;

	/**
	 * Constructs an empty list with an initial capacity of ten
	 */

	public A1ArrayList(){
		this(INIT_CAPACITY);
	}
	
	/**
	 * Constructs an empty list with the specified intil capacity
	 */

	public A1ArrayList(int initCapacity) throws IllegalArgumentException{
        capacity = INIT_CAPACITY;
        elements = (E[]) new Object[capacity];
    }
	
	/**
	 * 
	 * @param e - the element to be appended to list
	 * 
	 * Appends the specified element to the end of this list.
	 * 
	 * @return - true
	 */

	public boolean add(E e){
		if(e == null){
			throw new NullPointerException("e is null");
		}
		
		elements[size] = e;
		size++;
		resize();
		return true;
	}
	
	/**
	 * 
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted into list
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	
	public void add(int index, E element) throws IndexOutOfBoundsException{
		if (element == null){
			throw new NullPointerException(); 				// 1st case when element is null
		}
		
		if(index <0 || index > size){  		  				// 2nd case if index is out of bounds (either 0 or > size)
				throw new ArrayIndexOutOfBoundsException();
			}
		

		int a = size;
		while( a > index){
			 elements[a] = elements[a - 1];		 			// if we do element is not null and above tests are false,
			 a--;								 			// we do adding an entry at index i by making room
		}								    	 			// by shifting forward entries
		elements[index] = element;
		
		size++;									 			//increase size as we added new element
		resize();
	}
	
	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	
	public void clear(){
		elements = (E[]) new Object[5]; 	 				// we clear by setting size to 0 and creating new array
		size = 0; 							
	}
	
	/**
	 * 
	 * @param index
	 * 
	 * Removes the element at the specified position in this list.
	 * Shifts subsequent elements (if any) to the left by subtracting one from their indices
	 * 
	 * @return
	 */
	
	public E remove(int index){
		 if(index < 0 || index >= size) {
	            throw new ArrayIndexOutOfBoundsException(index);
	        }
		 E temp = elements[index];
		 
//	        for(int a = index + 1; a < size; a++) {  // to remove entry at index we fill the hole to the left  
//	            elements[a-1]= null;		 		 // by shifting backward 
//	        }
	        while(index < size)
	        {
	        	elements[index] = elements[index+1];
	        	index++;
	        }
	        
	        size--;
	        resize();
	        return temp;
	}
	
	/**
	 * 
	 * @param o - the element to removed from the list (if its in list)
	 * 
	 * Removes the first occurrence of the element specified, if it is in list
	 * 
	 * @return - true if list contains the specified element.
	 */
	
	public boolean remove(Object o) {
        int indexOfO = indexOf(o);

        if(indexOfO == -1) {
            return false;
        }

        remove(indexOfO);
        return true;
	}
	
	/**
	 * 
	 * @param o - the element to search for
	 * 
	 * Returns the index of the specified element in the list, if it is in list
	 * 
	 * @return - index of first occurrence of specified element or -1 if element does not exist
	 */
	
	private int indexOf(Object o) {
		if (o == null) {
	        for (int a = 0; a < size; a++)
	            if (elements[a]==null)
	                return a;
	    } else {
	        for (int b = 0; b < size; b++) 			//checking if first occurrence of specified element is in list
	            if (o.equals(elements[b]))
	                return b;
	    }
	    return -1;						   			// if list does not contain element then return -1
	}
	
	/**
	 * @return - string representation of the current elements in list 
	 */
	
	public String toString() {
		return Arrays.toString(elements);
	}
	
	/**
	 * @return - the size of the list
	 */
	
	public int size(){
		return size;
	}
	
	/**
	 * Resize the array by comparing size to capacity.
	 * Either double the capacity or shrink capacity 
	 */
	
	private void resize()
	{
		if(size() > capacity/2) //expand it
		{
			capacity = capacity * 2;
			E[] temp;
			temp = (E[]) new Object[capacity];
			for(int a = 0; a+1 <= size; a++)
			{
				temp[a] = elements[a];
			}
			
			elements = temp;
		}
		if(size() < (double)capacity*.25)	//needs shrink
		{
			capacity = capacity / 2;
			E[] temp;
			temp = (E[]) new Object[capacity];
			for(int a = 0; a+1 <= size; a++)
			{
				temp[a] = elements[a];
			}
			
			elements = temp;
		}
	}
	
}
