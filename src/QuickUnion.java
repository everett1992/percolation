// Caleb Everett   22/09/13
// TCNJ          Algorithms
//
// interface around the books QuickUnionUF and QuickUnionPathCompressionUF
// classes so they can be used polymorphiclally
public interface QuickUnion {
  public int count();
  public int find(int p);
  public boolean connected(int p, int q);
  public void union(int p, int q);
}
