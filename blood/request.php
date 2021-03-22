<?php
error_reporting(0);
require "init.php";
 
$name = $_POST["name"];
$phone = $_POST["phone"];
$groupblood = $_POST["group"];
 
//$name = "sdf";
//$password = "sdf";
//$email = "sdf@r54";
 
$sql = "INSERT INTO `request` (`name`, `phone`, `groupblood`) VALUES ( '".$name."', '".$phone."', '".$groupblood."');";
if(!mysqli_query($con, $sql)){
    echo '{"message":"Unable to save the data to the database."}';
}
 
?>