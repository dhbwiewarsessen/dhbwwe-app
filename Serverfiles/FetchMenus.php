<?php
$con = mysqli_connect("localhost", "dhbwwe_user", "jhgcwbncskijioihe", "dhbwwe_data");
mysqli_set_charset($con, "utf8");
$con->set_charset("utf8");

$url = 'https://openmensa.org/api/v2/canteens/33/meals'; // URL to Mensa API
$file_content = file_get_contents($url); // put the contents of the file into a variable
$data = json_decode($file_content, true); // decode the JSON feed (true -> to array)

if($data != NULL){
	foreach($data as $days_menu){
		if (!entry_for_date_already_in_table($days_menu['date'])){
			write_menu_to_db($days_menu);
			echo "added entry to db <br>";
		}else{
			replace_entry_in_db($days_menu);
			echo "replaced entry in db <br>";
		}
	}
}

function entry_for_date_already_in_table($date){
    global $con;
    $statement = mysqli_prepare($con, "SELECT * FROM menus WHERE date = ?");
    mysqli_stmt_bind_param($statement, "s", $date);
    mysqli_stmt_execute($statement);

    while(mysqli_stmt_fetch($statement)){
        echo "entry already exists in db <br>";
        return true;
    }
    return false;
}

function replace_entry_in_db($replacement_data){
	global $con;
	$statement = mysqli_prepare($con, 'UPDATE menus SET (dish1, price1, dish2, price2, dish3, price3) VALUES (?, ?, ?, ?, ?, ?, ?) WHERE date = ?');
	$rep_data_restructured = restructure_menu($replacement_data);
	$w1 = $rep_data_restructured['Wahlessen 1'][0]['name'];
    $wp1 = $rep_data_restructured['Wahlessen 1'][0]['price'];
    $w2 = $rep_data_restructured['Wahlessen 2'][0]['name'];
    $wp2 = $rep_data_restructured['Wahlessen 2'][0]['price'];
    $w3 = $rep_data_restructured['Wahlessen 3'][0]['name'];
    $wp3 = $rep_data_restructured['Wahlessen 3'][0]['price'];
	$date = $replacement_data['date'];
    mysqli_stmt_bind_param($statement, "sisisis", $w1, $wp1, $w2, $wp2, $w3, $wp3, $date);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);
}

function write_menu_to_db($db_menu){
    global $con;
    $statement = mysqli_prepare($con, "INSERT INTO menus (date, dish1, price1, dish2, price2, dish3, price3) VALUES (?, ?, ?, ?, ?, ?, ?)");
	$data_restructured = restructure_menu($db_menu);
	$date = $db_menu['date'];
    $w1 = $data_restructured['Wahlessen 1'][0]['name'];
    $wp1 = $data_restructured['Wahlessen 1'][0]['price'];
    $w2 = $data_restructured['Wahlessen 2'][0]['name'];
    $wp2 = $data_restructured['Wahlessen 2'][0]['price'];
    $w3 = $data_restructured['Wahlessen 3'][0]['name'];
    $wp3 = $data_restructured['Wahlessen 3'][0]['price'];
    mysqli_stmt_bind_param($statement, "ssisisi", $date, $w1, $wp1, $w2, $wp2, $w3, $wp3);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);
}

function restructure_menu($raw_entry){
    $db_menu_structure = array('Wahlessen 1', 'Wahlessen 2', 'Wahlessen 3');
    foreach($raw_entry['meals'] as $meal){
        $new_price = $meal['prices']['students'] * 100 + 0.01;
        $db_menu_structure[$meal['category']][] = array('name'=>$meal['name'], 'price'=>$new_price); 
    }
	return $db_menu_structure;
}

//function debug_output_menu($meals){ //Only for raw, not restructured data.
//    if(is_array($meals))
//    {
//        foreach($meals as $meal){
//        echo $meal['category'] . '<br>';
//        echo $meal['name']  . '<br>';
//        echo $meal['prices']['student']  . '<br>';
//        }
//    }else{
//        echo 'Erooooor, data for display not an array!';
//    }
//}
?> 
