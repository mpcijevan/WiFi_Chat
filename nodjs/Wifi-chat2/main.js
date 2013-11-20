
var http = require('http'),
    express = require('express'),
    user_module = require('./models/user'),
    mysql = require('mysql');

var app = express();
app.use(express.bodyParser());

var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : 'hovik',
    database : 'Chat'
});


app.post('/aduser', function(request, result){
   console.log(request.body );
    var name = request.body.name;
    var surname = request.body.surname;
    var password = request.body.password;
    var nikname = request.body.nikname;
    var date = request.body.date;
    var email = request.body.email;
    var values  =
    {
        name: name,
        surname:surname,
        password: password,
        nikname: nikname,
        date:date,
        email: email

    };

    connection.query('Insert into user SET ?',values,function(val_err, val_result) {
        if (val_err) {
            result.send(val_err);
        } else {
            result.send(val_result);
        }


    });
    result.set("ok");
});

app.get('/getuser',function(req,res,next) {
//connection.connect();

connection.query('SELECT * from user', function(err, rows, fields) {
    if (err) throw err;
    //console.log('The solution is: ', rows[0].username);
    var users_array = new Array();
    for(var i = 0; i< rows.length; i++){
        var user = new user_module.user();
        user.set_ID(rows[i].id);
        user.set_name(rows[i].name);
        user.set_surname(rows[i].surname);
        user.set_nikname(rows[i].nikname);
        user.set_mail(rows[i].email);
        user.set_password(rows[i].password);
        user.set_date(rows[i].date);
        users_array.push(user);
    }

        res.send(users_array);
    });
   // connection.end();
});

app.get('/adduser', function(req, res, next) {

    //connection.connect();
    var name = 'agator';
    var surname = 'stur';
    var password = '400';
    var nikname = 'ROZIK';
    var date = '5678';
    var email = 'dghj';
    var gender = 'sfgh';

    var values  =
    {
        name: name,
        surname:surname,
        password: password,
        nikname: nikname,
        date:date,
        email: email,
        gender: gender
    };

    connection.query('Insert into user SET ?',values,function(err, result) {
        if (err) {
            res.send(err);
        } else {
            res.send(result);
        }


    });
    //connection.end();
});


http.createServer(app).listen(3001,function(){
    console.log("Server started!");
});



