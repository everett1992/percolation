public class Percolation {

  private QuickUnionUF matrix;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    matrix = new QuickUnionUF(N);
  }

  // open site (row i, col j) if it is not already
  public void open(int i, int j) {
  }

  // is site (row i, col j) open?
  public boolean isOpen(int i, int j) {
    return false;
  }

  // is site (row i, col j) full?
  public boolean isFull(int i, int j) {
    return false;
  }

  // does the system percolate?
  public boolean percolates() {
    return false;
  }

  public static void main(String[] args) {
    System.out.println("hello");
  }
}
