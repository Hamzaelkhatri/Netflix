<?php
require_once '../includes/DB_operation.php';

$response = array();
if($_SERVER['REQUEST_METHOD']=='POST')
{
    if(isset($_POST['Email']) and isset($_POST['avatar']))
    {
        $db=new DBOperation();
        if($db->SetProfile($_POST['Email'],$_POST['avatar']))
        {
            $response['error']=false;
            $response['message']='successfully';
        }
        else
        {
            $response['error']=true;
            $response['message']='Faild please retry later';
        }
    }
    else 
        {
            $response['error']=true;
            $response['message']='server Erreur';

        }
}
else
{
    $response['error']= true;
    $response['message'] ="REQUERED FIELDS ARE MISSING";
}
echo json_encode($response)
?>