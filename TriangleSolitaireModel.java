package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a triangle solitaire game. Creates a board with a triangle of marbles, and an empty
 * space in the middle, or other specified empty space.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * default constructor for the triangle solitaire model.
   */
  public TriangleSolitaireModel() {
    this.armThickness = 5;
    this.sRow = 0;
    this.sCol = 0;
    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Constructor for the triangle solitaire model, taking in an arm thickness.
   *
   * @param armThickness the arm thickness
   * @throws IllegalArgumentException if the arm thickness is less than 2.
   */
  public TriangleSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness < 2) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    this.armThickness = armThickness;
    this.sRow = 0;
    this.sCol = 0;
    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Constructor for the triangle solitaire model, taking in the empty row and column.
   *
   * @param sRow the empty row
   * @param sCol the empty column
   * @throws IllegalArgumentException if the empty row or column is invalid.
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    if ((sRow < 0 || sCol < 0) || (sRow > 4 || sCol > 4) ||
            sCol > sRow) {
      throw new IllegalArgumentException("Invalid space");
    }
    this.armThickness = 5;
    this.sRow = sRow;
    this.sCol = sCol;
    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Constructor taking in the arm thickness and the empty row and column.
   *
   * @param armThickness the arm thickness
   * @param sRow         the empty row
   * @param sCol         the empty column
   * @throws IllegalArgumentException if the arm thickness is less than 2, or if the empty row
   *                                  or column is invalid.
   */
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness < 2) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    if ((sRow < 0 || sCol < 0) || (sRow > armThickness - 1 || sCol > armThickness - 1) ||
            sCol > sRow) {
      throw new IllegalArgumentException("Invalid space");
    }

    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  @Override
  protected SlotState[][] makeState(int arm, int x, int y) {
    state = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i == x && j == y) {
          state[i][j] = SlotState.Empty;
        } else if (j <= i) {
          state[i][j] = SlotState.Marble;
        } else {
          state[i][j] = SlotState.Invalid;
        }
      }
    }
    return state;
  }

  /**
   * determines if the game is over.
   *
   * @return if the game is over
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          if (this.valid(i, j, i + 2, j) || this.valid(i, j, i, j + 2) ||
                  this.valid(i, j, i - 2, j) || this.valid(i, j, i, j - 2) ||
                  this.valid(i, j, i - 2, j - 2) || this.valid(i, j, i + 2, j + 2) ||
                  this.valid(i, j, i - 2, j + 2) || this.valid(i, j, i + 2, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if the move is invalid.
   *
   * @param fromRow the row of the slot to be moved from.
   * @param fromCol the column of the slot to be moved from.
   * @param toRow   the row of the slot to be moved to.
   * @param toCol   the column of the slot to be moved to.
   * @return true if the move is invalid, false otherwise.
   */
  @Override
  protected boolean invalid(int fromRow, int fromCol, int toRow, int toCol) {
    return ((this.invalidSection(fromRow, fromCol, this.armThickness)) ||
            (this.invalidSection(toRow, toCol, this.armThickness)) ||
            (!(this.state[fromRow][fromCol].equals(SlotState.Marble))) ||
            (!(this.state[toRow][toCol].equals(SlotState.Empty))) ||
            (!(this.state[(fromRow + toRow) / 2]
                    [(fromCol + toCol) / 2].equals(SlotState.Marble))) ||
            ((Math.abs(fromRow - toRow) != 2) && (fromCol == toCol)) ||
            ((Math.abs(fromCol - toCol) != 2) && (fromRow == toRow)) ||
            ((fromRow + toRow) % 2 != 0) || ((fromCol + toCol) % 2 != 0) ||
            ((Math.abs(fromRow - toRow) != 2) && (Math.abs(fromCol - toCol) != 2)) ||
            ((Math.abs(fromRow - toRow) > 2)) || ((Math.abs(fromCol - toCol) > 2)) ||
            (fromRow == toRow && fromCol == toCol));
  }

  /**
   * Checks if the move is valid.
   *
   * @param fromRow the row of the slot to be moved from.
   * @param fromCol the column of the slot to be moved from.
   * @param toRow   the row of the slot to be moved to.
   * @param toCol   the column of the slot to be moved to.
   * @return true if the move is valid, false otherwise.
   */
  @Override
  protected boolean valid(int fromRow, int fromCol, int toRow, int toCol) {
    return !(invalid(fromRow, fromCol, toRow, toCol));
  }

  /**
   * determines if the section of the board is invalid.
   *
   * @param toRow        the row of the section to be checked.
   * @param toCol        the column of the section to be checked.
   * @param armThickness the thickness of the arm.
   * @return true if the section is invalid, false otherwise.
   */
  @Override
  protected boolean invalidSection(int toRow, int toCol, int armThickness) {
    return ((toRow < 0 || toCol < 0) || (toRow > this.armThickness - 1 ||
            toCol > this.armThickness - 1) ||
            toCol > toRow);
  }

  /**
   * Returns the size of the board.
   *
   * @return the size of the board
   */
  @Override
  public int getBoardSize() {
    return this.armThickness;
  }
}
