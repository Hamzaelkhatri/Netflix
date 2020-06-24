<?php

class DBOperation
{
    public $con;
        function __construct()
         {       require_once dirname(__FILE__).'/DB_connect.php';
            $db=new DBconnect();
           $this->con=$db->connect();    
         }


            function creatUser($username,$pass,$email)
        {
            $password=md5($pass);
            $stmt = $this->con->prepare("INSERT INTO user (id, userName, password,Email) VALUES (NULL,?, ?,?);");
            $stmt->bind_param("sss",$username,$password,$email);
            if($stmt->execute())
                return true;
            else
                return false; 
        }

        function CheckUser($Email,$pass)
        {
            $password=md5($pass);
            $stmt=$this->con->prepare("SELECT * FROM user WHERE Email=? and password=?");
            $stmt->bind_param("ss",$Email,$password);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows>0;
        }

        function User_data($email)
        {
            $DataUser=array();
            $stmt=$this->con->prepare("SELECT userName,Email,Avatar FROM user WHERE Email=?");
            $stmt->bind_param("s",$email);
            $stmt->execute();
            $stmt->store_result();
            $stmt->bind_result($username,$email,$avatar);
            if($stmt->fetch())
            {
                $DataUser['user']=$username;
                $DataUser['email']=$email;
                $DataUser['avatar']=$avatar;
            }
            else
            {
                $DataUser['user']="NULL";
                $DataUser['email']="NULL";
                $DataUser['avatar']="NULL";
            }
            return $DataUser;
        }

        function GetUser($Email)
        {
            $user_N;
            $stmt=$this->con->prepare("SELECT Email FROM user WHERE Email='$Email'");
            $stmt->execute();
            $stmt->store_result();
            $NumRow=$stmt->num_rows;
            $stmt->bind_result($user_N);
            if($stmt->fetch())
            {
                return $user_N;
            }
            else
                return NULL;
            
        }

        function SetProfile($Email,$avatar)
        {
            $stmt=$this->con->prepare("UPDATE user SET avatar=? WHERE Email = ?");
            $stmt->bind_param("is",$avatar,$Email);
            if($stmt->execute())
                return true;
            return false;

        }

        function GetMovies()
        {
            $Movie=array();
            $stmt = $this->con->prepare("SELECT title,description,streamibgLink,img_thun,img_cover,original,season,Episode,title_ep FROM movie ORDER BY id");
            $stmt->execute();
            $stmt->store_result();
            $NumRow=$stmt->num_rows;
            $i=0;
            $stmt->bind_result($user_N['title'],$user_N['description'],$user_N['streaminglink'],
            $user_N['imgthum'],$user_N['imgCover'],$user_N['original'],$user_N['season'],$user_N['Episode'],$user_N['title_ep']);
            while($stmt->fetch())
            {
                $Movie[$i]['title']=$user_N['title'];
                $Movie[$i]['description']=$user_N['description'];
                $Movie[$i]['streaminglink']=$user_N['streaminglink'];
                $Movie[$i]['imgthum']=$user_N['imgthum'];
                $Movie[$i]['imgCover']=$user_N['imgCover'];
                $Movie[$i]['original']=$user_N['original'];
                $Movie[$i]['season']=$user_N['season'];
                $Movie[$i]['Episode']=$user_N['Episode'];
                $Movie[$i]['title_ep']=$user_N['title_ep'];
                $i++;
            }
            return $Movie;
        }
}

?>