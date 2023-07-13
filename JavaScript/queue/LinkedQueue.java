package queue;

public class LinkedQueue extends AbstractQueue {
    private class Node {
        Object value;
        Node next;

        public Node(Object value, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    protected Object elementImpl() {
        return head.value;
    }

    public void enqueueImpl(Object element) {
        Node newElement = new Node(element, null);
        if (isEmpty()) {
            head = tail = newElement;
        } else {
            tail.next = newElement;
            tail = newElement;
        }
    }

    public void dequeueImpl() {
        head = head.next;
    }

    protected void clearImpl() {
        head = tail = null;
    }

    protected void setImpl(int index, Object element) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.value = element;
    }

    protected Object getImpl(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

}
