import java.util.HashMap;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Collections;

public class PercolationTiming {
  // creates a list of all cells in an N x N grid.
  public static LinkedList<int[]> cells(int n) {
    LinkedList<int[]> cells = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int[] cell = {i, j};
        cells.push(cell);
      }
    }

    Collections.shuffle(cells);
    return cells;
  }

  // Randomly open cells untill the matrix percolates
  // params n - size of the grid
  // returns the number of opened cells
  public static int test(int n, ListIterator<int[]> cells) {
    return test(n, cells, true);
  }
  public static int test(int n, ListIterator<int[]> cells, boolean weighted) {
    Percolation perc = new Percolation(n, weighted); // initialize percolation matrix

    int opened_cells = 0;

    while (!perc.percolates()) {           // open cells untill the matrix percolates
      int[] cell = cells.next();
      perc.open(cell[0], cell[1]);
      opened_cells++;

    }
    return opened_cells;                   // return the number of opened cells
  }

  public static void main(String[] args) {
    LinkedList<int[]> cells;

    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    map.put("QuickUnionUF", false);
    map.put("WeightedQuickUnionUF", true);

    for (String type : map.keySet()) {
      Boolean weighted = map.get(type);
      int n, t, max_n, max_t;

      System.out.printf("%n---------- %s (Weighted %s) ----------%n", type, (weighted ? "true" : "false"));

      n = (int) Math.pow(2,4); max_n = (int) Math.pow(2, 12); t = 1;
      System.out.printf("%s: doubling N from %d to %d with T = %d%n", type, n, max_n, t);
      while (n <= max_n) {
        cells = cells(n); // create an array of all cell coordinates

        Stopwatch sw = new Stopwatch();

        for (int test = 0; test < t; test++) {             // test t times
          Collections.shuffle(cells);
          PercolationTiming.test(n, cells.listIterator(), weighted);
        }
        System.out.printf("N: %5d, time: %f%n", n, sw.elapsedTime());
        n *= 2;                                            // double n
      }

      System.out.println("--");

      t = (int) Math.pow(2,4); max_t = (int) Math.pow(2, 12); n = 200;
      System.out.printf("%s: doubling T from %d to %d with N = %d%n", type, t, max_t, n);
      cells = cells(n); // create an array of all cell coordinates
      while (t <= max_t) {
        Stopwatch sw = new Stopwatch();

        for (int test = 0; test < t; test++) {             // test t times
          Collections.shuffle(cells);
          PercolationTiming.test(n, cells.listIterator(), weighted);
        }

        System.out.printf("T: %5d, time: %f%n", t, sw.elapsedTime());
        t *= 2;                                            // double n
      }
    }
  }
}
