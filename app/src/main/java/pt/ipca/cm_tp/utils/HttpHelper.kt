package pt.ipca.cm_tp.databases.http

import android.util.Log
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class HttpHelper (){

    // Define URL of the Server
    val address = "http://cc81-193-137-231-114.ngrok.io/ST_get"

    fun post (json: String) : String{

        // Define URL of the Server
        val URL = address //Or https://172.25.224.1:8080/

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