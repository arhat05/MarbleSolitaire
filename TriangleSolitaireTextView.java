package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a text view of the triangle solitaire game. Contains its own implementation of
 * toString(), as it overrides the inherited toString() method.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @throws IllegalArgumentException if the model given is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * second constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @param o     an appendable object.
   * @throws IllegalArgumentException in either is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable o)
          throws IllegalArgumentException {
    super(model, o);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int space = model.getBoardSize(); space > i + 1; space--) {
        s.append(" ");
      }
      for (int j = 0; j < model.getBoardSize(); j++) {
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (j == model.getBoardSize() - 1) {
            s.append("_");
          } else if (this.space(i, j)) {
            s.append("_");
          } else {
            s.append("_ ");
          }
        }
        if (model.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (j == model.getBoardSize() - 1) {
            s.append("O");
          } else if (this.space(i, j)) {
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

  @Override
  protected boolean space(int r, int c) {
    return (model.getSlotAt(r, c + 1).equals(MarbleSolitaireModelState.SlotState.Invalid));
  }

}
