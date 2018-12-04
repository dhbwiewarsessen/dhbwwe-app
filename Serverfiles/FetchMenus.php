<?php
$con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");

$url = 'https://openmensa.org/api/v2/canteens/33/meals'; // URL to Mensa API
$file_content = file_get_contents($url); // put the contents of the file into a variable
$data = json_decode($file_content, true); // decode the JSON feed (true -> to array)
$db_menu = array('Wahlessen 1', 'Wahlessen 2', 'Wahlessen 3');
date_default_timezone_set('Europe/Berlin'); //Set correct time zone, otherwise date() uses server time.
$date = date('Y-m-d', time());

$raw_menus = get_todays_menu($data);
if($raw_menus != NULL){
    $tmp = restructure_menu($raw_menus);
    if (entry_for_date_already_in_table()==false){
        write_menu_to_db($tmp);
        echo "added entry to db";
    }
}

function entry_for_date_already_in_table(){
    global $con, $date;
    $statement = mysqli_prepare($con, "SELECT * FROM menus WHERE date = ?");
    mysqli_stmt_bind_param($statement, "s", $date);
    mysqli_stmt_execute($statement);

    while(mysqli_stmt_fetch($statement)){
        echo "entry already exists in db";
        return true;
    }
    return false;
}

function get_todays_menu($data){
    global $date;   //reference to global variable $date.
    foreach($data as $entry){
        if($entry['date'] == $date){    //compare date in json object and global $date.
            if($entry['closed']){
                echo "canteen closed";
                return NULL;    //returns Null when canteen is closed.
            }else{
                return $entry['meals'];
            }
        }
    }
    echo "canteen closed";
    return NULL; //Null if no entry for today is found and canteen not closed.
}

function restructure_menu($meals){
    global $db_menu;
    foreach($meals as $meal){
        $new_price = $meal['prices']['students'] * 100;
        $db_menu[$meal['category']][] = array('name'=>$meal['name'], 'price'=>$new_price); 
    }
}

function write_menu_to_db($db_menu){
    global $con, $date, $db_menu;
    $statement = mysqli_prepare($con, "INSERT INTO menus (date, dish1, price1, dish2, price2, dish3, price3) VALUES (?, ?, ?, ?, ?, ?, ?)");
    $w1 = $db_menu['Wahlessen 1'][0]['name'];
    $wp1 = $db_menu['Wahlessen 1'][0]['price'];
    $w2 = $db_menu['Wahlessen 2'][0]['name'];
    $wp2 = $db_menu['Wahlessen 2'][0]['price'];
    $w3 = $db_menu['Wahlessen 3'][0]['name'];
    $wp3 = $db_menu['Wahlessen 3'][0]['price'];
    mysqli_stmt_bind_param($statement, "ssisisi", $date, $w1, $wp1, $w2, $wp2, $w3, $wp3);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);
}

function debug_output_menu($meals){ //Only for raw, not restructured data.
    if(is_array($meals))
    {
        foreach($meals as $meal){
        echo $meal['category'] . '<br>';
        echo $meal['name']  . '<br>';
        echo $meal['prices']['student']  . '<br>';
        }
    }else{
        echo 'Eroor, data for display not an array!';
    }
}
?> 
