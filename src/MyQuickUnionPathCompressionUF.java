// Caleb Everett   22/09/13
// TCNJ          Algorithms
//
// wapper around the books QuickUnionPathCompressionUF
// class so it can be used polymorphiclally
public class MyQuickUnionPathCompressionUF implements QuickUnion {

  // The Books QuickUnionPathCompressionUF
  private QuickUnionPathCompressionUF qupcuf;

  public MyQuickUnionPathCompressionUF(int N) {
    qupcuf = new QuickUnionPathCompressionUF(N);
  }
  public int count() {
    return qupcuf.count();
  }
  public int find(int p) {
    return qupcuf.find(p);
  }

  public boolean connected(int p, int q) {
    return qupcuf.connected(p, q);
  }

  public void union(int p, int q) {
    qupcuf.union(p, q);
  }

}


