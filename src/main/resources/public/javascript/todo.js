

function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByOwner() {
  console.log("Getting the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?owner=" +  document.getElementById("owner").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByStatus() {
  console.log("Getting the todos.");
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?status=" +  document.getElementById("status").value, function(returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByBody() {
  console.log("Getting the todos.");
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?contains=" +  document.getElementById("body").value, function(returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByCategory() {
  console.log("Getting the todos.");
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?category=" +  document.getElementById("category").value, function(returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}


function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function(aUrl, aCallback){
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function(){

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
