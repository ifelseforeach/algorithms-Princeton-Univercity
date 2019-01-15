import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {

    }

    public boolean isEmpty() {
        return (first == null && last == null);
        // is the deque empty?
    }

    public int size() {
        return this.size;
        // return the number of items on the deque
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null)
            throw new IllegalArgumentException();
        if (isEmpty()) {
            Node node = new Node(item, null, null);
            this.first = node;
            this.last = node;
            size++;
        } else {
            Node exFirst = this.first;
            Node node = new Node(item, exFirst, null);
            exFirst.previous = node;
            this.first = node;
            size++;
        }
    }

    public void addLast(Item item) { // add the item to the end
        if (item == null)
            throw new IllegalArgumentException();
        if (isEmpty()) {
            Node node = new Node(item, null, null);
            this.first = node;
            this.last = node;
            size++;
        } else {
            Node exLast = this.last;
            Node node = new Node(item, null, exLast);
            exLast.next = node;
            this.last = node;
            size++;
        }

    }

    public Item removeFirst() { // remove and return the item from the front
        if (isEmpty())
            throw new NoSuchElementException();
        Node exFirst = this.first;
        Item item = exFirst.item;
        Node newFirst = exFirst.next;
        if (newFirst == null) {
            this.first = null;
            this.last = null;
        } else {
            newFirst.previous = null;
            this.first = newFirst;
        }
        size--;
        return item;

        // remove and return the item from the front
    }

    public Item removeLast() { // remove and return the item from the end
        if (isEmpty())
            throw new NoSuchElementException();
        Node exLast = this.last;
        Item item = exLast.item;
        Node newLast = exLast.previous;
        if (newLast == null) {
            this.first = null;
            this.last = null;
        } else {
            newLast.next = null;
            this.last = newLast;
        }
        size--;
        return item;

    }

    public Iterator<Item> iterator() { // return an iterator over items in order
                                       // from front to end
        return new DequeIterator<Item>(this);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!this.isEmpty()) {
            Iterator<Item> iterator = this.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    public static void main(String[] args) {
        // unit testing (optional)
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Deque<Item>.Node node;
        private final Deque<Item> collection;

        private DequeIterator(Deque<Item> collection) {

            this.collection = collection;
            this.node = collection.first.previous;

        }

        @Override
        public boolean hasNext() {
            return ((this.node != null && this.node.next != null) || (this.node == null && collection.first != null)) ? true : false;
        }

        @Override
        public Item next() {
            if (this.node != null && this.node.next != null) {
                this.node = this.node.next;
                return node.item;
            } else if (this.node == null && collection.first != null) {
                this.node = collection.first;
                return node.item;
            } else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class Node {

        private final Item item;
        private Node next;
        private Node previous;

        public Node(Item item1, Node next1, Node previous1) {
            this.item = item1;
            this.next = next1;
            this.previous = previous1;
        }

    }

}
