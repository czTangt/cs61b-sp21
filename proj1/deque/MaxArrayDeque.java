package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> originalComparator;

    public MaxArrayDeque(Comparator<T> comparator) {
        originalComparator = comparator;
    }

    public T max() {
        int maxIndex = 0;
        for (int i = 0; i < this.size(); i++) {
            if (originalComparator.compare(this.get(i), this.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return this.get(maxIndex);
    }

    public T max(Comparator<T> comparator) {
        int maxIndex = 0;
        for (int i = 0; i < this.size(); i++) {
            if (comparator.compare(this.get(i), this.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return this.get(maxIndex);
    }
}
