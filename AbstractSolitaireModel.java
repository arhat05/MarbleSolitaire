package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * The abstract class for the Marble Solitaire game. This class is used to create a model for the
 * different boards that can be used in the game.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected int armThickness;
  protected int sRow;
  protected int sCol;
  protected SlotState[][] state;


  /**
   * Return the state of the board, given a row and column of the empty slot,
   * and the arm thickness.
   *
   * @param arm arm thickness.
   * @param x   row of the empty slot.
   * @param y   column of the empty slot.
   * @return the state of the board.
   */
  protected abstract SlotState[][] makeState(int arm, int x, int y);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0).
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0).
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0).
   * @throws IllegalArgumentException if the move is not possible.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (this.invalid(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move attempt");
    }

    this.state[fromRow][fromCol] = SlotState.Empty;
    this.state[toRow][toCol] = SlotState.Marble;
    this.state[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
  }

  /**
   * returns if a combination of row and column is valid or not.
   *
   * @param fromRow the row of the slot to be moved from.
   * @param fromCol the column of the slot to be moved from.
   * @param toRow   the row of the slot to be moved to.
   * @param toCol   the column of the slot to be moved to.
   * @return true if the move is invalid, false otherwise.
   */
  protected boolean invalid(int fromRow, int fromCol, int toRow, int toCol) {
    return (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0 ||
            fromRow >= this.getBoardSize() || fromCol >= this.getBoardSize() ||
            toCol >= this.getBoardSize() || toRow >= this.getBoardSize()) ||
            (this.invalidSection(fromRow, fromCol, this.armThickness)) ||
            (this.invalidSection(toRow, toCol, this.armThickness)) ||
            (!(this.state[fromRow][fromCol].equals(SlotState.Marble))) ||
            (!(this.state[toRow][toCol].equals(SlotState.Empty))) ||
            (!(this.state[(fromRow + toRow) / 2]
                    [(fromCol + toCol) / 2].equals(SlotState.Marble))) ||
            ((Math.abs(fromRow - toRow) != 2) && (fromCol == toCol)) ||
            ((Math.abs(fromCol - toCol) != 2) && (fromRow == toRow)) ||
            !((fromRow == toRow) ^ (fromCol == toCol));
  }

  /**
   * returns if a section of the board is valid or not.
   *
   * @param fromRow      the row of the section to be checked.
   * @param fromCol      the column of the section to be checked.
   * @param armThickness the thickness of the arm.
   * @return true if the section is invalid, false otherwise.
   */
  protected abstract boolean invalidSection(int fromRow, int fromCol, int armThickness);


  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          if (this.valid(i, j, i + 2, j) || this.valid(i, j, i, j + 2) ||
                  this.valid(i, j, i - 2, j) || this.valid(i, j, i, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Determine and return if the move is valid or not.
   *
   * @param fromRow the row of the slot to be moved from.
   * @param fromCol the column of the slot to be moved from.
   * @param toRow   the row of the slot to be moved to.
   * @param toCol   the column of the slot to be moved to.
   * @return
   */
  protected boolean valid(int fromRow, int fromCol, int toRow, int toCol) {
    return (fromCol >= 0 && fromRow >= 0 && toRow >= 0 &&
            toCol >= 0 && fromRow < this.getBoardSize()
            && fromCol < this.getBoardSize() && toRow < this.getBoardSize() &&
            toCol < this.getBoardSize()) &&
            ((!this.getSlotAt(fromRow, fromCol).equals(SlotState.Invalid)) &&
                    (!this.getSlotAt(toRow, toCol).equals(SlotState.Invalid))) &&
            (!this.getSlotAt(fromRow, fromCol).equals(SlotState.Empty)) &&
            (!this.getSlotAt(toRow, toCol).equals(SlotState.Marble)) &&
            ((Math.abs(fromRow - toRow) == 2) || (Math.abs(fromCol - toCol) == 2)) &&
            (!this.getSlotAt((fromRow + toRow) / 2,
                    (fromCol + toCol) / 2).equals(SlotState.Empty)) &&
            ((fromRow == toRow) ^ (fromCol == toCol));
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer.
   */
  @Override
  public int getBoardSize() {
    return (this.armThickness * 3) - 2;
  }


  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   * @return the state of the slot at the given row and column.
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (0 > row || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("invalid row and column");
    }
    return this.state[row][col];
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }

}
