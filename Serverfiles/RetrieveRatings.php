<?php
    header("Content-type: application/json; charset: utf-8");
    
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");

    $date = $_POST["date"];
    $date_int = (int) preg_replace('/[^0-9]/', '', $date);//parse date to int for the query
    //echo $date;
    //$date_int = 20190417;

    $statement = mysqli_prepare($con, 
        "SELECT rating_id, date, dish, rating, comment, username 
        FROM ratings r
        LEFT JOIN user u ON r.user_id = u.user_id
        WHERE date = ?"
    );
    mysqli_stmt_bind_param($statement, "i", $date_int);

    $response = array();
    if(mysqli_stmt_execute($statement))
    {
        mysqli_stmt_store_result($statement);
        mysqli_stmt_bind_result($statement, $rating_id, $date, $dish, $rating, $comment, $username);
        
        $response["success"] = true;    
        while($statement->fetch())
        {
            $bindResults = array($rating_id, $date, $dish, $rating, $comment, $username);
            array_push($response, $bindResults);
        }
    }
    else
    {
        $response["success"] = false;
    }
    
    echo json_encode($response);
?>