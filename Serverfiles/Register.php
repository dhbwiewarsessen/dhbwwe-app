<?php
    header("Content-type: application/json; charset: utf-8");
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");
    	
    $username = $_POST["username"];
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];

    function registerUser(){
       global $con, $name, $email, $username, $password;
        
        $statement = mysqli_prepare($con, "INSERT INTO user (username, name, email, password) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "ssss", $username, $name, $email, $password);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);
    }
    
    function usernameAvailable() {
        global $con, $username;
        $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    
    function emailAvailable()
    {
        global $con, $email;
        $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ?"); 
        mysqli_stmt_bind_param($statement, "s", $email);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    
    function getUserId(){
        global $con, $username;
         $statement = mysqli_prepare($con, "SELECT user_id FROM user WHERE username = ?");
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
    
        mysqli_stmt_store_result($statement);
        mysqli_stmt_bind_result($statement, $userID);
    
        while(mysqli_stmt_fetch($statement)){
            return $userID;
        }
    }
    
    function sendConfirmationEmail(){
        global $email, $name;
        
        $confirmation_link = "https://dhbwwe.cu.ma/EmailConfirmation.php?".generateRandomString(16);
        $to_email = $email;
        $subject = 'Welcome to DHBWieWarsEssen';
        $message = "What's poppin' ".$name."!\n\nOur humble team welcomes you to a new chapter of your life.\nForget what you know and prepare to be amazed.\nA new paradise awaits in the corporeal form of this android application.\nBear witness to its divinty and stay true to the will of your new god: DHBWieWarsEssen.\n\nRejoice, for this is the solution!\n\nGodspeed to you,\n\nthe DHBWieWarsEssen-Team\n\n\nPS: Please confirm your email address here:\n".$confirmation_link;
        $headers = 'From: noreply@dhbwwe.cu.ma';
        mail($to_email,$subject,$message,$headers);
    }
    
    function generateRandomString($length = 10) {
        return substr(str_shuffle(str_repeat($x='0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length/strlen($x)) )),1,$length);
    }
    
    if(usernameAvailable()){
        if(emailAvailable()){
            registerUser();
            $response["success"] = true;
            $response["userId"] = getUserId();
            sendConfirmationEmail();
        }else{
            $response["success"] = false;
            $response["error"] = "Email not available";
        }
    }else{
        $response["success"] = false;
        $response["error"] = "Username not available";
    }
    echo json_encode($response);
?>
