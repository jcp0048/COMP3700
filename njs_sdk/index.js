$(document).ready(function(){
  $('#order-id-input').bind('keydown', function(event) {
      console.log(event.keyCode);
      switch(event.keyCode){
         //....your actions for the keys .....
      }
   });
})

function database_connect() {
  var mysql = require('mysql')
  var con = mysql.createConnection({
  //Propteries
    host: "localhost",
    user: "root",
    password: "",
    database: "database"
  });

  con.connect(function(error){
    //callback
    if (!!error) {
      console.log("Error");
      return false;
    }  else {
      console.log("Connected")
      return con;
    }
  });
}

function db_insert(connection) {

};


function showOrderId(){
  value = $('#order-id-input').val();

}
