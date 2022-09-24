package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Implementation for the controller interface.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;

  /**
   * constructor for the controller implementation.
   *
   * @param model    the MarbleSolitaireModel to be passed through
   * @param view     the View state to be passed
   * @param readable the readable object
   * @throws IllegalArgumentException if any are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable readable) throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Invalid inputs");
    }

    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  /**
   * This method plays a new game of Marble Solitaire. throws an IllegalStateException
   * only if the controller is unable to successfully read input or transmit output.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(readable);

    try {
      while (!this.model.isGameOver()) { //runs until game over
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");

        int validInputs = 0;
        int[] moveVals = new int[4];
        while (validInputs < 4) { // input while loop
          if (this.model.isGameOver()) {
            try {
              this.view.renderMessage("Game over! \n");
              this.view.renderBoard();
              this.view.renderMessage("\nScore: " + this.model.getScore());
              break;
            } catch (IOException e3) {
              throw new IllegalStateException();
            }
          }
          if (scan.hasNext()) {
            String move = "";
            move = scan.next();
            if (!(move.contains("Q") || move.contains("q"))) {
              try {
                int positiveCheck = Integer.parseInt(move);
                if (positiveCheck > 0) {
                  moveVals[validInputs] = positiveCheck;
                  validInputs++;
                } else {
                  this.view.renderMessage("positives only, re-enter value\n");
                }

              } catch (NumberFormatException e) {
                this.view.renderMessage("Invalid move. Play again. Numbers only!\n");
              }
            } else if (move.contains("Q") || move.contains("q")) {
              try {
                this.view.renderMessage("Game quit!\n" + "State of game when quit:\n");
                this.view.renderBoard();
                this.view.renderMessage("\nScore: " + this.model.getScore());
                return;
              } catch (IOException e) {
                throw new IllegalStateException();
              }
            }
          } else {
            throw new IllegalStateException();
            //this.view.renderMessage("Invalid move. Play again.\n");
          }
        }

        try {
          this.model.move(moveVals[0] - 1, moveVals[1] - 1,
                  moveVals[2] - 1, moveVals[3] - 1);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("invalid move, try again\n");
        }
      }
      // if (this.model.isGameOver()) {
      try {
        this.view.renderMessage("Game over!\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + this.model.getScore());
      } catch (IOException e3) {
        throw new IllegalStateException();
      }
      //}

    } catch (IOException e) {
      throw new IllegalStateException();
    }

  }

}

