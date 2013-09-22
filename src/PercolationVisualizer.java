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

public class PercolationVisualizer {

  private Draw draw;

  public PercolationVisualizer() {
    draw = new Draw();
    // enable difffered drawing.
    draw.show(0);
  }

  public void draw(Percolation perc) throws InterruptedException {
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
    draw.show(0);

    // wait for any key press
    do {
      // clear other presses
      while (draw.hasNextKeyTyped()) { draw.nextKeyTyped(); }
      Thread.sleep(100);
    } while (!draw.hasNextKeyTyped());
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

  public static void main(String[] args) throws InterruptedException {
    int n = 100;
    PercolationVisualizer viz = new PercolationVisualizer();
    Percolation perc = new Percolation(n);

    viz.draw(perc);
    for (int i = 0; i < perc.size(); i++) {
      for (int j = 0; j < 10; j++) {
        perc.open(i,j);
      }
    }
    viz.draw(perc);

    System.exit(0);

  }
}
