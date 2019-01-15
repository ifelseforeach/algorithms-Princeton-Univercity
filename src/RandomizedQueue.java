import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue() {
        // construct an empty randomized queue
    }

    public boolean isEmpty() { // is the randomized queue empty?
        return false;

    }

    public int size() {
        return 0; // return the number of items on the randomized queue
    }

    public void enqueue(Item item) { // add the item
        if (item == null)
            throw new IllegalArgumentException();
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        return null; // remove and return a random item
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return null; // return a random item (but do not remove it)
    }

    public Iterator<Item> iterator() {
        return null; // return an independent iterator over items in random
                     // order
    }

    public static void main(String[] args) {
        // unit testing (optional)
    }

    private class RandomizedQueueInterator<Item> implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Item next() {
            if (!this.hasNext())
                throw new NoSuchElementException(); // TODO Throw a java.util.NoSuchElementException if the client calls
            // the next() method in the iterator when there are no more items to
            // return.
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
