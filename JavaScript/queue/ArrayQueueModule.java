package queue;


import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueModule {
    /*
   Model: a[1]...a[n]
   Inv: n > 0 for all 0<i<=n a[i] != null
   Let: immutable(k): for all 0<i<=k a'[i] = a[i]
    */
    private static int size;
    private static int head;
    private static Object[] elements = new Object[5];

    // Pred: element != null
    // Post: n' = n+1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    public static void enqueue(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size);
        elements[(head + size) % elements.length] = element;
        size++;
    }

    // Pred: true
    // Post: n = n' && immutable(n)
    private static void ensureCapacity(int size) {
        if (size >= elements.length) {
            Object[] resizeQueue = new Object[2 * size];
            int end = (head + size) % elements.length;
            System.arraycopy(elements, head, resizeQueue, 0, size - head);
            System.arraycopy(elements, 0, resizeQueue, size - head, end);
            elements = resizeQueue;
            head = 0;
        }

    }

    // Pred: n >=1
    // Post: R = a[1] && n' = n-1 && immutable(n)
    public static Object dequeue() {
        assert size >= 1;
        size--;
        Object result = elements[head];
        head = (++head) % elements.length;
        return result;
    }

    // Pred: n >=1
    // Post: R = a[1] && n' = n && immutable(n)
    public static Object element() {
        assert size >= 1;
        return elements[head];
    }

    // Pred: true
    // Post: R = n && n' = n && immutable(n)
    public static int size() {
        return size;
    }

    // Pred: true
    // Post: R = (n = 0) && n' = n && immutable(n)
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: n = 0 && immutable(n)
    public static void clear() {
        head = 0;
        size = 0;
        elements = new Object[10];
    }

    // Pred: n >= 1
    // Post: R = a[n'] && n' = n - 1 && immutable(n')
    public static Object remove() {
        assert size >= 1;
        size--;
        return elements[(head + size) % elements.length];
    }

    // Pred: n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public static Object peek() {
        assert size >= 1;
        return elements[(head + size - 1) % elements.length];
    }

    // Pred: element != null
    // Post: n' = n + 1 &&
    //       a'[1] = element &&
    //       immutable(n)
    public static void push(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size);
        size++;
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
    }


    // Pred: true
    // Post: string R: R = '[a[1], a[2],..., a[n]]' && immutable(n)
    public static String toStr() {
        return Arrays.toString(toArray());
    }

    // Pred: true
    // Post: array R: R.n = a.n && for all 1<=i<=n: R.a[i] = a[i] && immutable(n)
    public static Object[] toArray() {
        if (isEmpty()) return new Object[0];
        Object[] result = new Object[size()];
        for (int i = 0, index = head; ; i++, index++) {
            result[i] = elements[index % elements.length];
            if (i == size() - 1) {
                return result;
            }
        }
    }

    // Pred: n>=1
    // Post: R = a[index] && immutable(n)
    public static Object get(int index) {
        assert size >= 1;
        return elements[(head + index) % elements.length];
    }


    // Pred: element != null
    // Post: a[index] = element && immutable(n)
    public static void set(int index, Object element) {
        Objects.requireNonNull(element);
        elements[(head + index) % elements.length] = element;
    }

}
