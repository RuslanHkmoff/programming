package queue;

/*
    Model: a[1]...a[n]
    Inv: n >= 1 for all 1<=i<=n a[i] != null
    Let: immutable(k): for all 1<=i<=k a'[i] = a[i]
*/

public interface Queue {

    // Pred: element != null
    // Post: n' = n+1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    void enqueue(Object element);

    // Pred: n >=1
    // Post: R = a[1] && n' = n-1 && immutable(n)
    Object dequeue();

    // Pred: n >=1
    // Post: R = a[1] && n' = n && immutable(n)
    Object element();

    // Pred: true
    // Post: R = n && n' = n && immutable(n)
    int size();

    // Pred: true
    // Post: R = (n = 0) && n' = n && immutable(n)
    boolean isEmpty();


    // Pred: true
    // Post: n = 0 && immutable(n)
    void clear();

    // Pred: element != null
    // Post: R - number of occurrences element in a && immutable(n)
    int count(Object element);

    // Pred: n>=1 && 0 <= index <= n-1
    // Post: R = a[index+1] && immutable(n)
    Object get(int index);

    // Pred: element != null && 0 <= index <= n-1
    // Post: a[index+1] = element && immutable(n)
    void set(int index, Object element);

    // Pred: true
    // Post: array R: R.n = a.n && for all 1<=i<=n: R[i-1] = a[i] && immutable(n)
    Object[] toArray();
}
