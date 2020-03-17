package com.example.fastandroidnetworking

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONArray
import org.json.JSONObject

class slideshow : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()


        AndroidNetworking.get("https://fastandroidnetworking.000webhostapp.com/slideshow_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("judul_slideshow").toString()
                        var isi2=jsonObject.optString("url_slideshow").toString()

                        users.add(User("$isi1", "$isi2"))


                    }

                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_slideshow)
//
//        val context = this
//        btnkembalisli.setOnClickListener{
//            val intent = Intent(context,Utama::class.java)
//            startActivity(intent)
//        }
//
//        getdariserver()
//
//        BtnUpdateslid.setOnClickListener{
//
//            var data_judul_slideshow :String =EditSlide1.text.toString()
//            var data_url_slideshow :String = EditSlide2.text.toString()
//
//            postkeserver(data_judul_slideshow,data_url_slideshow)
//
//            val intent = Intent(context, Utama::class.java)
//            startActivity(intent)
//        }
//    }
//    fun getdariserver() {
//
//        AndroidNetworking.get("http://10.200.41.155/cobarepo-master/cobarepo-master/slideshow_json.php")
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//                override fun onResponse(response: JSONObject) {
//                    Log.e("_kotlinResponse", response.toString())
//
//                    val jsonArray: JSONArray = response.getJSONArray("result")
//                    for (i: Int in 0 until jsonArray.length()) {
//                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                        Log.e("_kotlineTitle", jsonObject.optString("judul_slideshow"))
//
//                        judul_slideshow.setText(jsonObject.optString("judul_slideshow"))
//                        url_slideshow.setText(jsonObject.optString("url_slideshow"))
//                    }
//
//
//                }
//
//                override fun onError(anError: ANError) {
//                    Log.i("_err", anError.toString())
//                }
//            })
//    }
//
//    fun postkeserver(data1: String, data2: String) {
//
//        AndroidNetworking.post("http://192.168.1.24/cobarepo-master/cobarepo-master/proses_pengumuman.php")
//            .addBodyParameter("judul_slideshow", data1)
//            .addBodyParameter("url_slideshow", data2)
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONArray(object : JSONArrayRequestListener {
//                override fun onResponse(response: JSONArray) { // DO anything with response
//                }
//
//                override fun onError(error: ANError) { // handle error
//                }
//            })
    }
}
