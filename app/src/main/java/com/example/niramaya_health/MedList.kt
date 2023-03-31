package com.example.niramaya_health

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.niramaya_health.adapters.medAdapter
import com.example.niramaya_health.models.Medicine

class MedList : AppCompatActivity() {

    private lateinit var adapter: medAdapter
    private lateinit var med_recyclerView:RecyclerView
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_list)

        med_recyclerView=findViewById(R.id.med_recyclerView)
        progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Fetching Data..")
        progressDialog.show()

        fetchData()
    }

    // @SuppressLint("SuspiciousIndentation")
    private fun fetchData() {

        val queue = Volley.newRequestQueue(this)

        val url="https://api.jsonserve.com/QDDzKx"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val medJsonArray = it.getJSONArray("medicines")
                val medArray= ArrayList<Medicine>()

                for(i in 0 until medJsonArray.length()) {
                    val medJsonObject = medJsonArray.getJSONObject(i)
                    val meditems= Medicine(

                        medJsonObject.getString("description"),
                        medJsonObject.getString("imageurl"),
                        medJsonObject.getString("title"),
                        medJsonObject.getString("weburl")
                    )
                    medArray.add(meditems)
                }

                val adapter: medAdapter = medAdapter(medArray,this)
                med_recyclerView.adapter=adapter
                med_recyclerView.layoutManager= LinearLayoutManager(this)
                progressDialog.dismiss()



            }, {

            }


        )
        queue.add(request)

    }
}