package umm3601.todo;


import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import java.util.function.Predicate;
import java.util.Comparator;

/**
 * A fake "database" of todo info
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

  // large method that gets called any time getTodos is called from the controller.
  // This handles various query parameters, and calls their respective helper functions.
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwners(filteredTodos, targetOwner);
    }
    if(queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];

      if (targetStatus.equals("complete")){
        boolean isComplete = true;
        filteredTodos = filterTodosByStatus(filteredTodos, isComplete);
      }

      if (targetStatus.equals("incomplete")){
        boolean isComplete = false;
        filteredTodos = filterTodosByStatus(filteredTodos, isComplete);
      }
    }
    if(queryParams.containsKey("contains")) {
      String givenString = queryParams.get("contains")[0];
      filteredTodos = filterTodosByBody(filteredTodos, givenString);
    }
    if(queryParams.containsKey("category")){
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }
    if(queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = filterTodosByLimit(filteredTodos, targetLimit);
    }
    if (queryParams.containsKey("orderBy")) {
      switch (queryParams.get("orderBy")[0]) {
        case "owner":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.owner));
          break;
        case "category":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.category));
          break;
        case "body":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.body));
          break;
        case "status":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.status));
          break;
      }
    }
    return filteredTodos;
  }

  /**


  /**
   * Get an array of todos sorted by the given comparator
   *
   * @param todos the list of todos to sort
   * @param c     the comparator to sort by
   * @return an array of all the todos from the given list
   * sorted by the given comparator
   */
  public Todo[] sortTodos(Todo[] todos, Comparator<? super Todo> c) {
    return Arrays.stream(todos).sorted(c).toArray(Todo[]::new);
  }

  /*
   * Get an array of all the todos having the target owner.
   *
   * @param todos the list of todos to filter by owner
   * @param targetOwner the target owner to look for
   * @return an array of all the todos from the given list that have
   * the target owner
   */
  public Todo[] filterTodosByOwners(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  // This helper function simply takes a given int from the query param and creates and
  // returns a smaller todo array
  public Todo[] filterTodosByLimit(Todo[] todos, int targetLimit) {
    Todo[] filteredTodos = new Todo[targetLimit];
    for (int i=0;i<targetLimit;i++){
      filteredTodos[i] = todos[i];
    }
    return filteredTodos;
  }
  /**
   * Get an array of all the todos having the target owner.
   *
   * @param todos the list of todos to filter by status
   * @param todoStatus the target status to look for
   * @return an array of all the todos from the given list that have
   * the target status
   */
  public Todo[] filterTodosByStatus(Todo[] todos, boolean todoStatus){

    return Arrays.stream(todos).filter(x -> x.status == todoStatus).toArray(Todo[]::new);

  }

  /**
   * Get an array of all the todos having the target owner.
   *
   * @param todos the list of todos to filter by a given string within the body
   * @param contains the target string to look for
   * @return an array of all the todos from the given list that have
   * the given string
   */
  public Todo[] filterTodosByBody(Todo[] todos, String contains){
    return Arrays.stream(todos).filter(x -> x.body.contains(contains)).toArray(Todo[]::new);
  }
  /**
   * Get an array of all the todos having the target owner.
   *
   * @param todos the list of todos to filter by a given category
   * @param targetCategory the target category to look for
   * @return an array of all the todos from the given list that have
   * the given category
   */
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory){
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

}
