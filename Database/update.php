<?php
error_reporting(E_ERROR | E_PARSE);
$c = new mysqli("localhost", "root", "", "ANMP_UTS");

if ($c->connect_errno) {
    $arrayerror = array('result' => 'ERROR',
        'msg' => 'Failed to connect DB');
    echo json_encode($arrayerror);
    die();
}

if (isset($_POST['password']) && isset($_POST['userid'])) {
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $userid = $_POST['userid'];
    $pass = $_POST['password'];
    
    $sql = "UPDATE user SET password = '$pass', lastname ='$lastname', firstname = '$firstname' WHERE id = $userid";

    $result = $c->query($sql);
    if ($result === false) {
        $arrayerror = array(
            'result' => 'ERROR',
            'msg' => 'Failed to update user'
        );
        echo json_encode($arrayerror);
        die();
    } else if ($c->affected_rows === 0) {
        $arrayerror = array(
            'result' => 'ERROR',
            'msg' => 'No rows updated'
        );
        echo json_encode($arrayerror);
        die();
    }

    $arrayjson = array(
        'result' => 'OK'
    );
    echo json_encode($arrayjson);
    die();
}
?>