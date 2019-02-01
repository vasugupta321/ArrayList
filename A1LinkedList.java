import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class A1LinkedList <E> implements List <E>{
	transient int size = 0;
	transient Node<E> first;
	transient Node<E> last;
	
	public A1LinkedList() {
    }
	
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

	@Override
	public boolean add(E e) {
        final Node<E> oldLast = last;
        final Node<E> newNode = new Node<>(oldLast, e, null);
        last = newNode;
        if (oldLast == null)
            first = newNode;
        else
            oldLast.next = newNode;
        size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (!(index >= 0 && index <= size))
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		
        if (index == size){ //to insert into the last position
            final Node<E> oldLast = last;
            final Node<E> newNode = new Node<>(oldLast, element, null);
            last = newNode;
            if (oldLast == null)
                first = newNode;
            else
                oldLast.next = newNode;
            size++;

        }
        else{ //non-last position
        	Node<E> x; 
        	if (index < size/2)
    		{
    			x = first;
    			for (int i = 0; i < index; i++)
    				x = x.next; //add before this node
    		}else{
    			x = last;
    			for (int i = size - 1; i > index; i--)
    				x = x.prev; //add before this node
    		}
            
            final Node<E> predecessor = x.prev;
            final Node<E> newNode = new Node<>(predecessor, element, x);
            x.prev = newNode;
            if (predecessor == null)
                first = newNode;
            else
                predecessor.next = newNode;
            size++;
        }
	}

	@Override
	public void clear() {
		//optional nulling
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        
        first = last = null;
        size = 0;
	}

	@Override
	public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    final Node<E> next = x.next;
                    final Node<E> prev = x.prev;

                    if (prev == null) { //if first node
                        first = next;
                    } else {
                        prev.next = next;
                        x.prev = null;
                    }

                    if (next == null) { //if last node
                        last = prev;
                    } else {
                        next.prev = prev;
                        x.next = null;
                    }

                    x.item = null;
                    size--;
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    final Node<E> next = x.next;
                    final Node<E> prev = x.prev;

                    if (prev == null) { //if remove the first
                        first = next;
                    } else {
                        prev.next = next;
                        x.prev = null;
                    }

                    if (next == null) { //if remove the last
                        last = prev;
                    } else {
                        next.prev = prev;
                        x.next = null;
                    }

                    x.item = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
	}

	@Override
	public E remove(int index) {
		if (!(index >= 0 && index <= size))
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		
		Node<E> x;
		if (index < size/2)
		{
			x = first;
			for (int i = 0; i < index; i++)
				x = x.next; //remove this node
		}else{
			x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev; //remove this node
		}
			
        final E element = x.item; //will return it at the end
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) { //remove the first
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) { //remove the last
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append('[');
        
        for (Node <E> element  = first; element != null; element = element.next){
            sb.append(element.item == this ? "(this Collection)" : element.item);
            if (element.next == null) return sb.append(']').toString();
            sb.append(',').append(' ');
        }
        return null;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
