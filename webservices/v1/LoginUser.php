<?php
require_once '../includes/DB_operation.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST')
{
    if(isset($_POST['Email']) and isset($_POST['password']))
    {
        $db=new DBOperation();
        if($db->CheckUser($_POST['Email'],$_POST['password']) > 0)
        {
            $test = $db->GetUser($_POST['Email']);
            $response['error']=false;
            $response['message'] ="Welcome";
        }
        else
        {
            $response['error'] = true;
            $response['message'] = "LOGIN FAILD";
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