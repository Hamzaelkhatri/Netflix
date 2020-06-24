<?php
require_once '../includes/DB_operation.php';
$response =array();
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
if(isset($_POST['userName']) and isset ($_POST['password'])and isset($_POST['Email']))
{
    $db=new DBOperation();
    if($db->creatUser($_POST['userName'],$_POST['password'],$_POST['Email']))
    {
        $response['error']=false;
        $response['message'] ="CREATE SUCSSEFULLY";
    }
    else
    {
        $response['error']=true;
        $response['message'] ="User Aleardy exist";
    }
   
}
else
{
    $response['error']=true;
    $response['message'] ="Server Erreur";
}
}
else
{
    $response['error']= true;
    $response['message'] ="REQUERED FIELDS ARE MISSING";
}
echo json_encode($response);
?>