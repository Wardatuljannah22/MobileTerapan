<?php

include("config.php");

	$dhuha= $_POST['dhuha'];
	$shubuh = $_POST['shubuh'];
	$dhuhur = $_POST['dhuhur'];
	$ashar = $_POST['ashar'];
	$maghrib = $_POST['maghrib'];
	$isha = $_POST['isha']

	$sql = "UPDATE jadwal_sholat SET dhuha='$dhuha', shubuh='$shubuh', dhuhur='$dhuhur', ashar='$ashar', maghrib='$maghrib', isha='$isha' WHERE id_jadwal= 1 " ;
	$query = mysqli_query($db, $sql);

	//apakah quer update berhasil?
	if ( $query){

	} else {
		//kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}



?>