package by.astondev.homework2.taskcollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomArrayList<T> implements Iterable<T> {
    private static final int CAPACITY_DEFAULT = 10;
    private static final int COUNT_INCREASE_ELEMENT_DEFAULT = 1;

    private T[] values;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public CustomArrayList(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("size must be greater than 0");
        }
        values = (T[]) new Object[initSize];
    }

    public CustomArrayList() {
        this(CAPACITY_DEFAULT);
    }

    /**
     * @param element added to CustomArrayList
     * @return true if element was added to CustomArrayList
     */
    public boolean add(T element) {
        ensureCapacity(COUNT_INCREASE_ELEMENT_DEFAULT);
        values[size++] = element;
        return true;
    }

    /**
     * @param index index of element
     * @return element of array
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) {
        if (!isCorrectIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        return values[index];
    }

    /**
     * @return first element of array
     * @throws NoSuchElementException array is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return values[0];
        }
    }

    /**
     * @return last element of array
     * @throws NoSuchElementException
     */
    public T getLast() {
        int last = size - 1;

        if (last < 0) {
            throw new NoSuchElementException();
        } else {
            return values[last];
        }
    }

    /**
     * @param index index of element that need to remove
     * @return removed element
     * @throws IndexOutOfBoundsException if index incorrect
     */
    public T remove(int index) {
        if (!isCorrectIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        T element = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;

        return element;
    }

    /**
     * @param element element that need to remove. The first one found will delete
     * @return removed element
     * @throws NoSuchElementException when element not found
     */
    public T remove(T element) {
        int indexElement = findIndexElement(element);
        if (indexElement != -1) {
            return remove(indexElement);
        }
        throw new NoSuchElementException();
    }

    /**
     * @param list CustomArrayList<? extends T>
     * @return true if elements from list was added to current CustomArrayList
     */
    public boolean addAll(CustomArrayList<? extends T> list) {
        if (list == null || list.size == 0) {
            return false;
        }

        int sizeAddedList = list.size;
        ensureCapacity(sizeAddedList);

        T[] addedArray = list.values;
        System.arraycopy(addedArray, 0, values, size, sizeAddedList);

        size += sizeAddedList;

        return true;
    }

    /**
     * @param element element whose presence in this list is to be tested
     * @return true if array contains the element
     */
    public boolean contains(T element) {
        return findIndexElement(element) != -1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.stream(values)
                .limit(size)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < size;
            }

            @Override
            public T next() {
                currentIndex++;
                if (isCorrectIndex(currentIndex)) {
                    return values[currentIndex];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

//    public static <T extends Comparable<T>> void sort(CustomArrayList<T> customArrayList) {
//        Arrays.sort(customArrayList.values);
//    }

    private void ensureCapacity(int necesseryIncrease) {
        int needCapacity = this.size + necesseryIncrease;
        if (needCapacity > values.length) {
            int newCapacity = Math.max(defaultIncreaseCapacity(), necesseryIncrease);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private int defaultIncreaseCapacity() {
        return values.length + values.length / 2 + 1;
    }

    private boolean isCorrectIndex(int index) {
        return index >= 0 && index < size;
    }

    private int findIndexElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                return i;
            }
        }
        return -1;
    }

}
