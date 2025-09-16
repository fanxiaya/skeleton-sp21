package deque;

public class ArrayDeque<T> {
    private T[] array;
    private Integer size;
    private Integer max;
    private Integer nextFirst;
    Integer nowFirst;
    Integer nowLast;
    private Integer nextLast;


    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        max = 8;
        nextFirst = max / 2 - 1;
        nextLast = nextFirst + 1;
    }

    private void extendSize() {
        T[] newArray = (T[]) new Object[max * 2];
        if (nowLast == max - 1 && nowFirst == 0) {
            // 没占用对方的空间
            System.arraycopy(array, nowFirst, newArray, max / 2, max);
            nowFirst = max / 2;
            nowLast += max / 2;
        } else if (nowFirst > max / 2 - 1) {
            int numToEnd = max - nowFirst;
            System.arraycopy(array, nowFirst, newArray, max / 2 - numToEnd, numToEnd);
            System.arraycopy(array, 0, newArray, max / 4, max - numToEnd);
            nowFirst = max / 2 - numToEnd;
            nowLast = max / 4 + nowLast;
        } else if (nowLast < max / 2) {
            // 队列被分成两段
            System.arraycopy(array, nowFirst, newArray, max / 4, max - nowFirst);
            System.arraycopy(array, 0, newArray, max / 4 + (max - nowFirst), nowLast + 1);
            nowFirst = max / 4;
            nowLast = max / 4 + (max - nowFirst) + nowLast;
        }
        max *= 2;
        setNextFirst();
        setNextLast();
        array = newArray;
    }

    private void reduceSize() {
        T[] newArray = (T[]) new Object[max / 2];
        System.arraycopy(array, nowFirst, newArray, nowFirst - max / 4, size);
        nowFirst -= max / 4;
        nowLast = nowFirst + size;
        max /= 4;
        setNextFirst();
        setNextLast();
        array = newArray;
    }

    private void checkSize() {
        if (size + 1 > max) {
            extendSize();

        } else if (size > 16 && size - 1 <= max / 4) {
            reduceSize();
        }

    }


    private void setNextFirst() {
        //当前设置的first位置是nextFirst
        if (nowFirst == 0) {
            nextFirst = max - 1;
        } else {
            nextFirst--;
        }
    }


    private void setNextLast() {
        if (nowLast == max - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }

    }

    //remove专用的set方法
    private void setNextFirst(Integer index) {
        nextFirst = index;
    }


    private void setNextLast(Integer index) {
        nextLast = index;
    }


    public void addFirst(T item) {
        checkSize();
        array[nextFirst] = item;
        nowFirst = nextFirst;
        setNextFirst();
        size++;
    }

    public void addLast(T item) {
        checkSize();
        array[nextLast] = item;
        nowLast = nextLast;
        setNextLast();
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {

        setNextFirst(nowFirst);
        T removed = array[nowFirst];
        array[nowFirst] = null;
        size--;
        checkSize();
        return removed;
    }

    public T removeLast() {
        setNextLast(nowLast);
        T removed = array[nowLast];
        array[nowLast] = null;
        size--;
        checkSize();
        return removed;
    }

    public T get(int index) {
        return array[index];
    }

//    public Iterator<T> iterator() {
//    }
//
//    public boolean equals(Object o) {
//    }
}