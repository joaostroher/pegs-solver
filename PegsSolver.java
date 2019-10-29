import java.util.ArrayList;

public class PegsSolver {
  public static void main(String[] args) {
    PegsState ps = new PegsState();
    System.out.println(solve(ps));
  }


  public static boolean solve(final PegsState state)
  {
    ArrayList<PegMove> pm = state.getPossiblePegsMoves();
    for (int i = 0; i < pm.size(); i++) {
      PegsState p = new PegsState(state);
      p.move(pm.get(i));
      if (p.isSolved()) {
        while (p!=null) {
          System.out.println(p);
          p = p.getStateAnt();
        }
        return true;
      }
      if (solve(p))
        return true;
    }
    return false;
  }
}