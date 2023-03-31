package com.example.niramaya_health

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.niramaya_health.adapters.labTestAdapter
import com.example.niramaya_health.databinding.ProgressBarBinding
import com.example.niramaya_health.models.Test

class LabTest : AppCompatActivity() {

    private lateinit var lab_recyclerView: RecyclerView
    private lateinit var progressDialog: ProgressDialog

    private lateinit var adapter: labTestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)

        lab_recyclerView=findViewById(R.id.lab_recyclerView);

        progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Fetching Tests..")
        progressDialog.show()



        fetchdata()


    }

    @SuppressLint("SuspiciousIndentation")
    private fun fetchdata() {

        val queue = Volley.newRequestQueue(this)

        val url="https://api.jsonserve.com/KumMmx"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val labJsonArray = it.getJSONArray("tests")
                val labArray= ArrayList<Test>()

                for(i in 0 until labJsonArray.length()) {
                    val labJsonObject = labJsonArray.getJSONObject(i)
                    val labitems = Test(

                        labJsonObject.getString("description"),
                        labJsonObject.getString("image"),
                        labJsonObject.getString("lab_company_url"),
                        labJsonObject.getString("title")
                    )
                    labArray.add(labitems)
                }
                lab_recyclerView.layoutManager= LinearLayoutManager(this)
                val adapter: labTestAdapter = labTestAdapter(labArray,this)
                lab_recyclerView.adapter = adapter
                progressDialog.dismiss()


                //adapter.
            }, {

            }


        )
        queue.add(request)




    }
}