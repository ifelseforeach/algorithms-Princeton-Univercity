package week1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;    

    public PercolationStats(int n, int trials) // perform trials independent
                                               // experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        this.thresholds = new double[trials];       
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            while (true) {
                percolation.open(StdRandom.uniform(1,  n + 1),  StdRandom.uniform(1,  n + 1));
                if (percolation.percolates()) {
                    thresholds[trial] = ((double) percolation.numberOfOpenSites()) / ((double) (n * n));
                    break;
                }
            }

        }
    }

    public double mean() // sample mean of percolation threshold
    {
        return StdStats.mean(thresholds);

    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(thresholds);

    }

    public double confidenceLo() // low endpoint of 95% confidence interval
    {

        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(this.thresholds.length);

    }

    public double confidenceHi() // high endpoint of 95% confidence interval
    {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(this.thresholds.length);

    }

    public static void main(String[] args) // test client (described below)
    {
        if (args != null && args.length == 2) {

            int n = Integer.parseInt(args[0].trim());
            int t = Integer.parseInt(args[1].trim());
            System.out.println("n=" + n + ", T=" + t);

            PercolationStats stats = new PercolationStats(n, t);
            String line = String.format("%1$-24s", "mean");
            System.out.print(line);
            System.out.println("= " + stats.mean());
            line = String.format("%1$-24s", "stddev");
            System.out.print(line);
            System.out.println("= " + stats.stddev());
            line = String.format("%1$-24s", "95% confidence interval");
            System.out.print(line);
            System.out.println("= [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

        } else
            System.out.println("No arguments");

    }
}
