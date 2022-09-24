package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Main class for Marble Solitaire. Runs the game with command line arguments. Game board is
 * specified by the first command line argument.
 */
public final class MarbleSolitaire {
  /**
   * main method for Marble Solitaire, runs the game with command line arguments.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    switch (args[0].toLowerCase()) {
      case "english":
        MarbleSolitaireModel model1 = new EnglishSolitaireModel();
        MarbleSolitaireView mView1 = new MarbleSolitaireTextView(model1);
        MarbleSolitaireController m1 = new MarbleSolitaireControllerImpl(model1, mView1,
                new InputStreamReader(System.in));
        m1.playGame();
        break;
      case "european":
        MarbleSolitaireModel model2 = new EuropeanSolitaireModel();
        MarbleSolitaireView mView2 = new MarbleSolitaireTextView(model2);
        MarbleSolitaireController m2 = new MarbleSolitaireControllerImpl(model2, mView2,
                new InputStreamReader(System.in));
        m2.playGame();
        break;
      case "triangular":
        MarbleSolitaireModel model3 = new TriangleSolitaireModel();
        MarbleSolitaireView mView3 = new TriangleSolitaireTextView(model3);
        MarbleSolitaireController m3 = new MarbleSolitaireControllerImpl(model3, mView3,
                new InputStreamReader(System.in));
        m3.playGame();
        break;
      default:
        System.out.println("invalid choice");
        break;
    }
  }
}
