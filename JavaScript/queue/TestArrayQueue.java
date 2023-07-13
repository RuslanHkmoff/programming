package queue;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();
        fill(queue1, "q1_elem");
        dump(queue1);
        fill(queue2, "q2_elem");
        dump(queue2);
    }

    private static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " "
                    + queue.element() + " " + queue.dequeue());
        }

    }

    private static void fill(ArrayQueue queue, String prefix) {
        for (int i = 0; i < 10; ++i) {
            queue.enqueue(prefix + i);
        }
        System.out.println(queue.toStr());
    }

}
