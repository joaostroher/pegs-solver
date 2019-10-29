public class PegMove {
  public static int UP = 0;
  public static int RIGHT = 1;
  public static int DOWN = 2;
  public static int LEFT = 3;

  private int row;
  private int column;
  private int direction;


  public PegMove(int row, int column, int direction) {
    this.row = row;
    this.column = column;
    this.direction = direction;
  }

  public boolean isRight() {
    return this.direction == RIGHT;
  }

  public boolean isLeft() {
    return this.direction == LEFT;
  }

  public boolean isDown() {
    return this.direction == DOWN;
  }

  public boolean isUp() {
    return this.direction == UP;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  @Override
  public String toString() {
    return String.valueOf(row) + " " + String.valueOf(column) + " " + String.valueOf(direction);
  }
}