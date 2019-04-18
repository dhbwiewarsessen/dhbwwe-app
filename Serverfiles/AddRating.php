<?php
    header("Content-type: application/json; charset: utf-8");
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");
    	
    $date = $_POST["date"];
    $date_int = (int) preg_replace('/[^0-9]/', '', $date);//parse date to int for the query
    $dish = $_POST["dish"];
    $rating = $_POST["rating"];
    $comment = $_POST["comment"];
    $userId = $_POST["user_id"];

//  Test values    
//  $date_int = 20190216;
//  $dish = "Huettenkaese";
//  $rating = 2;
//  $comment = "Subba";
//  $userId = 131;

    $statement = mysqli_prepare($con, "INSERT INTO ratings (date, dish, rating, comment, user_id) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "isisi", $date_int, $dish, $rating, $comment, $userId);
    
    $response = array();
    if(mysqli_stmt_execute($statement))
    {
        $response["success"] = true;
        $response["ratingId"] = mysqli_insert_id($con);    
    }
    else
    {
        $response["success"] = false;
    }
    
    echo json_encode($response);
?>
