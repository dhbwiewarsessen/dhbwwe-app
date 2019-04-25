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
    
    if(usernameAvailable()){
        if(emailAvailable()){
            registerUser();
            $response["success"] = true;
            $response["userId"] = getUserId();    
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
