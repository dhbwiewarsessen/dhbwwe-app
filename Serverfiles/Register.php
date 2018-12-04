<?php
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    	
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
    if(usernameAvailable()){
        registerUser();
        $response["success"] = true;
    }else{
        $response["success"] = false;
        $response["error"] = "username not available";
    }
    echo json_encode($response);
?>
