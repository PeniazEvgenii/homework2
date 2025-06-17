package by.astondev.homework2.taskcollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public boolean add(T element) {
        Node<T> currentNode = last;
        Node<T> newNode = new Node<>(null, null, element);

        if (currentNode == null) {
            first = newNode;
        } else {
            currentNode.next = newNode;
            newNode.previous = currentNode;
        }
        last = newNode;
        size++;
        return true;
    }

    public boolean addFirst(T element) {
        Node<T> currentNode = first;
        Node<T> newNode = new Node<>(null, null, element);

        if (currentNode == null) {
            last = newNode;
        } else {
            newNode.next = currentNode;
            currentNode.previous = newNode;
        }
        first = newNode;
        size++;
        return true;
    }

    public boolean addAll(CustomLinkedList<? extends T> list) {
        if (list == null) {
            return false;
        }

        for (T value : list) {
            add(value);
        }

        return true;
    }

    public T get(int index) {
        if (!isCorrectIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> currentNode = getNodeByIndex(index);
        return currentNode.value;
    }

    private Node<T> getNodeByIndex(int index) {
        int halfList = size / 2;
        Node<T> currentNode;

        if (index < halfList) {
            currentNode = first;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = last;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.previous;
            }

        }
        return currentNode;
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.value;
    }

    public int size() {
        return size;
    }

    public T remove(int index) {
        if (!isCorrectIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> delededNode = getNodeByIndex(index);
        T result = delededNode.value;

//        if(delededNode.previous == null) {
//            removeFirst();
//        } else if(delededNode.next == null) {
//            removeLast();
//        } else {
//            Node<T> next = delededNode.next;
//
//            delededNode.previous.next = next;
//            next.previous = delededNode.previous;
//            size--;
//        }

        if (delededNode.previous == null) {
            first = delededNode.next;
        } else {
            delededNode.previous.next = delededNode.next;
        }

        if (delededNode.next == null) {
            last = delededNode.previous;
        } else {
            delededNode.next.previous = delededNode.previous;
        }
        size--;
        return result;
    }

    public T removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        T result = first.value;

        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first.next.previous = null;
            first = first.next;
        }
        size--;
        return result;
    }

    public T removeLast() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        T result = last.value;

        if (last.previous == null) {
            first = null;
            last = null;
        } else {
            last.previous.next = null;
            last = last.previous;
        }

        size--;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = first;

        while (current != null) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(current.value);
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }
        };
    }

    private boolean isCorrectIndex(int index) {
        return index >= 0 && index < size;
    }


    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private final T value;

        public Node(Node<T> next, Node<T> previous, T value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }
    }
}
