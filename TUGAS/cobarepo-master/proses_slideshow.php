<?php

include("config.php");

	$judul_slideshow= $_POST['judul_slideshow'];
	$url_slideshow = $_POST['url_slideshow'];
	

	$sql = "UPDATE slideshow SET judul_slideshow='$judul_slideshow', url_slideshow='$url_slideshow' " ;
	$query = mysqli_query($db, $sql);

	//apakah quer update berhasil?
	if ( $query){

	} else {
		//kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}



?>