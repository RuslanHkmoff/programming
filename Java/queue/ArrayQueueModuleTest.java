package queue;

public class ArrayQueueModuleTest {
    public static void main(String[] args) {
        fillQueue();
        dumpQueue();
        fillDeque();
        dumpDeque();
    }

    public static void fillQueue() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue("element" + i);
        }
        System.out.println(ArrayQueueModule.toStr());
    }

    public static void dumpQueue() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
    }

    public static void fillDeque() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.push("element" + i);
        }
    }

    public static void dumpDeque() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.peek() + " " +
                            ArrayQueueModule.remove()
            );
        }
    }
}