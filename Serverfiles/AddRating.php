<?php
    $con = mysqli_connect("localhost", "id7851303_dhbwiewarsessen", "jhgcwbncskijioihe", "id7851303_data");

    $date = $_POST["date"];
    $date_int = (int) preg_replace('/[^0-9]/', '', $date);//parse date to int for the query
    $dish = $_POST["dish"];
    $rating = $_POST["rating"];
    $comment = $_POST["comment"];
    $userId = $_POST["userId"];

    $statement = mysqli_prepare($con, "INSERT INTO ratings (date, dish, rating, comment, user_id) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "isisi", $date_int, $dish, $rating, $comment, $userId);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
