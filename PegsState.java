import java.util.ArrayList;

public class PegsState {
  private static int STATE_LENGTH = 7;
  public static int NO_PEG = 0;
  public static int WITH_PEG = 1;
  public static int WITHOUT_PEG = 2;

  private int[][] state =
  {{0, 0, 2, 2, 2, 0, 0},
   {0, 0, 2, 2, 2, 0, 0},
   {2, 2, 2, 2, 2, 2, 2},
   {2, 2, 1, 2, 2, 2, 2},
   {2, 2, 1, 1, 1, 2, 2},
   {0, 0, 1, 1, 1, 0, 0},
   {0, 0, 1, 1, 1, 0, 0}}; //Esse é para teste
  private int pegsCount = 0;
  private int moves = 0;
  private PegsState stateAnt = null;

  public PegsState() {
    initialize();
    recountPegs();
}

  public PegsState(PegsState ps) {
    this.state = new int[STATE_LENGTH][STATE_LENGTH];
    for(int i = 0; i < STATE_LENGTH; i++) {
      for(int j = 0; j < STATE_LENGTH; j++) {
        this.state[i][j] = ps.getState()[i][j];
      }
    }
    this.moves = ps.getMoves();
    this.pegsCount = ps.getPegsCount();
    this.stateAnt = ps;
  }

  private void initialize() {
    this.state = new int[7][7];
    for(int i = 0; i < STATE_LENGTH; i++) {
      for(int j = 0; j < STATE_LENGTH; j++) {
        if ((i == 3) && (j == 3))
          this.state[i][j] = WITHOUT_PEG;
        else if (((i < 2) || (i > 4)) && ((j < 2) || (j > 4)))
          this.state[i][j] = NO_PEG;
        else
          this.state[i][j] = WITH_PEG;
      }
    }
  }

  public boolean isSolved() {
    return (this.pegsCount == 1);
  }

  public int getPegsCount() {
    return this.pegsCount;
  }

  public int getMoves() {
    return this.moves;
  }

  public PegsState getStateAnt() {
    return this.stateAnt;
  }

  public int[][] getState() {
    return state;
  }

  public ArrayList<PegMove> getPossiblePegsMoves() {
    ArrayList<PegMove> moves = new ArrayList<>();
    for(int i = 0; i < STATE_LENGTH; i++) {
      for(int j = 0; j < STATE_LENGTH; j++) {
        if (this.state[i][j] == WITH_PEG) {
          if ((i > 0) && (i < (STATE_LENGTH-1))) {
            if ((this.state[i-1][j] == WITH_PEG) && (this.state[i+1][j] == WITHOUT_PEG))
              moves.add(new PegMove(i, j, PegMove.DOWN));
            else if ((this.state[i-1][j] == WITHOUT_PEG) && (this.state[i+1][j] == WITH_PEG))
              moves.add(new PegMove(i, j, PegMove.UP));
          }
          if ((j > 0) && (j < (STATE_LENGTH-1))) {
            if ((this.state[i][j-1] == WITH_PEG) && (this.state[i][j+1] == WITHOUT_PEG))
              moves.add(new PegMove(i, j, PegMove.RIGHT));
            else if ((this.state[i][j-1] == WITHOUT_PEG) && (this.state[i][j+1] == WITH_PEG))
              moves.add(new PegMove(i, j, PegMove.LEFT));
          }
        }
      }
    }
    return moves;
  }

  private void recountPegs() {
    pegsCount = 0;
    for(int i = 0; i < STATE_LENGTH; i++) {
      for(int j = 0; j < STATE_LENGTH; j++) {
        if (this.state[i][j] == WITH_PEG)
          pegsCount++;
      }
    }
  }

  public void move(PegMove pm) {
    if (pm.isDown()) {
      this.state[pm.getRow()-1][pm.getColumn()] = WITHOUT_PEG;
      this.state[pm.getRow()+1][pm.getColumn()] = WITH_PEG;
    }
    else if (pm.isUp()) {
      this.state[pm.getRow()-1][pm.getColumn()] = WITH_PEG;
      this.state[pm.getRow()+1][pm.getColumn()] = WITHOUT_PEG;
    }
    else if (pm.isLeft()) {
      this.state[pm.getRow()][pm.getColumn()-1] = WITH_PEG;
      this.state[pm.getRow()][pm.getColumn()+1] = WITHOUT_PEG;
    }
    else if (pm.isRight()) {
      this.state[pm.getRow()][pm.getColumn()-1] = WITHOUT_PEG;
      this.state[pm.getRow()][pm.getColumn()+1] = WITH_PEG;
    }
    this.state[pm.getRow()][pm.getColumn()] = WITHOUT_PEG;

    this.pegsCount--;
    this.moves++;
  }

  @Override
  public String toString() {
    String s = "";
    for(int i = 0; i < STATE_LENGTH; i++) {
      for(int j = 0; j < STATE_LENGTH; j++) {
        //s += String.valueOf(this.state[i][j])+" ";
        s += this.state[i][j] == WITH_PEG ? "\u25A3" : (this.state[i][j] == WITHOUT_PEG ? "\u25A1": " ");
        s += " ";
      }
      s += "\n";
    }
    return s;
  }
}