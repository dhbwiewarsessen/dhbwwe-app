<?php
    $con = mysqli_connect("localhost", "id7851303_dhbwiewarsessen", "jhgcwbncskijioihe", "id7851303_data");

    $menu = $_POST["menu"];
    $rating = $_POST["rating"];
    $comment = $_POST["comment"];
    $userId = $_POST["userId"];
    $statement = mysqli_prepare($con, "INSERT INTO ratings (menu, rating, comment, userId) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $menu, $rating, $comment, $userId);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>