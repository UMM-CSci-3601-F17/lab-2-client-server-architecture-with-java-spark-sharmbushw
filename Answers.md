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



## What are the contents of the public folder? What is the purpose of each of the HTML files there?
The public folder contains all of the code that users will interact directly with, i.e, the client.

The index.html is the page the user sees when they initially navigate to the site. It includes navigation to the
two other pages, as well as a button which triggers a simple javascript function that sends an alert to the screen.

The about.html is simply an "about" page that can be navigated to from index.html

The users.html page links to users.js, which actually interacts with the API. The html page links function calls
to buttons.

