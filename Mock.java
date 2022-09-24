package cs3500.marblesolitaire.model.hw02;

/**
 * A mock for the Marble Solitaire Model.
 */
public class Mock implements MarbleSolitaireModel {
  private StringBuilder l;

  public Mock(StringBuilder l) {
    this.l = l;
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    l.append("move()" + fromRow + " " + fromCol + " " + toRow + " " + toCol);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
