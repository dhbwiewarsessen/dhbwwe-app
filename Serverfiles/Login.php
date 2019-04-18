<?php
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $name, $email, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["userId"] = $userID;
        $response["username"] = $username;
        $response["name"] = $name;
        $response["email"] = $email;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>
