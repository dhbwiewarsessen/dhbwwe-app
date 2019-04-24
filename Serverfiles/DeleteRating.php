<?php
    header("Content-type: application/json; charset: utf-8");
    
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");


    $id = $_POST["ratingId"];
    
    $statement = mysqli_prepare($con, "DELETE FROM ratings WHERE rating_id = ?");
    mysqli_stmt_bind_param($statement, "i", $id);
    
    $response = array();
    
    if(mysqli_stmt_execute($statement))
    {
        $response["success"] = true;
    }
    else
    {
        $response["success"] = false;
    }
    
    echo json_encode($response);
?>