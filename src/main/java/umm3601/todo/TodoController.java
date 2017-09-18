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

  /**
   * Get the single todo specified by the `id` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the todo with that ID is found, a fail
   * JSON object if no todo with that ID is found
   */
  public JsonObject getTodo(Request req, Response res)
  {
    res.type("application/json");
    String id = req.params("id");
    Todo todo = database.getTodo(id);
    if (todo != null)
    {
      return buildSuccessJsonResponse("user", gson.toJsonTree(todo));
    }
    else
    {
      String message = "User with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

}
