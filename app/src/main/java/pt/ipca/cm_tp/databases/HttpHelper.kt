package pt.ipca.cm_tp.databases.http

import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper (){

    fun post (json: String) : String{

        // Define URL of the Server
        val URL = "https://172.25.224.1:5000/" //Or https://172.25.224.1:8080/

        // Define Header
        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        // Create a Client for trigger the request
        val client = OkHttpClient()

        //Request Body (JSON)
        val body = RequestBody.create(headerHttp, json)

        // Buld Request Http for the Server
        var request = Request.Builder().url(URL).post(body).build()
        //var request = Request.Builder().url(URL).get(body).build()

        //  Use the Client for the request and get the Answer
        val response = client.newCall(request).execute()

        return response.body().toString()
    }

    fun getStudent (string: String): String{


        Log.d("ANSWER","Get Student!!!$string")

        // Define URL of the Server
        val address = "http://192.168.1.127:5000/flask/ST_get" //Or https://172.25.224.1:8080/

        // Add to URL the number of the Student
        val URL = "$address/$string/"

        //Log.d("ANSWER","$address/$string/")

        // Create a Client for trigger the request
        val client = OkHttpClient()

        // Buld Request Http for the Server
        var request = Request.Builder().url(URL).get().build()

        //  Use the Client for the request and get the Answer
        val response = client.newCall(request).execute()

        // Extract the Body of the request
        val responseBody = response.body()

        // Show the body of the response
        val json = responseBody?.string()
        Log.d("ANSWER","$json")
        return response.body().toString()

    }

    fun getAll(){

    }

}