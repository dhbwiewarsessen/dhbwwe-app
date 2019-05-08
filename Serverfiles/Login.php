<?php
    header("Content-type: application/json; charset: utf-8");
    $con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
    mysqli_set_charset($con, "utf8");
    $con->set_charset("utf8");

    $response = array();
    $username = $_POST["username"];
    $password = $_POST["password"];

    function loginUser(){
        global $con, $username, $password, $response;

        $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
        mysqli_stmt_bind_param($statement, "ss", $username, $password);

        if(mysqli_stmt_execute($statement)){
            mysqli_stmt_store_result($statement);
            mysqli_stmt_bind_result($statement, $userID, $username, $name, $email, $password);

            if(mysqli_stmt_num_rows($statement) < 1)
            {
                $response["success"]=false;
                $response["error"]="Wrong combination of username and password";
                return;
            }

            while(mysqli_stmt_fetch($statement)){
                $response["success"] = true;
                $response["userId"] = $userID;
                $response["username"] = $username;
                $response["name"] = $name;
                $response["email"] = $email;
                $response["password"] = $password;
            }
        }else{
            $response["success"]=false;
            $response["error"]="Server error";
        }
    }

    function usernameExists(){
        global $con, $username;
        $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ?");
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement);
        if ($count < 1){
            return false;
        }else {
            return true;
        }
    }

    if(usernameExists()){
        loginUser();
    }else{
        $response["success"] = false;
        $response["error"] = "Username does not exist";
    }
    
    echo json_encode($response);
?>
