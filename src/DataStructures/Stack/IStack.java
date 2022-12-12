package DataStructures.Stack;

public interface IStack<E>
{
    int size();
    default boolean isEmpty()
    {
        return size() == 0;
    }
    void push(E element);
    E pop();
    E peek();
}
