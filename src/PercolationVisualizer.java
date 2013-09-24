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

  private final Draw draw;

  private final Percolation perc;

  // Record the last color painted to a cell.
  private final Color[][] matrix;

  public PercolationVisualizer(Percolation perc) {
    this.perc = perc;

    matrix = new Color[perc.size()][perc.size()];

    draw = new Draw();

    // enable difffered drawing.
    draw.show(0);
  }

  // Draw all cells.
  public void draw() { draw(0); }
  public void draw(int wait) {
    final int n = perc.size();

    draw.setXscale(0, (double) n + 2);
    draw.setYscale(0, (double) n + 2);

    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        final Color last_color = matrix[i][j];
        final Color color;
        if (perc.is_full(i,j)) {
          color = Draw.BOOK_BLUE;
        }
        else if (perc.is_open(i,j)) {
          color = Draw.WHITE;
        }
        else {
          color = Draw.BLACK;
        }


        if (color != last_color) {
          draw_cell(i, j, color);
        }
        matrix[i][j] = color;
      }
    }
    draw.show(wait);
  }

  // draw a cell
  private void draw_cell(int i, int j, Color color) {
    // canvas places 0,0 in bottom left, we want it in top left.
    final double j_draw = ((double) perc.size() - j) + 1.5;
    final double i_draw = ((double) i) + 1.5;

    draw.setPenColor(color);
    draw.filledSquare(i_draw, j_draw, 0.5);
    draw.setPenColor(Draw.BLACK);
    draw.square(i_draw, j_draw, 0.5);
  }

  public static void main(String[] args) {

    int n = StdIn.readInt();

    Percolation perc = new Percolation(n);
    PercolationVisualizer viz = new PercolationVisualizer(perc);

    viz.draw();

    double draw_time = 0;
    double open_time = 0;

    try {
      while (!StdIn.isEmpty()) {
        // read integers from standard in
        int i = StdIn.readInt();
        int j = StdIn.readInt();

        Stopwatch open_sw = new Stopwatch();
        // open the cell
        perc.open(i,j);
        open_time += open_sw.elapsedTime();


        Stopwatch draw_sw = new Stopwatch();
        // draw the percolation matrix
        viz.draw();
        draw_time += draw_sw.elapsedTime();
      }
    } catch (NoSuchElementException ex) {
      System.err.println("Unexpectantly Reached end of input while proccessing a cell");
    }

    System.out.printf("open: %f%n", open_time);
    System.out.printf("draw: %f%n", draw_time);

    System.exit(0);

  }
}
