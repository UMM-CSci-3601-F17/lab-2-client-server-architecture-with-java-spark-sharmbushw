package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *redirect.get("/fromPath", "/toPath");

// redirect a POST to "/fromPath" to "/toPath", with status 303
redirect.post("/fromPath", "/toPath", Redirect.Status.SEE_OTHER);

// redirect any request to "/fromPath" to "/toPath" with status 301
redirect.any("/fromPath", "/toPath", Redirect.Status.MOVED_PERMANENTLY);
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */

  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwners(filteredTodos, targetOwner);
    }
    if(queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = filterTodosByLimit(filteredTodos, targetLimit);
    }
    if(queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];
      boolean isComplete = false;
      if (targetStatus.equals("complete")){
        isComplete = true;
      }
      filteredTodos = filterTodosByStatus(filteredTodos, isComplete);
    }
    return filteredTodos;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param todos the list of todos to filter by age
   * @param targetOwner the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public Todo[] filterTodosByOwners(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByLimit(Todo[] todos, int targetLimit) {
    Todo[] filteredTodos = new Todo[targetLimit];
    for (int i=0;i<targetLimit;i++){
      filteredTodos[i] = todos[i];
    }
    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean todoStatus){

    return Arrays.stream(todos).filter(x -> x.status == todoStatus).toArray(Todo[]::new);

  }

}
