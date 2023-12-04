const express = require('express');
const router = express.Router();
const studentController = require('../controllers/studentController');

router.get('/', studentController.view);

router.get('/dashboard', studentController.home);

router.get('/login', studentController.login);
router.post('/login', studentController.loginForm);

router.get('/logout', studentController.logout);

router.get('/viewresult', studentController.resultView);
router.post('/viewresult', studentController.resultViewForm);

router.get('/addresult', studentController.form);
router.post('/addresult', studentController.newResult);

router.get('/editresult/:id', studentController.edit);
router.post('/editresult/:id', studentController.editResult);

router.get('/:id', studentController.delete);

module.exports = router;