package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A {@code MarbleSolitaireTextView} for the MarbleSolitaireTextView object.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {


  /**
   * Constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @throws IllegalArgumentException if the model given is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * second constructor for a MarbleSolitaireTextView object.
   *
   * @param model a MarbleSolitaireModel object.
   * @param o     an appendable object.
   * @throws IllegalArgumentException in either is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable o) {
    super(model, o);

  }

}
