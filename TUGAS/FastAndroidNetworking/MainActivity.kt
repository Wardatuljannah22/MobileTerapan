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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnkembalishol.setOnClickListener {
            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }

        getdariserver()


        BtnUpSholat.setOnClickListener{

            var data_shubuh :String =editText1.text.toString()
            var data_dhuha:String = editText2.text.toString()
            var data_dhuhur:String = editText3.text.toString()
            var data_ashar:String = editText4.text.toString()
            var data_maghrib:String = editText5.text.toString()
            var data_isha:String = editText6.text.toString()

            postkeserver(data_shubuh,data_dhuha,data_dhuhur,data_ashar,data_maghrib,data_isha)

            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }
    }

    fun getdariserver() {

        AndroidNetworking.get("http://fastandroidnetworking.000webhostapp.com/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val JSONArray1 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray1 .length()) {
                        val jsonObject: JSONObject = JSONArray1 .getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("shubuh"))

                        txt1.setText(jsonObject.optString("shubuh"))
                    }

                    val JSONArray2 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray2.length()) {
                        val jsonObject: JSONObject = JSONArray2.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("dhuhur"))

                        txt2.setText(jsonObject.optString("dhuhur"))
                    }

                    val  JSONArray3 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray3.length()) {
                        val jsonObject: JSONObject = JSONArray3.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("ashar"))

                        txt3.setText(jsonObject.optString("ashar"))
                    }

                    val  JSONArray4 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray4.length()) {
                        val jsonObject: JSONObject = JSONArray4.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("maghrib"))

                        txt4.setText(jsonObject.optString("maghrib"))
                    }

                    val  JSONArray5 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray5.length()) {
                        val jsonObject: JSONObject =JSONArray5.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("isha"))

                        txt5.setText(jsonObject.optString("isha"))
                    }

                    val JSONArray6 = response.getJSONArray("result")
                    for (i: Int in 0 until JSONArray6.length()) {
                        val jsonObject: JSONObject = JSONArray6.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("dhuha"))

                        txt6.setText(jsonObject.optString("dhuha"))
                    }


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1: String, data2: String, data3:String, data4:String, data5:String, data6:String) {

        AndroidNetworking.post("http://192.168.1.31/cobarepo-master/proses_jadwal.php")
            .addBodyParameter("shubuh", data1)
            .addBodyParameter("dhuha", data2)
            .addBodyParameter("duhur", data3)
            .addBodyParameter("ashar", data4)
            .addBodyParameter("maghrib", data5)
            .addBodyParameter("isha", data6)
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