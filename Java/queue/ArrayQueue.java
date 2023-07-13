package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue extends AbstractQueue {
    private int head;
    private Object[] elements = new Object[10];


    public void enqueueImpl(Object element) {
        ensureCapacity(size);
        elements[(head + size) % elements.length] = element;
    }

    // Pred: true
    // Post: n = n' && immutable(n)
    private void ensureCapacity(int size) {
        if (size >= elements.length) {
            Object[] resizeQueue = new Object[2 * size];
            int end = (head + size) % elements.length;
            System.arraycopy(elements, head, resizeQueue, 0, size - head);
            System.arraycopy(elements, 0, resizeQueue, size - head, end);
            elements = resizeQueue;
            head = 0;
        }
    }


    protected Object elementImpl() {
        return elements[head];
    }

    protected void dequeueImpl() {
        head = (++head) % elements.length;
    }

    public void clearImpl() {
        head = 0;
        elements = new Object[10];
    }

    // Pred: n >= 1
    // Post: R = a[n'] && n' = n - 1 && immutable(n')
    public Object remove() {
        assert size >= 1;
        size--;
        return elements[(head + size) % elements.length];
    }

    // Pred: n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public Object peek() {
        assert size >= 1;
        return elements[(head + size - 1) % elements.length];
    }

    // Pred: element != null
    // Post: n' = n + 1 &&
    //       a'[1] = element &&
    //       immutable(n)
    public void push(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size);
        size++;
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
    }


    public Object getImpl(int index) {
        return elements[(head + index) % elements.length];
    }


    public void setImpl(int index, Object element) {
        elements[(head + index) % elements.length] = element;
    }

    // Pred: true
    // Post: string R: R = '[a[1], a[2],..., a[n]]' && immutable(n)
    public String toStr() {
        return Arrays.toString(toArray());
    }

}