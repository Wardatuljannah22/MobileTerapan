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
import org.json.JSONArray
import org.json.JSONObject

class identitas_masjid : AppCompatActivity() {
    val context = this;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas_masjid)


        btnkembaliid.setOnClickListener {
            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }

        getdariserver()

        btnUpdate.setOnClickListener{

            var data_namamasjid :String =editTextnamaMasjid.text.toString()
            var data_alamatmasjid :String = editTextAlamatMasjid.text.toString()

            postkeserver(data_namamasjid,data_alamatmasjid)

            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }

    }

    fun getdariserver() {

        AndroidNetworking.get("https://fastandroidnetworking.000webhostapp.com/identitas_masjid_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i: Int in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("nama_masjid"))

                        nama_masjid.setText(jsonObject.optString("nama_masjid"))
                        alamat_masjid.setText(jsonObject.optString("alamat_masjid"))
                    }


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1: String, data2: String) {

        AndroidNetworking.post("https://fastandroidnetworking.000webhostapp.com/proses_identitas.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
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

