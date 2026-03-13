import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T>
{
    private Node<T> front; // Reference to the front of the queue
    private Node<T> rear;  // Reference to the rear of the queue
    private int numElements = 0; // Number of elements in the queue

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedQueue()
    {
      front = null;
      rear = null;
      numElements = 0;
    }

    @Override
    public void enqueue(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        Node<T> newNode = new Node<>(element, null, rear);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        numElements++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T result = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        } else {
            front.prev = null;
        }
        numElements--;
        return result;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    @Override
    public boolean isEmpty()
    {
      return numElements == 0;
    }

    @Override
    public int size()
    {
      return numElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    // Inner class to implement the Iterator interface
    private class QueueIterator implements Iterator<T> {
        private Node<T> current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
} 
