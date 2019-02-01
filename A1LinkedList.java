	
/**
 * @author Vasu Gupta
 * Description: Implementing a generic doubly linked list, similar to the LinkedList method by Java 
 * 
 * @param <E> - E represents the type of element in the doubly linked list
 */

public class A1LinkedList<E> {

	private Node<E> head = new Node<>(null);
	private Node<E> tail = new Node<> (null);
	private int size = 0;

	/** 
	 * private inner class used for main class. 
	 * Private so accessible by members of given class
	 */

	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		public Node(E elements) {
			data = elements;
		}
	}

	/**
	 * Constructor: Creates an empty doubly linked list.
	 */

	public A1LinkedList()
	{
		size = 0;
		head.next = tail;
		tail.prev = head;
		head.prev = null; 
		tail.next = null; 
	}

	/**
	 * @param element - The element to add at the end of the linked list.
	 * 
	 * Inserts the specified element to the end of the list.
	 */

	public boolean add(E e) {
		if(e == null){
			throw new NullPointerException("e is null");
		}
		Node<E> n = new Node<>(e);
		n.prev = tail.prev;
		n.next = tail;
		tail.prev.next = n;
		tail.prev = n;
		size++;
		return true;
	}

	/**
	 * @param index - The index at which specified element is to be inserted
	 * @param element - element to be inserted
	 * 
	 * Inserts the specified element at the specific index told in the linked list.
	 * Shifts whatever element is currently at that index (if any) and subsequent elements to the right.
	 * So it adds one to their indices.
	 * If index is out of range, it throws IndexOutOfBoundsException. 
	 */

	public void add(int index, E elements) throws IndexOutOfBoundsException{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("index is out of bounds " + index);
		Node<E> temp;
		int counter;
		if(index > size/2){
			counter = size;
			temp = tail;
			while(--counter >= index)
				temp = temp.prev;
		}
		else{
			counter = -1;
			temp = head;
			while(++counter <= index){
				temp = temp.next;
			}
		}
		Node<E> temp2 = new Node<>(elements);
		temp2.next = temp;
		temp.prev.next = temp2;
		temp2.prev = temp.prev;
		temp.prev = temp2;
		size++;
	}

	/**
	 * Removes every element from this list.
	 */

	public void clear(){
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * @param index - The specified index to be removed from the list
	 * 
	 * Removes and returns the element at the specified index.
	 * Shifts any subsequent elements to the left by subtracting one from their indices.
	 * 
	 * @return - the element previously at specified index
	 * 
	 * Throws IndexOutOfBoundsException if index is out of range.
	 */

	public E remove(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= size )
			throw new IndexOutOfBoundsException("index is out of bounds " + index);
		Node<E> temp;
		int counter;
		if(index > size/2){
			counter = size;
			temp = tail;
			while(--counter >= index)
				temp = temp.prev;
		}
		else{
			counter = -1;
			temp = head;
			while(++counter <= index){
				temp = temp.next;
			}
		}
		E temp2 = temp.data;
		temp.next.prev = temp.prev;
		temp.prev.next = temp.next;
		size--;
		return temp2;
	}

	/**
	 * @param o - the object to be removed from the list
	 * 
	 * Removes the first occurrence of the specified element from this list, if the element is present
	 * 
	 * @return - true if list contains specified element 
	 */

	public boolean remove(Object o){
		Node<E> temp = head;
		while(temp.next != null){
			temp = temp.next;
			if(temp.data.equals(o)){
				temp.next.prev = temp.prev;
				temp.prev.next = temp.next;
				size--;
				return true;
			}
		}
		return false;
	}

	/**
	 * @return - the number of elements in this list
	 */

	public int size(){
		return size;
	}

	@Override
	public String toString()
	{
		String s = "[";
		for(int i=0; i<this.size; i++)
		{
			s = s + s.indexOf(0, size - 1);
		}
		s = s + "]";
		return s;
	}
}
