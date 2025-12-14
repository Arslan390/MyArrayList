import java.util.*;

public class SuperArrayList<V> implements List<V> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;
    private int capacity;

    public SuperArrayList(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            this.array = new Object[capacity];
        } else if (capacity == 0) {
            this.capacity = DEFAULT_CAPACITY;
            this.array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Некорректная вместимость: " + capacity);
        }
    }

    public SuperArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = new Object[capacity];
    }

    public SuperArrayList(Collection<? extends V> collection) {
        Object[] elements = collection.toArray();
        if ((size = elements.length) != 0) {
            if (collection.getClass() == SuperArrayList.class) {
                array = elements;
            } else {
                array = Arrays.copyOf(elements, size, Object[].class);
            }
        } else {
            this.capacity = DEFAULT_CAPACITY;
            this.array = new Object[capacity];
        }
    }


    public Object[] grow(int requiredCapacity) {
        while (requiredCapacity > capacity) {
            capacity = capacity << 1;
        }
        Object[] oldArray = array;
        Object[] newArray = new Object[capacity];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;

    }

    public Object[] grow() {
        return grow(size + 1);
    }

    @Override
    public void add(int index, V element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        if (size == array.length) array = grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends V> collection) {
        Object[] elements = collection.toArray();
        if (elements.length == 0) return false;
        if (elements.length > capacity - size) {
            int requiredCapacity = elements.length + size;
            array = grow(requiredCapacity);
        }
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public void clear() {
        capacity = 10;
        array = new Object[capacity];
        size = 0;
    }

    @Override
    public V get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        return (V) array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        V oldValue = (V) array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sort(Comparator<? super V> comparator) {
        int leftIndex = 0;
        int rightIndex = size - 1;
        quickSort(comparator, leftIndex, rightIndex);
    }

    private void quickSort(Comparator<? super V> comparator, int start, int end) {
        if (start >= end || array.length == 0) return;
        V pivot = (V) array[(start + end) / 2];
        int startMarker = start, endMarker = end;
        while (startMarker <= endMarker) {
            while (comparator.compare((V) array[startMarker], pivot) < 0) startMarker++;
            while (comparator.compare((V) array[endMarker], pivot) > 0) endMarker--;
            if (startMarker <= endMarker) {
                V temp = (V) array[startMarker];
                array[startMarker] = array[endMarker];
                array[endMarker] = temp;
                startMarker++;
                endMarker--;
            }
        }
        if (start < endMarker) quickSort(comparator, start, endMarker);
        if (startMarker < end) quickSort(comparator, startMarker, end);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Методы которые еще не реализованны
     */

    @Override
    public boolean add(V v) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public Iterator<V> iterator() {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends V> c) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public V set(int index, V element) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public ListIterator<V> listIterator() {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public ListIterator<V> listIterator(int index) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }

    @Override
    public List<V> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Будет доступно в следующей версии.");
    }
}