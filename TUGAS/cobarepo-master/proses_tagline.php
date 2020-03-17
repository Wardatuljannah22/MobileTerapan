<?php

include("config.php");

	$isi_tagline= $_POST['isi_tagline'];
	
	

	$sql = "UPDATE tagline SET isi_tagline='$isi_tagline' " ;
	$query = mysqli_query($db, $sql);

	//apakah quer update berhasil?
	if ( $query){

	} else {
		//kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}



?>