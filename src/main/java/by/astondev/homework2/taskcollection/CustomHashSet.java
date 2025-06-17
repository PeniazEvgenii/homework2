package by.astondev.homework2.taskcollection;

import java.util.HashMap;
import java.util.Iterator;

public class CustomHashSet<T> implements Iterable<T> {
    private static final int CAPACITY_DEFAULT = 16;
    private static final double LOAD_FACTOR_DEFAULT = 0.75;

    private Node<T>[] data;
    private int size;
    private int threshold;

    @SuppressWarnings("unchecked")
    public CustomHashSet() {
        data = (Node<T>[]) new Node[CAPACITY_DEFAULT];
        threshold = calculateThreshold();
    }

    /**
     * Метод добавления объекта в CustomHashSet<T>. CustomHashSet не хранит null значения
     * @param element объект <T>
     * @return true при успешном добавлении объекта. При добавлении null - return false
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        if (isNeedResize()) {
            resize();
        }

        int hash = hash(element);
        int indexBucket = getIndexBucket(hash);

        Node<T> newNode = new Node<>(element, hash, null);

        if (data[indexBucket] == null) {
            data[indexBucket] = newNode;
            size++;
            return true;
        }

        Node<T> currentNode = data[indexBucket];

        while (true) {
            if (currentNode.hash == hash && currentNode.value.equals(element)) {
                return false;
            }
            if (currentNode.next != null) {
                currentNode = currentNode.next;
            } else {
                break;
            }
        }

        currentNode.next = newNode;
        size++;

        return true;
    }

    public boolean remove(T element) {
        if (element == null) {
            return false;
        }

        int hash = hash(element);
        int indexBucket = getIndexBucket(hash);

        Node<T> currentNode = data[indexBucket];
        Node<T> prevNode = null;

        while (currentNode != null) {
            if(currentNode.hash == hash && currentNode.value.equals(element)) {
                if(prevNode == null) {
                    data[indexBucket] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return true;
            } else {
                prevNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current;
            private int bucket = 0;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                while (bucket < data.length && data[bucket] == null) {
                    bucket++;
                }

                current = current == null ? data[bucket] : current.next;

                T result = current.value;
                if (current.next == null) {
                    current = null;
                    bucket++;
                }
                count++;

                return result;
            }
        };
    }

    /**
     * Метод вычисления хеш-значения ключа, с использованием подмешивания старших битов
     * в младшие, чтобы улучшить распределение по бакетам. {@link HashMap#hash(Object)} )}
     * @param element элемент, у которого вычисляется хеш-значение
     * @return хеш-значение элемента
     */
    private int hash(T element) {
        int hash = element.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int getIndexBucket(int hash) {
        return hash % data.length;
    }

    private int calculateThreshold() {
        return (int) (data.length * LOAD_FACTOR_DEFAULT);
    }

    private boolean isNeedResize() {
        return size + 1 > threshold;
    }

    private void resize() {
        int newLength = data.length * 2;
        Node<T>[] newData = (Node<T>[]) new Node[newLength];

        for (Node<T> node : data) {
            if (node == null) {
                continue;
            }
            Node<T> current = node;
            while (current != null) {
                int newBucket = current.getHash() % newLength;
                Node<T> nextNode = current.next;

                current.next = newData[newBucket];
                newData[newBucket] = current;

                current = nextNode;
            }
        }

        data = newData;
        threshold = calculateThreshold();
    }

    private static class Node<T> {
        private final T value;
        private final int hash;
        private Node<T> next;

        public Node(T value, int hash, Node<T> next) {
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public Node<T> getNext() {
            return next;
        }
    }
}
