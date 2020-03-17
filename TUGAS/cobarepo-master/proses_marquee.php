<?php

include("config.php");

	$isi_marquee= $_POST['isi_marquee'];
	

	$sql = "UPDATE marquee SET isi_marquee='$isi_marquee' " ;
	$query = mysqli_query($db, $sql);

	//apakah quer update berhasil?
	if ( $query){

	} else {
		//kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}



?>