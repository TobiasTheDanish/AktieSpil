package DataStructures.LinkedList;

public interface ILinkedList<T> extends Iterable<T>
{
    int size();
    T peekFirst();
    T get(int index);

    void addFirst(T data);
    T removeFirst();
}
