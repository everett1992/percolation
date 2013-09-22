import java.util.LinkedList;
import java.lang.Math;

public class Percolation {

  private static String GRID_OVERSIZE_MESSAGE =
      "Grid too large, N must be less than " + Math.sqrt(Integer.MAX_VALUE);

  // Tracks which cells are connected
  private QuickUnionUF union;

  // open cells are true, closed cells are false
  private boolean[][] cells;

  private int virt_top;
  private int virt_bottom;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {

    // N will be doubled, so if n > sqrt(MAX_INT)
    // there would be an overflow.
    if (N > (int)Math.sqrt(Integer.MAX_VALUE)) {
      throw new RuntimeException(GRID_OVERSIZE_MESSAGE);
    }


    cells = new boolean[N][N];


    // Create a union data structure with N^2 + 2 seperate groups
    // Cell second to last cell is the virtual top, and last cell is the virtual bottom
    int num_cells = (int)Math.pow(N, 2);
    union = new QuickUnionUF(num_cells + 2);

    // Join the top row of cells to the virtual top
    virt_bottom = num_cells + 1;
    for (int i = 0; i < N; i++) {
      union.union(idx(i, N - 1), virt_bottom);
    }

    // Join the bottom row of cells to the virtual bottom
    virt_top = num_cells;
    for (int i = 0; i < N; i++) {
      union.union(idx(i, 0), virt_top);
    }

    if (Globals.verbose) {
      System.out.printf("created %d x %<d grid%n", N);
    }

  }

  // open site (row i, col j) if it is not already
  public void open(int i, int j) {
    if (!valid_coord(i, j)) {
      throw new RuntimeException(String.format("cell %d, %d out of bounds", i, j));
    } else if (cells[i][j] != true) {
      cells[i][j] = true;

      int[][] adj_cells = {
        {i + 1, j},     // left cell
        {i - 1, j},     // right cell
        {i,     j + 1}, // bottom cell
        {i,     j - 1}  // top cell
      };

      for (int[] cell : adj_cells) {
        if (is_open(cell[0], cell[1])) {
          union.union(idx(i, j), idx(cell[0], cell[1]));
        }
      }
    } else {
      if (Globals.verbose) {
        System.out.printf("Cell %d, %d is already open%n", i, j);
      }
    }
  }

  // is site (row i, col j) open?
  public boolean is_open(int i, int j) {
    return (valid_coord(i, j) && cells[i][j]);
  }

  // is site (row i, col j) full?
  public boolean is_full(int i, int j) {
    return (is_open(i, j) && union.connected(virt_top, idx(i, j)));
  }

  // does the system percolate?
  public boolean percolates() {
    return union.connected(virt_top, virt_bottom);
  }

  public void print() {
    for (int j = 0; j < cells[0].length; j++) {
      // for each row  (i) in each column (j)
      for (int i = 0; i < cells.length; i++) {
        if (is_full(i,j)) {
          System.out.print("f");
        }
        else if (is_open(i,j)) {
          System.out.print(" ");
        }
        else {
          System.out.print("X");
        }
      }
      System.out.print("\n");
    }

    if (percolates()) {
      System.out.println("The matrix percolates!");
    }
  }

  private boolean valid_coord(int i, int j) {
    return (i >= 0 && i < cells.length && j >= 0 && j < cells[i].length);
  }

  private int idx(int i, int j) {
    if (!valid_coord(i, j)) {
      throw new RuntimeException(String.format("cell %d, %d out of bounds", i, j));
    }
    int idx = i + j * cells[i].length;
    return idx;
  }

  public static void main(String[] args) {
    int n = 4;
    Percolation perc = new Percolation(n);
    int[][] cells = {
      {0,0},
      {0,2},
      {0,3},
      {0,1},
    };

    perc.print();

    for (int[] cell : cells) {
      int i = cell[0];
      int j = cell[1];

      perc.open(i,j);
      System.out.println(String.format("%d, %d: opened", i, j));
      System.out.println(String.format("%d, %d: %s", i, j, (perc.is_full(i, j) ? "full" : "empty")));

      perc.print();
    }

  }
}
