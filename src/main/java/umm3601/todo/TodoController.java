package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import static umm3601.Util.*;
/**
 * Controller that manages requests for info about todos.
 */

public class TodoController
{
  private final Gson gson;
  private TodoDatabase database;

  /**
   * Construct a controller for todos.
   *
   * This loads the "database" of todo from a JSON file and
   * stores that internally so that (subsets of) todos can be returned
   * in response to requests.
   *
   * @param database the database containing todo data
   */

  public TodoController(TodoDatabase database)
  {
    gson = new GSON();
    this.database = database;
  }

}
