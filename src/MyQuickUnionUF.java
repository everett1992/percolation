// Caleb Everett   22/09/13
// TCNJ          Algorithms
//
// wapper around the books QuickUnionUF
// class so it can be used polymorphiclally
public class MyQuickUnionUF implements QuickUnion {

  // The Books QuickUnionUF
  private QuickUnionUF quuf;

  public MyQuickUnionUF(int N) {
    quuf = new QuickUnionUF(N);
  }
  public int count() {
    return quuf.count();
  }
  public int find(int p) {
    return quuf.find(p);
  }

  public boolean connected(int p, int q) {
    return quuf.connected(p, q);
  }

  public void union(int p, int q) {
    quuf.union(p, q);
  }

}

