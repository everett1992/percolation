// Caleb Everett   22/09/13
// TCNJ          Algorithms
//
// Percolation class checks if an NxN grid of open or closed cells
// has a path of adjacent open cells from the top row to the bottom row.

public class Percolation {

  // Maximum size of gird.
  // Union datastructure cannot handle more than MAX INT
  // cells so the grid must be less than sqrt MAX INT on each size
  private static int MAX_SIZE = (int)Math.sqrt(Integer.MAX_VALUE);

  // Error message when grid is too large
  private static String GRID_OVERSIZE_MESSAGE =
      "Grid too large, N must be less than " + MAX_SIZE;


  // Tracks which cells are connected
  private QuickUnion union;

  // open cells are true, closed cells are false
  private boolean[][] cells;

  private int virt_top;
  private int virt_bottom;

  private final int size;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N, Boolean... varargs) {

    // N will be doubled, so if n > sqrt(MAX_INT)
    // there would be an overflow.
    if (N > MAX_SIZE) {
      throw new RuntimeException(GRID_OVERSIZE_MESSAGE);
    }

    // I wish this were ruby
    boolean weighted;
    if (varargs.length == 1) {
      weighted = varargs[0];
    } else if (varargs.length == 0) {
      weighted = true;
    } else {
      throw new IllegalArgumentException("too many arguments");
    }

    cells = new boolean[N][N];
    size = N;

    // Create a union data structure with N^2 + 2 seperate groups
    // Cell second to last cell is the virtual top, and last cell is the virtual bottom
    int num_cells = (int)Math.pow(N, 2);
    if (weighted) {
      union = new MyWeightedQuickUnionUF(num_cells);
    } else {
      union = new MyQuickUnionUF(num_cells);
    }

    // Join the top row of cells to the virtual top
    virt_top = 0; // virt top can be any cell from the top row of cells
    for (int i = 0; i < N; i++) {
      union.union(idx(i, 0), virt_top);
    }

    // Join the bottom row of cells to the virtual bottom
    virt_bottom = num_cells - 1; // virt bottom can be any cell from the bottom row of cells
    for (int i = 0; i < N; i++) {
      union.union(idx(i, size - 1), virt_bottom);
    }
  }

  // returns the size of the gird
  public int size() {
    return size;
  }

  // open site (row i, col j) if it is not already
  public void open(int i, int j) {
    if (!valid_coord(i, j)) {
      throw new RuntimeException(String.format("cell %d, %d out of bounds", i, j));
    } else {
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
    // If the grid is 1 x 1 virt top and bottom are the same cell,
    // so they are in the same group so make sure the cell 0,0 is open.
    if (size == 1) { return is_open(0, 0); }
    return union.connected(virt_top, virt_bottom);
  }

  // return false if the coordinates are outside of the grid
  private boolean valid_coord(int i, int j) {
    return (i >= 0 && i < size && j >= 0 && j < size);
  }

  // map a 2d coordinate to a 1d index to pass to the union object
  private int idx(int i, int j) {
    if (!valid_coord(i, j)) {
      throw new RuntimeException(String.format("cell %d, %d out of bounds", i, j));
    }
    int idx = i + j * size;
    return idx;
  }
}
