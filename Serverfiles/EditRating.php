<?php
    header("Content-type: application/json; charset: utf-8");
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");
    	
    $ratingId = $_POST["rating_id"];
    $rating = $_POST["rating"];
    $comment = $_POST["comment"];
    
    //Test-Data
    //$ratingId = 51;
    //$rating = 10;
    //$comment = "Ultra gut";

    $statement = mysqli_prepare($con, "UPDATE ratings SET rating = ?, comment = ? WHERE rating_id = ?");
    mysqli_stmt_bind_param($statement, "isi", $rating, $comment, $ratingId);
    
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