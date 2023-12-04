const mysql = require('mysql2');



var con = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "root",
    database: "rms",
    dateStrings: "date"
});

exports.view = (req,res) => {
    res.render('home')
}

exports.login = (req,res) => {
    res.render('login')
}

exports.loginForm = (req,res) => {
    const { email, password } = req.body;
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('SELECT * FROM teacher WHERE email = ? and pass = ?', [email,password], (err,rows) => {
            connection.release();
            
            if(!err && rows[0] != undefined) {
                    res.redirect('/dashboard')
            } else {
                res.render('login', {alert: 'Login Failed'});
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};

exports.logout = (req,res) => {
    res.redirect('/login')
}

exports.resultView = (req,res) => {
    res.render('view-result')
}

exports.resultViewForm = (req,res) => {
    const { rollno, name } = req.body;
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('SELECT * FROM student WHERE rollno = ? and name = ?', [rollno,name], (err,rows) => {
            connection.release();
            
            if(!err && rows[0] != undefined) {
                res.render('result', {rows} );
            } else {
                res.render('view-result', {alert: 'no result found'});
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};

exports.home = (req,res) => {
    
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);
    
        connection.query('SELECT * FROM student', (err,rows) => {
            connection.release();
    
            if(!err) {
                res.render('dashboard', { rows });
            } else {
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });

};

exports.form = (req,res) => {
    res.render('add-result');
};

exports.newResult = (req,res) => {
    const { rollno, name, dob, score } = req.body;
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('INSERT INTO student SET rollno = ?, name = ?, dob = ?, score = ?', [rollno, name, dob, score], (err,rows) => {
            connection.release();
            
            if(!err) {
                res.render('add-result', {alert: 'Result added successfully.'});
            } else {
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};



exports.edit = (req,res) => {
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('SELECT * FROM student WHERE id = ?', [req.params.id], (err,rows) => {
            connection.release();
            
            if(!err) {
                res.render('edit-result', { rows });
            } else {
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};

exports.editResult = (req,res) => {
    const { rollno, name, dob, score } = req.body;
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('UPDATE student SET rollno = ?, name = ?, dob = ?, score = ? WHERE id = ?', [rollno, name, dob, score, req.params.id], (err,rows) => {
            connection.release();
            
            if(!err) {
                con.getConnection(function(err,connection) {
                    if (err) throw err;
                    console.log("Connected as " + connection.threadId);        
                    
                    connection.query('SELECT * FROM student WHERE id = ?', [req.params.id], (err,rows) => {
                        connection.release();
                        
                        if(!err) {
                            res.render('edit-result', { rows, alert: 'User edited successfully.' });
                        } else {
                            console.log(err);
                        }
                
                        console.log('data from db \n',rows);
                    });
                });
            } else {
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};

exports.delete = (req,res) => {
    con.getConnection(function(err,connection) {
        if (err) throw err;
        console.log("Connected as " + connection.threadId);        
        
        connection.query('DELETE FROM student WHERE id = ?', [req.params.id], (err,rows) => {
            connection.release();
            
            if(!err) {
                res.redirect('/dashboard');
            } else {
                console.log(err);
            }
    
            console.log('data from db \n',rows);
        });
    });
};