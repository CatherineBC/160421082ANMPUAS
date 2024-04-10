<?php
error_reporting(E_ERROR | E_PARSE);
$c = new mysqli("localhost", "root", "", "ANMP_UTS");

if ($c->connect_errno) {
    $arrayerror = array('result' => 'ERROR',
        'msg' => 'Failed to connect DB');
    echo json_encode($arrayerror);
    die();
}
 if(isset($_POST['username']))
 {
    $username = $_POST['username'];
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $pass = $_POST['pass'];
    $email = $_POST['email'];
    $sql = "Insert into user (email,password,username,firstname,lastname) values ('$email','$pass','$username','$firstname','$lastname')";

    $result = $c->query($sql);

    if ($result) {
        $arrayjson = array(
            'result' => 'OK');
    } else {
        $arrayjson = array(
            'result' => 'ERROR',
            'msg' => 'Failed to insert into the database');
    }
    
    echo json_encode($arrayjson);
    die();
}
?>