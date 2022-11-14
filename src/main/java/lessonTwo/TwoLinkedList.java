package lessonTwo;

import java.util.Iterator;

public class TwoLinkedList<E> implements MyList<E> {
    private int size;
    private Node<E> tail, head;

    @Override
    public E get(int i) {
        if (i < 0 && i > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        if (tail.getIndex() == i) {
            return tail.getItem();
        }
        else if (head.getIndex() == i) {
            return head.getItem();
        } else if (size > 2) {
            Node<E> temp = tail.next;
            while (!temp.equals(head)) {
                if (temp.index == i) {
                    return temp.getItem();
                }
                temp=temp.next;
            }
        }
        return null;
    }

    @Override
    public void add(E obj) {
        if (this.size == 0) {
            head = tail = new Node<E>(size, obj, null, null);
        } else if (this.size == 1) {
            head = new Node<>(size, obj, tail, null);
            tail.setNext(head);
        } else {
            Node<E> temp = new Node<>(size, obj, head, null);
            head.setNext(temp);
            head = temp;
        }
        size++;
    }

    @Override
    public void set(int index, E obj) {
        if (index < 0 && index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        if (tail.getIndex() == index) {
            tail.setItem(obj);
        }
        else if (head.getIndex() == index) {
            head.setItem(obj);
        } else if (size > 2) {
            Node<E> temp = tail.next;
            while (!temp.equals(head)) {
                if (temp.index == index) {
                    temp.setItem(obj);
                    break;
                }
                temp=temp.next;
            }
        }
    }

    @Override
    public void insert(int index, E obj) {
        if (index < 0 && index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        Node<E> inserted = new Node<>(index, obj, null, null);
        if (tail.getIndex() == index) {
            changeIndex(index, true);
            tail.setPrev(inserted);
            inserted.setNext(tail);
            tail = inserted;
        }
        else if (head.getIndex() == index) {
            changeIndex(index, true);
            inserted.setNext(head);
            inserted.setPrev(head.prev);
            inserted.prev.setNext(inserted);
            head.setPrev(inserted);
        } else if (size > 2) {
            Node<E> temp = tail.next;
            while (!temp.equals(head)) {
                if (temp.index == index) {
                    changeIndex(index, true);
                    inserted.setNext(temp);
                    inserted.setPrev(temp.prev);
                    temp.setPrev(inserted);
                    inserted.prev.setNext(inserted);
                    break;
                }
                temp=temp.next;
            }
        }
        size++;
    }

    @Override
    public void delete(int index) {
        if (index < 0 && index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index incorrect");
        }
        if (tail.getIndex() == index) {
            changeIndex(index, false);
            tail.next.setPrev(null);
            tail = tail.next;
        }
        if (head.getIndex() == index) {
            changeIndex(index, false);
            head.prev.setNext(null);
            head = head.prev;
        } else if (size > 2) {
            Node<E> temp = tail.next;
            while (!temp.equals(head)) {
                if (temp.index == index) {
                    changeIndex(index, false);
                    temp.next.setPrev(temp.prev);
                    temp.prev.setNext(temp.next);
                    break;
                }
                temp=temp.next;
            }
        }
        size--;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator(this);
    }

    private void changeIndex(int index, boolean up) {
        Node<E> current = tail;
        int oldIndex = tail.getIndex();
        while (oldIndex < size-1) {
            oldIndex = current.getIndex();
            if (index <= current.getIndex()) {
                if (up) {
                    current.setIndex(current.getIndex() + 1);
                } else {
                    current.setIndex(current.getIndex() - 1);
                }
            }
            current = current.next;
        }
    }

    @Override
    public String toString() {
        return "TwoLinkedList{" +
                "tail=" + tail +
                ", head=" + head +
                '}';
    }

    class Node<E> {
        int index;
        private E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(int index, E item, Node<E> prev, Node<E> next) {
            this.index = index;
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrev() {
            return prev != null;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public int getIndex() {
            return index;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            String previos;
            if (prev != null) {
                previos = prev.getItem().toString();
            } else {
                previos = "";
            }
            String nextNode;
            if (next != null) {
                nextNode = next.getItem().toString();
            } else {
                nextNode = "";
            }
            return "Node{" +
                    "index=" + index +
                    ", item=" + item +
                    ", prev=" + previos +
                    ", next=" + nextNode +
                    '}';
        }
    }
}
