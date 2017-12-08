var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('login');
});

router.get('/store/:username', function(req, res, next) {
  if(){
    res.render('index') 
  }
});
module.exports = router;
