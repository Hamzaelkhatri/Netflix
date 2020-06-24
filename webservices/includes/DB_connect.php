<?php

include_once dirname(__FILE__).'/constants.php';
class DBconnect{

    public $con;
    function __construct()
    {
    }
        function connect()
        {
               
                $this->con=new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
                if(mysqli_connect_errno())
                {
                    echo "faild to connect database".mysqli_connect_error();
                }
                else
                {
                    return $this->con;
                }
        }
}