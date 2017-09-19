package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import umm3601.todo.Todo;

import java.io.IOException;

import static umm3601.Util.*;

public class TodoController {
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


  public TodoController(TodoDatabase database) {
    gson = new Gson();
    this.database = database;
  }


  public JsonObject getTodo(Request req, Response res) {
    res.type("application/json");
    String id = req.params("id");
    Todo todo = database.getTodo(id);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "Todo with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

    /**
   * Get a JSON response with a list of all the todos in the "database".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the todos
   */

  public JsonObject getTodos(Request req, Response res) {
    res.type("application/json");
    Todo[] todos = database.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
  }




  }


