## What is the purpose of .gitignore?
.gitignore is a file that you commit to your 
repository in order to specify which files and directories you do not 
wish to have added to Git upon any given commit.

## What is a route?
A route is a location which you specify in an api request, 
which gets linked to an action of some sort, or a response.

## What is the purpose of umm3601.Server class?
This is where the UserController class is initialized, 
the default Spark port is set, as well as where any requests/
responses are made for actions that you want to link to
specific routes. Redirects are also set so that the user would 
not have to write out an entire html file/ etc.

## What is the purpose of the umm3601.user.UserController class?
There are two methods within this class: getUser and getUsers, 
getUser checks for a specific user id within the database. If this id exists,
a success message is displayed, if not, a fail message is displayed.
getUsers returns the entire list of users within the database.

## Explain what happens when a user accesses each of the following URLs:
- :question: The page `users`
This directs the user to users.html page which contains html forms that utilize js to interact with the api endpoints for user data
- :question: The page `api/users`
This shows the raw json data
- :question: The page `api/users?age=25`
This shows the raw json data, except filtered by users with an age of 25
- :question: The page `api/users/588935f5de613130e931ffd5`
This shows raw json data, showing exclusively the user with the id specified.


## What are the contents of the public folder? What is the purpose of each of the HTML files there?
The public folder contains all of the code that users will interact directly with, i.e, the client.

The index.html is the page the user sees when they initially navigate to the site. It includes navigation to the
two other pages, as well as a button which triggers a simple javascript function that sends an alert to the screen.

The about.html is simply an "about" page that can be navigated to from index.html

The users.html page links to users.js, which actually interacts with the API. The html page links function calls
to buttons.

## Describe what happens when you filter users by age in the client? What is read from the web page, and what request is sent to the server? What is received, and how/where is it displayed?

The age value is read from the client, which is used to send a request to the server to display the json data of users with the specified age.
This is displayed above the form, right where the page says "Json will go here"

##  Where is the client-side JavaScript defined? Name the file(s) in which it is used.
The client-side Javascript is defined from within users.js, which is linked from users.html

## What role is Gradle playing in the project, and what is the purpose of build.gradle?

Gradle allows us to run our server locally in order to test everything as it would appear online. 

build.gradle is where the location of the server is specified, dependencies are established, and the karma plugin configurations are set. 
Essentially, it is the location where everything is set up so that we can utilize gradle to run and test our server.

