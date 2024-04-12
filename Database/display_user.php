<?php
error_reporting(E_ERROR | E_PARSE);
$c = new mysqli("localhost", "root", "", "ANMP_UTS");

if ($c->connect_errno) {
    $arrayerror = array(
        'result' => 'ERROR',
        'msg' => 'Failed to connect DB'
    );
    echo json_encode($arrayerror);
    die();
}

if (isset($_POST['userid'])) {
    $userId = $_POST['userid'];
    $sql = "SELECT * FROM user WHERE id = '$userId' ";

    $result = $c->query($sql);

    $obj = $result->fetch_object();

    if (!empty($obj)) {
        $arrayjson = array(
            'result' => 'OK',
            'data' => $obj
        );
    } else {
        $arrayjson = array(
            'result' => 'ERROR',
            'msg' => 'User not found'
        );
    }
    echo json_encode($arrayjson);
    die();
}
?>