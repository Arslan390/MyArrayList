import java.util.Collection;

public class SuperArrayList<V> {

    private Object[] array;
    private int size;
    private int capacity;

    public SuperArrayList() {
        this.capacity = 10;
        this.array = new Object[capacity];
    }

    public SuperArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.capacity = 10;
        } else {
            throw new IllegalArgumentException("Неверная вместимость: " + initialCapacity);
        }
        this.array = new Object[capacity];
    }


    public void add(int index, V element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        if (size == array.length) array = grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

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

    public void clear() {
        capacity = 10;
        array = new Object[capacity];
        size = 0;
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
}
