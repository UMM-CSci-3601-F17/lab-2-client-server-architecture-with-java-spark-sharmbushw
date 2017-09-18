package umm3601.todo;

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
}
