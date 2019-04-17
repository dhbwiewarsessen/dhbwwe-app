<?php
    header("Content-type: application/json; charset: utf-8");
    
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");


    $date = $_POST["date"];
    $date_int = (int) preg_replace('/[^0-9]/', '', $date);//parse date to int for the query
    //echo $date;

    $statement = mysqli_prepare($con, "SELECT * FROM ratings WHERE date = ?");
    mysqli_stmt_bind_param($statement, "i", $date_int);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $rating_id, $date, $dish, $rating, $comment, $user_id);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["rating_id"] = $rating_id;
        $response["dish"] = $dish;
        $response["rating"] = $rating;
        $response["comment"] = $comment;
        $response["user_id"] = $user_id;
    }
    
    echo json_encode($response);
?>