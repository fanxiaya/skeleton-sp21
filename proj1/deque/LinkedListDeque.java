package deque;

public class LinkedListDeque<T> {

    /*内部类node*/
    class Node {
        T item;
        Node next;
        Node prev;

        //构造方法
        Node(T item) {
            prev = null;
            this.item = item;
            next = null;
        }

        //后置节点设置
        void setNext(Node next) {
            this.next = next;
        }

        //前置节点设置
        void setPrev(Node prev) {
            this.prev = prev;
        }

        //        前置节点获取
        Node getPrev() {
            return this.prev;
        }

        //        后置节点获取
        Node getNext() {
            return this.next;
        }


    }

    //    哨兵节点
    Node sentinel;
    int size;

    LinkedListDeque() {
        this.sentinel = new Node(null);
        this.size = 0;
        this.sentinel.setNext(this.sentinel);
        this.sentinel.setPrev(this.sentinel);

    }

    public void addFirst(T item) {
        this.size++;
        Node addedNode = new Node(item);
//        获取原有的第一个节点
        Node prevFirstNode = this.sentinel.getNext();
//sentinel和新节点头尾链接
        this.sentinel.setNext(addedNode);
        addedNode.setPrev(this.sentinel);
//新节点和原来的新节点头尾链接
        prevFirstNode.setPrev(addedNode);
        addedNode.setNext(prevFirstNode);

    }

    public void addLast(T item) {
        this.size++;
        Node addedNode = new Node(item);
//        获取原有的尾节点
        Node prevLastNode = this.sentinel.getPrev();
//新节点和原尾节点头尾链接
        prevLastNode.setNext(addedNode);
        addedNode.setPrev(prevLastNode);
//新节点和原来的头节点链接
        this.sentinel.setPrev(addedNode);
        addedNode.setNext(this.sentinel);

    }

    public boolean isEmpty() {
        return size == 0;
    }

    //    打印和打印辅助方法
    private void printDequeHelper(Node node) {
        if (node.item == null) {
            System.out.println();
        } else {
            System.out.print(node.item + " ");
            printDequeHelper(node.getNext());
        }
    }

    public T removeFirst() {
        if (isEmpty()) return null;

        this.size--;
//     获取原来的头节点和2th节点
        Node oldFirst = this.sentinel.getNext();
        Node oldSecond = oldFirst.getNext();
        this.sentinel.setNext(oldSecond);
        oldSecond.setPrev(this.sentinel);
        return oldFirst.item;
    }

    public T removeLast() {
        if (isEmpty()) return null;

        this.size--;
//     获取原来的尾节点和-2th节点
        Node oldLast = this.sentinel.getPrev();
        Node oldSecond = oldLast.getPrev();
        this.sentinel.setPrev(oldSecond);
        oldSecond.setNext(this.sentinel);
        return oldLast.item;
    }


    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
        } else {
            printDequeHelper(sentinel.getNext());
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node current = sentinel;
        while (index >= 0) {
            current = sentinel.getNext();
            index--;
        }
        return current.item;

    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel).item;

    }

    private Node getRecursiveHelper(int index, Node node) {
        if (index < 0) {
            return node;
        } else {
            node = node.getNext();
            return getRecursiveHelper(--index, node);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque<?>)) {
            return false;
        }
        LinkedListDeque<?> other = (LinkedListDeque<?>) o;
        if (this.size != other.size) {
            return false;
        }
        Node thisNode = this.sentinel.getNext();
        Node otherNode = (Node) other.sentinel.getNext();
        while (thisNode != this.sentinel) {
            if (!thisNode.item.equals(otherNode.item)) {
                return false;
            }
            thisNode = thisNode.getNext();
            otherNode = otherNode.getNext();
        }
        return true;
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeFirst();
        deque.removeLast();
        System.out.println(deque.getRecursive(0));
        System.out.println(deque.getRecursive(3));


        deque.printDeque();

    }

}