package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * A {@code EnglishSolitaireModel} represents the model for the game, and contains all the
 * necessary methods to represent the game. Creates a game with a plus shaped board, and contains
 * an empty slot to begin with.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * Produces the default board.
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the armThickness of 3 and given values for the empty slot.
   *
   * @param sRow the row of the empty square.
   * @param sCol the column of the empty square.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    if ((((0 <= sCol) && (sCol < 2)) || ((4 < sCol) && (sCol < 8)))
            && (((0 <= sRow) && (sRow < 2)) || ((4 < sRow) && (sRow < 8)))) {
      throw new IllegalArgumentException(
              "Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }
    this.armThickness = 3;
    this.sRow = sRow;
    this.sCol = sCol;

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the armThickness entered and the empty slot in the center.
   *
   * @param armThickness the amount of marbles thick the arm is.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness < 1 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    this.armThickness = armThickness;
    this.sRow = armThickness + ((armThickness - 3) / 2);
    this.sCol = armThickness + ((armThickness - 3) / 2);

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the given arm thickness and empty slot.
   *
   * @param armThickness arm thickness of the board.
   * @param sRow         empty slot row position.
   * @param sCol         empty slot column position.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (invalidSection(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid Board");
    }
    if (armThickness < 1 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid Board");
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
        if ((((0 <= i) && (i < arm - 1)) ||
                ((((2 * arm) - 2) < i) && (i < this.getBoardSize()))) &&
                (((0 <= j) && (j < arm - 1)) ||
                        ((((2 * arm) - 2) < j) && (j < this.getBoardSize())))) {
          state[i][j] = SlotState.Invalid;
        } else if (i == x && j == y) {
          state[i][j] = SlotState.Empty;
        } else {
          state[i][j] = SlotState.Marble;
        }
      }
    }
    return state;
  }

  protected boolean invalidSection(int sRow, int sCol, int armThickness) {
    return (((0 <= sCol && sCol < armThickness - 1) ||
            ((2 * armThickness) - 2 < sCol &&
                    sCol < (armThickness * 2) + (armThickness - 2))) &&
            ((0 <= sRow && sRow < armThickness - 1) ||
                    (((2 * armThickness) - 2 < sRow) &&
                            (sRow < (armThickness * 2) + (armThickness - 2)))));
  }
}
