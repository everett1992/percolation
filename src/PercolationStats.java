import java.util.LinkedList;
import java.util.Collections;

public class PercolationStats {
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
  public static int test(int n) {
    Percolation perc = new Percolation(n); // initialize percolation matrix
    LinkedList<int[]> cells = cells(n);    // generate list of random cells to open

    int opened_cells = 0;

    while (!perc.percolates()) {           // open cells untill the matrix percolates
      int[] cell = cells.pop();
      perc.open(cell[0], cell[1]);
      opened_cells++;

    }
    return opened_cells;                   // return the number of opened cells
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.printf("Missing argument: %d of 2%n", args.length);
      System.exit(1);
    }

    int t = 0;
    int n = 0;

    try {
      t = Integer.parseInt(args[0]);
      n = Integer.parseInt(args[1]);
    } catch (NumberFormatException ex) {
      System.err.println("Illegal argument: t and n must be numbers");
      System.exit(1);
    }

    if (t <= 0 || n <= 0) {
      System.err.println("Illegal argument: t and n must be greater than 0");
      System.exit(1);
    }
    System.out.printf("Running %d tests on %d x %<d grids%n", t, n);

    double[] opened_cells = new double[t];

    // [1..t]
    for (int test = 0; test < t; test++) {
      opened_cells[test] = ((double) test(n))/(Math.pow(n,2));

    }

    double mean = StdStats.mean(opened_cells);
    double stddev = StdStats.stddev(opened_cells);
    double conf_95_low = mean - (1.96 * stddev);
    double conf_95_high = mean + (1.96 * stddev);
    System.out.printf("mean percolation threshold = %f%n", mean);
    System.out.printf("stddev                     = %f%n", stddev);
    System.out.printf("95%% confidence interval    = %f..%f%n", conf_95_low, conf_95_high);
  }
}
