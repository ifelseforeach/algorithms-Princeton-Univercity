package week2;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class TestingRandomness {
    
    private static void printLength(RandomizedQueue<Integer> rndQueue) {
        System.out.println("Size=" + rndQueue.size());
        for (Integer c : rndQueue) {
            System.out.println(c);
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> rndQueue = new RandomizedQueue<Integer>();
       
        double mean = 0;
        double sum  = 0;
        int N =8;
        for (int i=0; i<N; i++) {
            int number = StdRandom.uniform(10);
            Integer integer = new Integer(number);
            rndQueue.enqueue(integer);
            sum+=number;
        }
        
        sum = sum / N;
        System.out.println(sum);
        
        System.out.println(rndQueue.size());    
        
        System.out.println("Iterator" + rndQueue.toString());  
        Iterator<Integer> iterator = rndQueue.iterator();
        while (iterator.hasNext()) {
            Integer number = (Integer) iterator.next();
            System.out.println(number);    
        }
        
        
        for (int i=0; i<N-1; i++) {      
            System.out.println(rndQueue.toString());
            int number  = rndQueue.dequeue().intValue();
            sum+=number;
        }
        
        sum = sum / N;
        System.out.println(sum);
        
       /*  System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample());
        System.out.println(rndQueue.sample()); */
        
        for (Integer c : rndQueue) {
            System.out.println("Random=" + c);
        }

        rndQueue.dequeue();
        printLength(rndQueue);

        rndQueue.dequeue();
        printLength(rndQueue);

        rndQueue.dequeue();
        printLength(rndQueue);

        rndQueue.dequeue();
        printLength(rndQueue);
    }

}
