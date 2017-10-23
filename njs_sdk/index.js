var SQL = require('sql.js');
var fs = require('fs');
$(document).ready(function() {
  var test = connect_to_db();
  console.log(test);
  var table = get_table(test, "items");
  console.log(table);
  console.log(table[0].values[0][0]);
  $('#order-id-input').bind('keydown', function(event) {
    console.log(event.keyCode);
    switch (event.keyCode) {
      //....your actions for the keys .....
    }
  });
})

function connect_to_db() {
  databasePath = "./mydb.db"
   db = new SQL.Database(fs.readFileSync(databasePath));
   return db;
}

function db_insert(connection) {

};

function get_table(db, table) {
  var table = db.exec("SELECT * FROM " + table);
  return table;
}


function showOrderId() {
  value = $('#order-id-input').val();

}
