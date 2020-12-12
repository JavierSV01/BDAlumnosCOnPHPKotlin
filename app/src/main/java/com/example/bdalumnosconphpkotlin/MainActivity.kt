package com.example.bdalumnosconphpkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  conectarSimple()
        conectarJson()
    }

    fun conectarJson() {  // conecta con una url y devuelve su contenido

        val listaAlumnos = ArrayList<Alumnos>()

            val url = "http://iesayala.ddns.net/12345/index.php"
            val queue = Volley.newRequestQueue(this)
            val stringRequest =
                StringRequest(Request.Method.GET, url, Response.Listener { response ->
                    //conectó correctamente


                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = JSONObject(jsonArray.getString(i))
                        val a = Alumnos(
                            //"1", "Juan", 15, "1bach"
                            jsonObject.get("ID").toString(),
                            jsonObject.get("Nombre").toString(),
                            Integer.parseInt(jsonObject.get("Edad").toString()),
                            jsonObject.get("Curso").toString()
                        )

                        listaAlumnos.add(a)

                    }

                    for (i in 0..listaAlumnos.size-1){
                        println(listaAlumnos.get(i).id)
                        println(listaAlumnos.get(i).nombre)
                    }
                    recyclerView.layoutManager =  LinearLayoutManager(this)
                    val a = RecyclerAdapter(this, listaAlumnos)
                    recyclerView.adapter = a

                }, Response.ErrorListener {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
                        .show()   // se produjo un error
                })
            queue.add(stringRequest)

    }
}