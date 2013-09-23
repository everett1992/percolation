// Caleb Everett   22/09/13
// TCNJ          Algorithms
//
// Percolation Visualizer class draws state of a Percolation object
//
// - Closed cells are drawn on grey
// - Cells connected to the top row are 'full' and drawn in blue
// - Cells that are open but not connected to the top are 'empty and 
//   drawn in white

import java.awt.Color;
import java.util.NoSuchElementException;

public class PercolationVisualizer {

  private Draw draw;

  public PercolationVisualizer() {
    draw = new Draw();
    // enable difffered drawing.
    draw.show(0);
  }

  public void draw(Percolation perc) {
    draw(perc, 0);
  }
  public void draw(Percolation perc, int wait) {
    int n = perc.size();

    draw.clear();
    draw.setXscale(0, (double) n + 2);
    draw.setYscale(0, (double) n + 2);


    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        if (perc.is_full(i,j)) {
          draw_full(i, j);
        }
        else if (perc.is_open(i,j)) {
          draw_open(i, j);
        }
        else {
          draw_closed(i, j);
        }
      }
    }
    draw.show(wait);

    //wait_for_keypress();
  }

  private void draw_cell(int i, int j, Color color) {
    draw.setPenColor(color);
    draw.filledSquare(((double) i) + 1.5, ((double) j) + 1.5, 0.5);
    draw.setPenColor(Draw.BLACK);
    draw.square(((double) i) + 1.5, ((double) j) + 1.5, 0.5);
  }

  private void draw_full(int i, int j) {
    draw_cell(i, j, Draw.BOOK_BLUE);
  }

  private void draw_open(int i, int j) {
    draw_cell(i, j, Draw.WHITE);
  }

  private void draw_closed(int i, int j) {
    draw_cell(i, j, Draw.BLACK);
  }

  public static void main(String[] args) {

    int n = StdIn.readInt();

    PercolationVisualizer viz = new PercolationVisualizer();
    Percolation perc = new Percolation(n);

    viz.draw(perc);

    try {
      while (!StdIn.isEmpty()) {
        // read integers from standard in
        int i = StdIn.readInt();
        int j = StdIn.readInt();

        // open the cell
        perc.open(i,j);


        // draw the percolation matrix
        viz.draw(perc, 50);
      }
    } catch (NoSuchElementException ex) {
      System.err.println("Unexpectantly Reached end of input while proccessing a cell");
    }

    System.exit(0);

  }
}
