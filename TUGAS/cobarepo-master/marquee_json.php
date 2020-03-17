<?php

include("config.php");



$sql = "SELECT * FROM marquee";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('isi_marque' => $row['isi_marque'],
    
));
}
echo json_encode(array("result" => $result));
?>



