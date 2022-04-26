<?php 

$hostname = "localhost";
$username = "root";
$password = "database";
$dbname = "backend";
$query = "select id, title, time from onlyfacts";
$resarr = array();

$conn = mysqli_connect($hostname, $username, $password, $dbname);
//mysqli_set_charset($conn, "utf8mb4");

if($result = mysqli_query($conn, $query)){
    while($row = mysqli_fetch_array($result)){
        array_push($resarr, array("id"=> $row[0], "title"=> $row[1], "time" => $row[2]));
    }

    echo json_encode($resarr, JSON_UNESCAPED_UNICODE);

}else{
    echo "failed to connect database";
}

mysqli_close($conn);

?>