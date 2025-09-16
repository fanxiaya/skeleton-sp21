package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestLinkedListDeque {
    @Test
    public void testAddFirstSingle() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(10);
        // 检查size
        assertEquals(1, deque.size);
        // 检查第一个元素
        assertEquals(Integer.valueOf(10), deque.sentinel.getNext().item);
    }

    @Test
    public void testAddFirstMultiple() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);
        // 检查size
        assertEquals(3, deque.size);
        // 检查顺序
        assertEquals(Integer.valueOf(30), deque.sentinel.getNext().item);
        assertEquals(Integer.valueOf(20), deque.sentinel.getNext().getNext().item);
        assertEquals(Integer.valueOf(10), deque.sentinel.getNext().getNext().getNext().item);
    }

    @Test
    public void testAddLastSingle() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(5);
        assertEquals(1, deque.size);
        // 通过 addFirst 再 addLast 验证顺序
        deque.addFirst(3);
        assertEquals(Integer.valueOf(3), deque.sentinel.getNext().item);
        assertEquals(Integer.valueOf(5), deque.sentinel.getNext().getNext().item);
    }

    @Test
    public void testAddLastMultiple() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(3, deque.size);
        assertEquals(Integer.valueOf(1), deque.sentinel.getNext().item);
        assertEquals(Integer.valueOf(2), deque.sentinel.getNext().getNext().item);
        assertEquals(Integer.valueOf(3), deque.sentinel.getNext().getNext().getNext().item);
    }

    @Test
    public void testIsEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testPrintDequeEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.printDeque(); // 应该只打印一个换行
    }

    @Test
    public void testPrintDequeNonEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.printDeque(); // 应该打印 1 2 3
    }
}