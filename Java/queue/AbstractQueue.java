package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {

    protected int size=0;

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public Object dequeue() {
        Object result = element();
        size--;
        dequeueImpl();
        return result;
    }


    public Object element() {
        assert size >= 1;
        return elementImpl();
    }

    public void enqueue(Object element) {
        Objects.requireNonNull(element);
        enqueueImpl(element);
        size++;
    }

    public void clear() {
        size = 0;
        clearImpl();
    }

    public Object get(int index) {
        assert size >= 1 && index <= size && index >= 0;
        return getImpl(index);
    }


    public void set(int index, Object element) {
        assert index >= 0 && index < size;
        Objects.requireNonNull(element);
        setImpl(index, element);
    }

    public Object[] toArray() {
        if (isEmpty()) return new Object[0];
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            Object current = this.dequeue();
            result[i] = current;
            this.enqueue(current);
        }
        return result;
    }

    public int count(Object element) {
        Objects.requireNonNull(element);
        int count = 0;
        for (int i = 0; i < size; i++) {
            Object current = this.dequeue();
            if (element.equals(current)) {
                count++;
            }
            this.enqueue(current);
        }
        return count;

    }


    protected abstract void dequeueImpl();

    protected abstract Object elementImpl();

    protected abstract void enqueueImpl(Object element);

    protected abstract void clearImpl();

    protected abstract void setImpl(int index, Object element);

    protected abstract Object getImpl(int index);
}
