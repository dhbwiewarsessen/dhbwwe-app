<?php
    header("Content-type: application/json; charset: utf-8");
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");
    	
    $date = $_POST["date"];
    $time = $_POST["time"];
    $date_int = (int) preg_replace('/[^0-9]/', '', $date);//parse date to int for the query
    $time_int = (int) preg_replace('/[^0-9]/', '', $time);//parse time to int for the query
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

    function isRatingAllowed(){
        global $con, $date_int, $dish, $userId;
        
        $statement = mysqli_prepare($con, "SELECT * FROM ratings WHERE user_id = ? AND date = ? AND dish = ?"); 
        mysqli_stmt_bind_param($statement, "iis", $userId, $date_int, $dish);
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

    $statement = mysqli_prepare($con, "INSERT INTO ratings (date, time, dish, rating, comment, user_id) VALUES (?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "iisisi", $date_int, $time_int, $dish, $rating, $comment, $userId);
    $response = array();
    
    if(isRatingAllowed()){
        if(mysqli_stmt_execute($statement)){
            $response["success"] = true;
            $response["ratingId"] = mysqli_insert_id($con);    
        }else{
            $response["success"] = false;
            $response["error"] = "Server error";
        }        
    }else{
        $response["success"] = false;
        $response["error"] = "You already rated this dish";
    }

    echo json_encode($response);
?>
