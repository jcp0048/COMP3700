var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var SQL = require('sql.js');
var fs = require('fs');

function connect_to_db() {
  databasePath = "C:/Users/ship1/Github repo/COMP3700/SMS/mydb.db"
   db = new SQL.Database(fs.readFileSync(databasePath));
   return db;
}

function search_items() {
  var table = db.exec("SELECT * FROM items");
}

function search_users(username) {
  var user = db.exec("SELECT name FROM users");
  var users = user[0].values;
  for(var i = 0; i < users.length; i++) {
    if (users[i][0] == username) {
      return true
    }
    else{
      return ("You have entered invalid credentials")
    }
  }
}

function db_insert(connection) {

};

function showOrderId() {
  value = $('#order-id-input').val();
}


db = connect_to_db()
router.get('/search_users/:username', function(req, res, next) {
res.send(search_users(req.params.username))
});

module.exports = router;
