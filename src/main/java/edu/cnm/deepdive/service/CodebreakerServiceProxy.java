package edu.cnm.deepdive.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.model.Game;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Proxy interface (implemented by Retrofit)..&hellip;
 */
public interface CodebreakerServiceProxy {


  /**
   * Constructs and returns a {@link Call} instance that may be used to send a request to the web
   * service to generate a new secret code (start a new game).
   *
   * @param game "Stub" of {@link Game} specifying the characters that can be used in the code
   *             generated by the web service, and the length of the code to be generated.
   * @return{@link Call} instance that may be used to send the HTTP request and receive the
   * response.
   */
  @POST("codes")
  Call<Game> startGame(@Body Game game);

  @GET("codes/{id}")
  Call<Game> getGame(@Path("id") String id);


  static CodebreakerServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Implements the "lazy" <a * href="https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">initialization-on-demand
   * * holder idiom</a>. This created an instance of {@link CodebreakerServiceProxy} only when the
   * {@code InstanceHolder} class is initialized; since this initialization happens only once, and
   * sincce only 1 thread is allowed to load a class into memory, this guarantees that only one
   * instance of {@link CodebreakerServiceProxy} will be created&mdash; that is, {@code
   * CodebreakerServiceProxy} is a "singleton .
   */

  class InstanceHolder {

    private static final CodebreakerServiceProxy INSTANCE;

    static {
      //Creation of instances of Gson, OkhttpClient, Retrofit all employ the "builder pattern", as
      //supported by those libraries.

      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();

      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://ddc-java.services/codebreaker/")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .client(client)
          .build();
      INSTANCE = retrofit.create(CodebreakerServiceProxy.class);

    }
  }


}
