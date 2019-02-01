package Lists;

public class ArrayList<E> {
	
	private int size;
	private E[] data;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public E get (int i) throws IndexOutOfBoundsException{
		
		checkIndex(i, size);
		return data[i];
	}
	
	/**Replaces the element at index i with e, and returns the replaced element. */
	public E set (int i, E e) throws IndexOutOfBoundsException{
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}
	
	public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
		if(size == data.length)
			throw new IllegalStateException("Array is full");
		for(int k = size - 1; k >=i; k--)
			data[k+1] = data[k];
		data[i] = e;
		size++;
	}
	
	/**Removes/returns the element at index i,shifting subsequent elements earlier*/
	public E remove (int i) throws IndexOutOfBoundsException{
		checkIndex(i, size);
		E temp = data[i];
		for (int k=i; k < size - 1; k++)
			data[k] = data[k+1];
		data[size-1] = null;
		size--;
		return temp;
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
		if (i < 0 || i >= n){
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}
}
