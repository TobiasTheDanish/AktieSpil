package DataStructures.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T>implements ILinkedList<T>
{
    private LinkedListNode<T> first;
    private int size;

    public LinkedList()
    {
        first = null;
        size = 0;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void addFirst(T data)
    {
        first = new LinkedListNode<T>(data, first);
        ++size;
    }

    @Override
    public T removeFirst()
    {
        if(first == null)
        {
            throw new NoSuchElementException("The list is empty!");
        }
        T res = first.getData();
        first = first.getNext();
        --size;
        return res;
    }

    @Override
    public T peekFirst()
    {
        if(first == null)
        {
            throw new NoSuchElementException("The list is empty!");
        }
        return first.getData();
    }

    @Override
    public T get(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("List size: " + size + " index: " + index);
        }
        LinkedListNode<T> current = first;
        for(int i = 0; i < index; ++i)
        {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListIterator<T>(first);
    }



    private static class LinkedListNode<T>
    {
        private final T data;
        private final LinkedListNode<T> next;

        public LinkedListNode(T data, LinkedListNode<T> next)
        {
            this.data = data;
            this.next = next;
        }

        public T getData()
        {
            return data;
        }

        public LinkedListNode<T> getNext()
        {
            return next;
        }
    }

    private static class LinkedListIterator<T> implements Iterator<T>
    {
        private LinkedListNode<T> cur;

        public LinkedListIterator(LinkedListNode<T> cur)
        {
            this.cur = cur;
        }

        @Override
        public boolean hasNext()
        {
            return cur != null;
        }

        @Override
        public T next()
        {
            T res = cur.getData();
            cur = cur.getNext();
            return res;
        }
    }
}

