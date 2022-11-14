package lessonTwo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DynamicList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] mass;
    private int size;
    private int length;

    public DynamicList() {
        mass = new Object[DEFAULT_CAPACITY];
        this.length = DEFAULT_CAPACITY;
    }

    public DynamicList(int capacity) {
        mass = new Object[capacity];
        this.length = capacity;
    }

    @Override
    public E get(int i) {
        if (i < 0 && i > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        return (E) mass[i];
    }

    @Override
    public void add(E o) {
        if (mass.length == size) {
            mass = Arrays.copyOf(mass, (int) (length * 1.5));
        }
        mass[size] = o;
        this.size++;
    }

    @Override
    public void set(int index, E obj) {
        mass[index] = obj;
    }

    @Override
    public void insert(int index, E o) {
        if (index < 0 && index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        if (mass.length == size) {
            mass = Arrays.copyOf(mass, (int) (length * 1.5));
        }
        Object old = mass[index];
        mass[index] = o;

        for (int i = index + 1; i < mass.length - 1; i++) {
            if (old != null) {
                Object current = mass[i];
                mass[i] = old;
                old = current;
            }
        }
        size++;
    }


    @Override
    public void delete(int index) {
        for (int i = index; i < mass.length - 2; i++) {
            mass[i] = mass[i + 1];
            if ((i + 1 == mass.length - 1) && (mass[i + 1] != null)) {
                mass[i + 1] = null;
            }
        }
        size--;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator(this);
    }

    @Override
    public String toString() {
        return "DynamicList: {" +
                Arrays.stream(this.mass).filter(m -> m != null)
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
                + "}";
    }
}
