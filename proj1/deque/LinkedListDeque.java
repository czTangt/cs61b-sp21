package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    // define the basic struct
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // the first item (if it exits) is at sentinel.next
    // and the last item is at sentinel.prev
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node tempItem = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = tempItem;
        sentinel.next = tempItem;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node tempItem = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = tempItem;
        sentinel.prev = tempItem;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return value;
    }

    // get the item at the given index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    // same as get, but uses recursion
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recursiveHelper(sentinel.next, index);
    }

    private T recursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return recursiveHelper(p.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLListIterator();
    }

    private class DLLListIterator implements Iterator<T> {
        private Node current;

        private DLLListIterator() {
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        Deque<?> deque = (Deque<?>) o;
        if (size != deque.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(deque.get(i))) {
                return false;
            }
        }
        return true;
    }
}
