package lessonTwo;

import java.util.Iterator;

public interface MyList<E> extends Iterable<E> {
    E get(int i);

    public void add(E obj);

    void set(int index, E obj);

    void insert(int index, E obj);

    void delete(int index);

    int length();


    Iterator iterator();

    class MyListIterator<E> implements Iterator<E> {
        int cursor;
        MyList list;

        public MyListIterator(MyList list) {
            this.list = list;
            this.cursor = -1;
        }
        @Override
        public boolean hasNext() {
            cursor=cursor+1;
            return cursor<list.length();
        }

        @Override
        public E next() {
            return (E) list.get(cursor);
        }
    }
}
