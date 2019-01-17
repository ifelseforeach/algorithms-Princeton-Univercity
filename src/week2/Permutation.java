package week2;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        if (args != null && args.length == 1) {

            int k = Integer.parseInt(args[0].trim());
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
           String line = "";//StdIn.readAll();
           while (!StdIn.isEmpty()) {
               String value = StdIn.readString();
               randomizedQueue.enqueue(value);
           }
           
                    
            System.out.println("FileLine=" + line + ", k=" + k);

            if (k > 0) {
                for (int i = 0; i < k; i++) {
                    System.out.println(randomizedQueue.dequeue());
                }
            }

        } else
            System.out.println("No arguments");
    }
}
