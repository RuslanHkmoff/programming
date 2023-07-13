package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueADT {
    private int size = 0;
    private int head = 0;
    private Object[] elements = new Object[10];


    // Pred: queue != null element != null
    // Post: n' = n+1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    public static void enqueue(ArrayQueueADT queue, Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size);
        int index = (queue.head + queue.size) % queue.elements.length;
        queue.elements[index] = element;
        queue.size++;
    }

    // Pred: queue != null && true
    // Post: n = n' && immutable(n)
    private static void ensureCapacity(ArrayQueueADT queue, int size) {
        if (size >= queue.elements.length) {
            Object[] resizeQueue = new Object[2 * size];
            int end = (queue.head + queue.size) % queue.elements.length;
            System.arraycopy(queue.elements, queue.head, resizeQueue, 0, size - queue.head);
            System.arraycopy(queue.elements, 0, resizeQueue, size - queue.head, end);
            queue.elements = resizeQueue;
            queue.head = 0;
        }
    }

    // Pred: queue != null && n >=1
    // Post: R = a[1] && n' = n-1 && immutable(n)
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size >= 1;
        queue.size--;
        Object result = queue.elements[queue.head];
        queue.head = (++queue.head) % queue.elements.length;
        return result;
    }

    // Pred: queue != null && n >=1
    // Post: R = a[1] && n' = n && immutable(n)
    public static Object element(ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[queue.head];
    }

    // Pred: queue != null
    // Post: R = n && n' = n && immutable(n)
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // Pred: queue != null
    // Post: R = (n = 0) && n' = n && immutable(n)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred: queue != null
    // Post: n = 0 && immutable(n)
    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.size = 0;
        queue.elements = new Object[10];
    }

    // Pred: queue != null && n >= 1
    // Post: R = a[n'] && n' = n - 1 && immutable(n')
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size >= 1;
        queue.size--;
        return queue.elements[(queue.head + queue.size) % queue.elements.length];
    }

    // Pred: queue != null && n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
    }

    // Pred:  queue != null && element != null
    // Post: n' = n + 1 &&
    //       a'[1] = element &&
    //       immutable(n)
    public static void push(ArrayQueueADT queue, Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size);
        queue.size++;
        queue.head = (queue.head - 1 + queue.elements.length) % queue.elements.length;
        queue.elements[queue.head] = element;
    }


    // Pred: queue != null
    // Post: string R: R = '[a[1], a[2],..., a[n]]' && immutable(n)
    public static String toStr(ArrayQueueADT queue) {
        return Arrays.toString(toArray(queue));
    }

    // Pred: queue != null
    // Post: array R: R.n = a.n && for all 1<=i<=n: R.a[i] = a[i] && immutable(n)
    public static Object[] toArray(ArrayQueueADT queue) {
        if (isEmpty(queue)) return new Object[0];
        Object[] result = new Object[queue.size];
        for (int i = 0, index = queue.head; ; i++, index++) {
            result[i] = queue.elements[index % queue.elements.length];
            if (i == queue.size - 1) {
                return result;
            }
        }
    }

    // Pred:  queue != null && n>=1
    // Post: R = a[index] && immutable(n)
    public static Object get(ArrayQueueADT queue, int index) {
        assert queue.size >= 1;
        return queue.elements[(queue.head + index) % queue.elements.length];
    }

    // Pred: queue != null && element != null
    // Post: a[index] = element && immutable(n)
    public static void set(ArrayQueueADT queue, int index, Object element) {
        Objects.requireNonNull(element);
        queue.elements[(queue.head + index) % queue.elements.length] = element;
    }
}
