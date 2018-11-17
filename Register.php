<?php
    $con = mysqli_connect("localhost", "id7851303_dhbwiewarsessen", "jhgcwbncskijioihe", "id7851303_data");
    	
    $username = $_POST["username"];
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (username, name, email, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $username, $name, $email, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);
?>
