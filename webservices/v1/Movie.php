<?php
require_once '../includes/DB_operation.php';
if($_SERVER['REQUEST_METHOD']=='POST')
{
    $db= new DBOperation();
    $response['Movie']= $db->GetMovies();
}
echo json_encode($response)
?>