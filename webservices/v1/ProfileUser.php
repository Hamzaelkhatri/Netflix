<?php
require_once '../includes/DB_operation.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST')
{
    if(isset($_POST['Email']))
    {
        $db=new DBOperation();
        $response= $db->User_data($_POST['Email']);
    }
    else
    {
        $response['error'] = true;
        $response['message'] = "Load faild";
    }
}
echo json_encode($response);
?>