$(document).ready(function() {
    //Login button logic, gets server side function to check username in db
    $("#login-button").click(function(){
      $.get("/database/search_users/" + $("#user-id-input").val(),function(data){
          if(data=="true"){

          }
          else{

          }
      })
  
    })
  
  
    $('#order-id-input').bind('keydown', function(event) {
      console.log(event.keyCode);
      switch (event.keyCode) {
        //....your actions for the keys .....
      }
    });
  })
  