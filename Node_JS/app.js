const express = require('express');
const exphbs = require('express-handlebars');
const bodyParser = require('body-parser');
const mysql = require('mysql2');
const app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.use(bodyParser.json());

require('dotenv').config();

const port = 5000;

const routes = require('./server/routes/student');
app.use('/', routes);


app.use(express.static('public'));
app.use('/css', express.static(__dirname + 'public/css'));
app.use('/images', express.static(__dirname + 'public/images'));

app.engine('hbs', exphbs.engine({extname: '.hbs'}))
app.set('view engine', 'hbs');

app.get('', (req,res) => {
    res.render('home');
});

app.listen(port, () => console.log(`listening to ${port}`));