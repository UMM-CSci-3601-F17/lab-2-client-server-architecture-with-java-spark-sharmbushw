package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class FilterTodosByOwnerFromDB {

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] blancheTodos = db.filterTodosByOwners(allTodos, "Blanche");
    assertEquals("Incorrect number of users with age 27", 43, blancheTodos.length);

    Todo[] fryTodos = db.filterTodosByOwners(allTodos, "Fry");
    assertEquals("Incorrect number of users with age 33", 61, fryTodos.length);
  }

}
