package edu.cnm.deepdive;

import edu.cnm.deepdive.service.GameRepository;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Implements a simple client for a Codebreaker (similar to MasterMInd, Bulls & Cows) game
 * that runs as a web service. This client uses console interaction (standard &amp; output) to
 * obtain guesses from the user and display the results of each guess.
 *
 * @auther Nicholas Bennett and DDC Java + Android Bootcamp cohort 13.
 */


public class Main {

  /**
   * Entry point for game. Connects to Codebreaker service to start each game, and query the user
   * for guesses and new game, until the user declines to play again.
   *
   * @param args Command-line arguments (currently ignored)
   * @throws IOException if a network or network resource (such as web service) fails in sending the
   *                     request and receiving the response.
   */
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    ResourceBundle bundle = ResourceBundle.getBundle("strings");
    GameRepository repository = new GameRepository();
    // TODO Create proxy class for service.

    do {
      // TODO Play a single game.
      repository.newGame("0123456789", 3);

    } while (queryReplay(scanner, bundle));
  }

  /**
   * Prompts the user to play again. If a resource bundle exists for the current (or set) local a
   * string from the resource bundle will be used for the prompt text.
   *
   * @param scanner Instance of {@link Scanner} used to obtain user input.
   * @param bundle {@link ResourceBundle} holding UI string context.
   *
   * @return {@code false} if the user declines to play again; {@code true} otherwise.
   */

  private static boolean queryReplay(Scanner scanner, ResourceBundle bundle) {
    System.out.println(bundle.getString("replay_prompt"));
    String input = scanner.nextLine().trim().toLowerCase();
    return (input.isEmpty() || input.charAt(0) != 'n');
  }
}
