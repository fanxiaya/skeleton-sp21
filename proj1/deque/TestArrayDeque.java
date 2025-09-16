package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayDeque {
    @Test
    public void testAddFirstAndSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(10);
        assertEquals(1, deque.size());
        deque.addFirst(20);
        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(20), deque.get(0));
        assertEquals(Integer.valueOf(10), deque.get(1));
    }

    @Test
    public void testAddLastAndRemoveFirst() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("a");
        deque.addLast("b");
        assertEquals(2, deque.size());
        assertEquals("a", deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals("b", deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        assertEquals(Integer.valueOf(3), deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(1), deque.removeLast());
        assertEquals(Integer.valueOf(2), deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testGet() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addFirst("x");
        deque.addLast("y");
        assertEquals("x", deque.get(0));
        assertEquals("y", deque.get(1));
        assertNull(deque.get(-1));
        assertNull(deque.get(2));
    }

    @Test
    public void testPrintDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.printDeque(); // 只检查不抛异常
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = 100;
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }
        assertEquals(n, deque.size());
        for (int i = 0; i < n; i++) {
            assertEquals(Integer.valueOf(i), deque.get(i));
        }
        for (int i = 0; i < n; i++) {
            assertEquals(Integer.valueOf(i), deque.removeFirst());
        }
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            d1.addLast(i);
            d2.addLast(i);
        }
        assertTrue(d1.equals(d2));
        d2.removeLast();
        assertFalse(d1.equals(d2));
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }
        int idx = 0;
        for (int x : deque) {
            assertEquals(idx++, x);
        }
        assertEquals(5, idx);
    }
}