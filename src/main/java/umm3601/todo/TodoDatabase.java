package umm3601.todo;

import com.google.gson.JsonObject;

import javax.xml.ws.Response;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of todo info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `TodoController` to "query" the "database".
 */
public class TodoDatabase
{
  private Todo[] allTodos;

  public TodoDatabase(String userTodoFile) throws IOException
  {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userTodoFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single todo specified by the given ID. Return
   * `null` if there is no todo with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo
   * with that ID
   */
  public Todo getTodo(String id)
  {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id).findFirst().orElse(null));
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams)
  {
    Todo[] filteredTodos = allTodos;

    // Filter age if defined
    if(queryParams.containsKey("owner"))
    {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filteredTodos(filteredTodos, x -> x.owner.equalIgnoreCase(targetOwner));
    }
    if (queryParams.containsKey("category"))
    {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filteredTodos(filteredTodos, x -> x.category.equalsIgnoreCase(targetCategory));
    }
    if (queryParams.containsKey("status"))
    {
      String targetStatus = queryParams.get("status")[0];
      filteredTodos = filteredTodos(filteredTodos, x -> x.category.equalsIgnoreCase(targetStatus));
    }
    if (queryParams.containsKey("contains"))
    {
      String targetBody = queryParams.get("contains")[0];
      filteredTodos = filteredTodos(filteredTodos, x -> x.category.equalsIgnoreCase(targetBody);
    }
    return filteredTodos;
  }
}
