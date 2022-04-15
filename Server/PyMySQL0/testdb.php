<?php
$hostname = "localhost";
$username = "strike";
$password = "strikepw";
$dbname = "backendtest";
$query = "select * from test1";
$resarr = array();

$conn = mysqli_connect($hostname, $username, $password, $dbname);

if($result = mysqli_query($conn, $query)){
    while($row = mysqli_fetch_array($result)){
        array_push($resarr, array("num"=> $row[0], "name"=> $row[1]));
    }

    echo json_encode($resarr);

}else{
    echo "failed to connect database";
}

mysqli_close($conn);

?>