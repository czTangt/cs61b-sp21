package deque;

public class ArrayDeque<T> implements Deque<T> {
    // define the basic struct
    private T[] items;
    private int size;
    private final int INIT_CAPACITY = 8; // init size
    private final int MAX_CAPACITY = INIT_CAPACITY * 2;
    // nextFirst points to the position before the first item.
    private int nextFirst;
    // nextLast points to the position after the last item.
    private int nextLast;

    ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
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

    // check if it is circular; if it is, return true
    private boolean isCircular() {
        int first = nextIndex(nextFirst);
        int last = prevIndex(nextLast);
        return first > last;
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

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int first = nextIndex(nextFirst);
        // if it is a circular
        if (isCircular()) {
            System.arraycopy(items, first, newItems, 0, items.length - first);
            System.arraycopy(items, 0, newItems, items.length - first, nextLast);
        } else {
            System.arraycopy(items, first, newItems, 0, size);
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
        int first = nextIndex(nextFirst);
        if (isCircular()) {
            for (int i = first; i < items.length - first; i++) {
                System.out.println(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.println(items[i] + " ");
            }
        } else {
            for (int i = first; i < nextLast; i++) {
                System.out.println(items[i] + " ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        checkEmptySpace();
        // nextIndex(nextFirst) points the first item, nextFirst points the position before the first item.
        T item = items[nextIndex(nextFirst)];
        items[nextIndex(nextFirst)] = null;
        // the original position of first item becomes the position for the next addFirst insertion.
        nextFirst = nextIndex(nextFirst);
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        checkEmptySpace();
        T item = items[prevIndex(nextLast)];
        items[prevIndex(nextLast)] = null;
        nextLast = prevIndex(nextLast);
        size -= 1;
        return item;
    }


    // get the item at the given index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return items[(nextFirst + 1 + index) % items.length];
    }
}
