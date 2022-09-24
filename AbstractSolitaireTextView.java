package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class for the text view. This class is used to create the text view, and is inherited
 * by the 2 text views classes.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected MarbleSolitaireModelState model;
  protected Appendable o;


  /**
   * Constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @throws IllegalArgumentException if the model given is null.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Invalid");
    }
    this.model = model;
    this.o = System.out;
  }

  /**
   * second constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @param o     an appendable object.
   * @throws IllegalArgumentException in either is null.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState model, Appendable o)
          throws IllegalArgumentException {
    if (model == null || o == null) {
      throw new IllegalArgumentException("Invalid");
    }
    this.model = model;
    this.o = o;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string.
   */

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          if (((j < this.getArmThickness() - 1) &&
                  ((i < this.getArmThickness() - 1))) ||
                  ((i > ((this.getArmThickness() * 2) - 2)) &&
                          ((j < this.getArmThickness() - 1)))) {
            s.append("  ");
          }
        }
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (this.space(i, j)) {
            s.append("_");
          } else {
            s.append("_ ");
          }
        }
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (this.space(i, j)) {
            s.append("O");
          } else {
            s.append("O ");
          }
        }
      }
      if (i < model.getBoardSize() - 1) {
        s.append("\n");
      }
    }
    return s.toString();
  }

  boolean space(int r, int c) {
    return (c == model.getBoardSize() - 1)
            || ((c != 0) && (model.getSlotAt(r, c + 1).equals(MarbleSolitaireModelState.
            SlotState.Invalid)) && (c < model.getBoardSize() - 1));
  }

  int getArmThickness() {
    return (model.getBoardSize() + 2) / 3;
  }

  @Override
  public void renderBoard() throws IOException {
    this.o.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.o.append(message);

  }
}
