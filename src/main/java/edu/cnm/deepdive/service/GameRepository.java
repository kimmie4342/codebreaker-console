package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Error;
import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
import java.io.IOException;
import retrofit2.Response;


/**
 * Performs operation on an instance of the model classes {@link Game} and {@link
 * edu.cnm.deepdive.model.Guess}, including sending stub instances to the web service, and receiving
 * completed instances.
 */
public class GameRepository {

  private final CodebreakerServiceProxy proxy;

  /**
   * Initializes this instance by obtaining an instance of {@link CodebreakerServiceProxy} to enable
   * making requests of the web service.
   */


  public GameRepository() {
    proxy = CodebreakerServiceProxy.getInstance();
  }

  /**
   * Creates a stub(incomplete) instance of {@link Game}. setting the fields according to {@code
   * pool} and {@code length}, and sends this stub to the web service.
   *
   * @param pool
   * @param length
   * @return
   * @throws IOException
   */

  public Game newGame(String pool, int length) throws IOException, IllegalArgumentException {
    Game gameStub = new Game();
    gameStub.setPool(pool);
    gameStub.setLength(length);

    //Uses a Retrofit Call object to execute the HTTP request and obtain the response.

    Response<Game> response = proxy.startGame(gameStub).execute();
    if (!response.isSuccessful()) {
      throw new IllegalArgumentException();
    } //end if
    return response.body();
  }

  public Guess newGuess(Game game, String text)
      throws IOException, ValidationException {
    Guess guess = new Guess();
    guess.setText(text);
    Response<Guess> response = proxy.submitGuess(game.getId(), guess).execute();
    if (!response.isSuccessful()) {
      //noinspection ConstantConditions
      Error error = CodebreakerServiceProxy.getGsonInstance()
          .fromJson(response.errorBody().string(), Error.class);
      throw new ValidationException(error);
    }
    return response.body();
  }

  public static class ValidationException extends IllegalArgumentException {

    private final Error error;

    public ValidationException(Error error) {
      this.error = error;
    }

    public Error getError() {
      return error;
    }
  }

}
