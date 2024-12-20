package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    // define the basic struct
    private T[] items;
    private int size; // the number of items in the array
    // items.length -> the size of the position in the array, and it is the capacity
    private final int INIT_CAPACITY = 8; // init size
    private final int MAX_CAPACITY = 16; // max size
    // nextFirst points to the position before the first item.
    private int nextFirst;
    // nextLast points to the position after the last item.
    private int nextLast;

    public ArrayDeque() {
        items = createArray(INIT_CAPACITY);
        size = 0;
        nextFirst = INIT_CAPACITY - 1;
        nextLast = 0;
    }

    // update the index
    private int nextIndex(int index) {
        return (index + 1) % items.length;
    }

    private int prevIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }

    // check if it needs expand.
    private void checkExpand() {
        if (size != items.length) return;
        resize(size * 2);
    }

    // check if empty space exists.
    private void checkEmptySpace() {
        if (size >= MAX_CAPACITY
                && size < 0.25 * items.length) {
            resize(items.length / 2);
        }
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    private void resize(int newCapacity) {
        T[] newItems = createArray(newCapacity);
        int first = nextIndex(nextFirst);
        // if it not is a circular
        if (first <= nextLast) {
            System.arraycopy(items, first, newItems, 0, size);
        } else {
            int firstPart = items.length - first;
            System.arraycopy(items, first, newItems, 0, firstPart);
            System.arraycopy(items, 0, newItems, firstPart, nextLast);
        }
        items = newItems;
        // since the copy happens within the range from 0 to size,
        // so the nextFirst points of the last position of the newCapacity.
        nextFirst = newCapacity - 1;
        // the nextLast points the position right the previous size.
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        checkExpand();
        items[nextFirst] = item;
        nextFirst = prevIndex(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        checkExpand();
        items[nextLast] = item;
        nextLast = nextIndex(nextLast);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = nextIndex(nextFirst);
        for(int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index = nextIndex(index);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        checkEmptySpace();
        // nextIndex(nextFirst) points the first item, nextFirst points the position before the first item.
        int firstIndex = nextIndex(nextFirst); // cache calculation results
        T item = items[firstIndex];
        items[firstIndex] = null;
        // the original position of first item becomes the position for the next addFirst insertion.
        nextFirst = firstIndex;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        checkEmptySpace();
        int lastIndex = prevIndex(nextLast);
        T item = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        return item;
    }


    // get the item at the given index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        private ArrayDequeIterator() {
            index = nextIndex(nextFirst);
        }

        @Override
        public boolean hasNext() {
            return index != nextLast;
        }

        @Override
        public T next() {
            T returnItem = items[index];
            index = nextIndex(index);
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deque<?>)) return false;
        Deque<?> deque = (Deque<?>) o;
        if(size != deque.size()) return false;
        for (int i = 0; i < size; i++) {
            if(!get(i).equals(deque.get(i))) return false;
        }
        return true;
    }
}
