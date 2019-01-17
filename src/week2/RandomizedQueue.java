package week2;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] s;

    public RandomizedQueue() {
        s = (Item[])new Object[1];

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append(" ");
            sb.append(s[i]);
        }
        return sb.toString();

    }

    public boolean isEmpty() { // is the randomized queue empty?
        return (size == 0);
        // is the deque empty?

    }

    public int size() {
        return this.size; // return the number of items on the randomized queue
    }

    public void enqueue(Item item) { // add the item
        if (item == null)
            throw new IllegalArgumentException();
        if (s.length - size == 1)
            doubleSize();
        if (size == 0)
            s[size] = item;
        else {
            int randNumber = StdRandom.uniform(size + 1);
            if (randNumber == size)
                s[size] = item;
            else {
                Item tempItem = this.s[randNumber];
                s[randNumber] = item;
                s[size] = tempItem;
            }
        }
        size++;
    }

    private void doubleSize() {
        Item[] tempArr = s;
        s = (Item[])new Object[tempArr.length * 2];
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] != null) {
                s[i] = tempArr[i];
            } else
                break;
        }
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randNumber = StdRandom.uniform(size);
        Item tempItem = this.s[randNumber];
        Item lastItem = s[size - 1];
        this.s[randNumber] = lastItem;
        s[size - 1] = null;
        size--;
        if ((size != 0 && s.length / size >= 4) || size == 0)
            halfSize();

        return tempItem;
    }

    private void halfSize() {
        Item[] tempArr = s;
        this.s = (Item[])new Object[s.length / 2];

        for (int i = 0; i < this.s.length; i++) {
            if (tempArr[i] != null) {
                this.s[i] = tempArr[i];
            } else
                break;
        }
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = s[StdRandom.uniform(size)];

        return item;
        // return a random item (but do not remove it)
    }
 
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueInterator<Item>(this); // return an
                                                         // independent
                                                         // iterator over items
                                                         // in random
        // order
    }

    private class RandomizedQueueInterator<Item> implements Iterator<Item> {

        private final RandomizedQueue<Item> collection;
        private int pointer;       
        private int counter;

        private RandomizedQueueInterator(RandomizedQueue<Item> collection) {

            this.collection = collection;
            if (!collection.isEmpty())
                pointer = StdRandom.uniform(collection.size);           
            counter = 0;

        }

        @Override
        public boolean hasNext() {
            return (!isEmpty() && (counter < this.collection.size));
        }

        @Override
        public Item next() {
            if (this.collection.isEmpty() || counter >= this.collection.size)
                throw new NoSuchElementException();            
            Item item = collection.s[pointer];   
            pointer = ((pointer+1) % size);
            counter++;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
