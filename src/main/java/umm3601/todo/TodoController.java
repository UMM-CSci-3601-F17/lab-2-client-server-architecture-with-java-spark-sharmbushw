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
  public JsonObject getTodos(Request req, Response res) {
    res.type("application/json");
    Todo[] todos = database.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
  }

  public JsonObject maxTodos(Request req, Response res) {
      res.type("application/json");
      int maxtodo = Integer.parseInt(req.queryParams("limit"));

      Todo[] allTodos = database.listTodos(req.queryMap().toMap());
      Todo[] todos = new Todo[maxtodo];
      for(int i = 0; i < todos.length; i++){
        todos[i] =  allTodos[i];
      }
      return buildSuccessJsonResponse("todos",gson.toJsonTree(todos));
    }


  }

