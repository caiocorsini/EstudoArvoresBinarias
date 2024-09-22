public class Queue<T> {

    private T[] arrayQueue;
    private int first;
    private int last;
    private int capacity;
    private int cont;

    @SuppressWarnings("unchecked")
    public Queue(int size) {
        arrayQueue = (T[]) new Object[size]; // Generic array creation
        first = 0;
        last = -1;
        capacity = size;
        cont = 0;
    }

    public void enqueue(T newValue) {
        last = (last + 1) % capacity;
        arrayQueue[last] = newValue;
        cont++;
    }

    public T dequeue() {
        T temp = arrayQueue[first];
        first = (first + 1) % capacity;
        cont--;
        return temp;
    }

    public T peekFirst() {
        return arrayQueue[first];
    }

    public T peekLast() {
        return arrayQueue[last];
    }

    public int Count() {
        return cont;
    }
}