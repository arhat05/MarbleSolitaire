package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a model of a marble solitaire game. Creates the European Solitaire board, in the
 * shape of an octogon. Contains methods to move the marbles around the board, and to check if the
 * game is over, and check for valid moves. Initialized with a given size, and empty slot which
 * can be customized or defaulted to the center and size 3.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {


  /**
   * default constructor.
   */
  public EuropeanSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the armThickness entered and the empty slot in the center.
   *
   * @param armThickness the amount of marbles thick the arm is.
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness < 1 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    this.armThickness = armThickness;
    this.sRow = armThickness + ((armThickness - 3) / 2);
    this.sCol = armThickness + ((armThickness - 3) / 2);

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the armThickness of 3 and given values for the empty slot.
   *
   * @param sRow the row of the empty square.
   * @param sCol the column of the empty square.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {

    this.armThickness = 3;
    this.sRow = sRow;
    this.sCol = sCol;

    if (invalidSection(sRow, sCol, 3)) {
      throw new IllegalArgumentException("Invalid Empty Cell Position");
    }


    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * Produces a board with the given arm thickness and empty slot.
   *
   * @param armThickness arm thickness of the board.
   * @param sRow         empty slot row position.
   * @param sCol         empty slot column position.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {

    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;

    if (invalidSection(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid Empty");
    }
    if (armThickness < 1 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid Board");
    }

    this.state = this.makeState(this.armThickness, this.sRow, this.sCol);
  }

  /**
   * determines the invalid section of the board.
   * @param sRow      the row of the section to be checked.
   * @param sCol      the column of the section to be checked.
   * @param armThickness the thickness of the arm.
   * @return true if the section is invalid, false if it is valid.
   */
  protected boolean invalidSection(int sRow, int sCol, int armThickness) {
    return ((sCol - sRow) < -(this.getBoardSize() - armThickness)) ||
            ((sCol - sRow) > (this.getBoardSize() - armThickness)) ||
            ((sRow + sCol) < (armThickness - 1)) ||
            ((sRow + sCol) > (2 * (armThickness - 1) + this.getBoardSize()) - 1);
  }

  /**
   * makes a state with the given parameters, in the European Solitaire model (octagonal board).
   * @param arm thickness of the arm.
   * @param x row of the empty slot.
   * @param y column of the empty slot.
   * @return the state of the board.
   */
  @Override
  protected SlotState[][] makeState(int arm, int x, int y) {
    state = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (invalidSection(i, j, arm)) {
          state[i][j] = SlotState.Invalid;
        }

        else if (i == x && j == y) {
          state[i][j] = SlotState.Empty;
        }
        else {
          state[i][j] = SlotState.Marble;
        }
      }
    }

    return state;
  }
}
