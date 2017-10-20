$(document).ready(function() {
  $('#order-id-input').bind('keydown', function(event) {
    console.log(event.keyCode);
    switch (event.keyCode) {
      //....your actions for the keys .....
    }
  });
})

var mysql = require('mysql')
var con = mysql.createConnection({
  //Propteries
  multipleStatements: true,
  host: "localhost",
  user: "comp3700",
  password: "root",
  database: "mydb"
});

function createDb(){
  con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
    /*Create a database named "mydb":*/
    con.query("CREATE DATABASE mydb", function (err, result) {
      if (err) throw err;
      console.log("Database created");
    });
  });
};

function db_insert(connection) {

};


function showOrderId() {
  value = $('#order-id-input').val();

}
