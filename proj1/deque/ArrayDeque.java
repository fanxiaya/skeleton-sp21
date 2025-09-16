package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] array;
    private int size;
    private int max;
    private int firstEnd; // 指向下一个 addFirst 的位置
    private int lastEnd;  // 指向下一个 addLast 的位置

    public ArrayDeque() {
        size = 0;
        max = 16;
        array = (T[]) new Object[max];
        firstEnd = 0;
        lastEnd = max - 1;
    }

    private void extendSize() {
        int newMax = max * 2;
        T[] newArray = (T[]) new Object[newMax];
        // 将原队列内容顺序复制到新数组
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        array = newArray;
        max = newMax;
        firstEnd = 0;
        lastEnd = size - 1;
    }

    private void reduceSize() {
        if (max <= 16) return;
        int newMax = max / 2;
        T[] newArray = (T[]) new Object[newMax];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        array = newArray;
        max = newMax;
        firstEnd = 0;
        lastEnd = size - 1;
    }

    private void checkSize() {
        if (size + 1 > max) {
            extendSize();
        } else if (max > 16 && size < max / 4) {
            reduceSize();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        checkSize();
        firstEnd = (firstEnd - 1 + max) % max;
        array[firstEnd] = item;
        size++;
    }

    public void addLast(T item) {
        checkSize();
        lastEnd = (lastEnd + 1) % max;
        array[lastEnd] = item;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T item = array[firstEnd];
        array[firstEnd] = null;
        firstEnd = (firstEnd + 1) % max;
        size--;
        checkSize();
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T item = array[lastEnd];
        array[lastEnd] = null;
        lastEnd = (lastEnd - 1 + max) % max;
        size--;
        checkSize();
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        int realIndex = (firstEnd + index) % max;
        return array[realIndex];
    }

    //    public Iterator<T> iterator() {
//    }
//
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque<?>)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!(get(i).equals(other.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    class ArrayDequeIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index <= size - 1;
        }

        @Override
        public T next() {
            return get(index++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}