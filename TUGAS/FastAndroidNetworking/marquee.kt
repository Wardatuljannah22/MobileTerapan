package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_marquee.*
import org.json.JSONArray
import org.json.JSONObject

class marquee : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)



        butKembaliMar.setOnClickListener {
            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }


        getdariserver()

        btnUpMar.setOnClickListener{

            var data_marquee :String = editTextMarquee.text.toString()

            postkeserver(data_marquee)

            val intent = Intent(context, Utama::class.java)
            startActivity(intent)
        }

    }

    fun getdariserver(){

        AndroidNetworking.get("https://fastandroidnetworking.000webhostapp.com/marquee_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i: Int in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTitle", jsonObject.optString("isi_marquee"))

                        isi_marquee.setText(jsonObject.optString("isi_marquee"))
                    }


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

    }

    fun postkeserver(data1: String) {

        AndroidNetworking.post("https://fastandroidnetworking.000webhostapp.com/proses_marquee.php")
            .addBodyParameter("isi_marquee", data1)
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
