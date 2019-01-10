package week1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private int numberOfOpenSites = 0;
    private boolean[][] openSitesArray;
    private boolean[][] fullSitesArray;
    private final WeightedQuickUnionUF structure;
    private boolean perculates;

    public Percolation(int n) // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new IllegalArgumentException();

        this.n = n;
        this.perculates = false;
        this.openSitesArray = new boolean[n + 1][n + 1];
        this.fullSitesArray = new boolean[n + 1][n + 1];
        this.numberOfOpenSites = 0;
        this.structure = new WeightedQuickUnionUF(n * n + 1);
    }

    public void open(int row, int col) // open site (row, col) if it is not open
                                       // already
    {
        if (isOpen(row, col))
            return;
        this.openSitesArray[row][col] = true;
        this.numberOfOpenSites++;
        if (unionPossibleNeighbors(row, col))
            fullRecursionCheck(row, col);
    }

    private void fullRecursionCheck(int row, int col) {
        if (row == this.n)
            this.perculates = true;
        this.fullSitesArray[row][col] = true;
        if (row - 1 >= 1 && isOpen(row - 1, col) && !isFull(row - 1, col)) {
            fullRecursionCheck(row - 1, col);
        }
        if (col - 1 >= 1 && isOpen(row, col - 1) && !isFull(row, col - 1)) {
            fullRecursionCheck(row, col - 1);
        }
        if (col + 1 <= n && isOpen(row, col + 1) && !isFull(row, col + 1)) {
            fullRecursionCheck(row, col + 1);
        }
        if (row + 1 <= n && isOpen(row + 1, col) && !isFull(row + 1, col)) {
            fullRecursionCheck(row + 1, col);
        } else
            return;

    }

    public boolean isOpen(int row, int col) // is site (row, col) open?
    {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        return openSitesArray[row][col];

    }

    public boolean isFull(int row, int col) // is site (row, col) full?
    {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        return this.fullSitesArray[row][col];

    }

    public int numberOfOpenSites() // number of open sites
    {
        return this.numberOfOpenSites;

    }

    public boolean percolates() // does the system percolate?
    {
        return this.perculates;

    }

    private boolean unionPossibleNeighbors(int row, int col) {
        boolean madeFull = false;
        if (row == 1)
            madeFull = true;
        int quCounter = quId(row, col);
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            structure.union(quCounter, quId(row - 1, col));
            if (!isFull(row, col) && isFull(row - 1, col)) {
                madeFull = true;
            }
        }
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            structure.union(quCounter, quId(row, col - 1));
            if (!isFull(row, col) && isFull(row, col - 1)) {
                madeFull = true;
            }
        }
        if (col + 1 <= n && isOpen(row, col + 1)) {
            structure.union(quCounter, quId(row, col + 1));
            if (!isFull(row, col) && isFull(row, col + 1)) {
                madeFull = true;
            }
        }
        if (row + 1 <= n && isOpen(row + 1, col)) {
            structure.union(quCounter, quId(row + 1, col));
            if (!isFull(row, col) && isFull(row + 1, col)) {
                madeFull = true;
            }
        }
        return madeFull;

    }

    private int quId(int row, int col) {
        return (row - 1) * n + col;
    }

}
