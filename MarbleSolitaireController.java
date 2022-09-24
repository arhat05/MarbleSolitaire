package cs3500.marblesolitaire.controller;

/**
 * Interface to represent the controller for the Marble Solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * This method plays a new game of Marble Solitaire. throws an IllegalStateException
   * only if the controller is unable to successfully read input or transmit output.
   */
  void playGame() throws IllegalStateException;
}
