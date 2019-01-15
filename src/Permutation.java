import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        if (args != null && args.length == 1) {

            int k = Integer.parseInt(args[0].trim());
            String fileLine = StdIn.readString().trim();         
            System.out.println("FileLine=" + fileLine + ", k=" + k);
           
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();          
            if (k > 0) {
                for (int i = 0; i < 3; i++) {
                    System.out.println(randomizedQueue.dequeue());
                }
            }

        } else
            System.out.println("No arguments");
    }
}
