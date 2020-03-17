package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_pengumuman_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class pengumuman_masjid : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman_masjid)


        btnkembalipeng.setOnClickListener{
            val intent = Intent(context,Utama::class.java)
            startActivity(intent)
        }

        getdariserver()

        BtnUpdatePeng.setOnClickListener{

            var data_judul_pengumuman :String =EditJudul.text.toString()
            var data_isi_pengumuman :String =EditPengmas.text.toString()

            postkeserver(data_judul_pengumuman, data_isi_pengumuman)

            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }
    }

    fun getdariserver() {

        AndroidNetworking.get("https://fastandroidnetworking.000webhostapp.com/pengumuman_masjid_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i: Int in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("judul_pengumuman"))

                        isi_pengumuman.setText(jsonObject.optString("isi_pengumuman"))
                    }


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
    fun postkeserver(data1: String, data2: String) {

        AndroidNetworking.post("https://fastandroidnetworking.000webhostapp.com/proses_pengumuman.php")
            .addBodyParameter("judul_pengumuman", data1)
            .addBodyParameter("isi_pengumuman", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) { // DO anything with response
                }

                override fun onError(error: ANError) { // handle error
                }
            })
    }
}
