package DataStructures.Stack;
import DataStructures.LinkedList.*;

public class Stack<E> implements IStack<E>
{
    private final ILinkedList<E> list;

    public Stack()
    {
        list = new LinkedList<>();
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public void push(E element)
    {
        list.addFirst(element);
    }

    @Override
    public E pop()
    {
        return list.removeFirst();
    }

    @Override
    public E peek()
    {
        return list.peekFirst();
    }
}
