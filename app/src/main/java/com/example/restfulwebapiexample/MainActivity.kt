package com.example.restfulwebapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.koushikdutta.ion.Ion
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun downloadQuoteData(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
            .load("https://inspiration.goprogram.ai/")
            .asString()
            .setCallback { e, result ->
                // Write code to process the result
                // e parameter stores the exception if any
                Log.d(TAG, "The received data : $result")

                // Helper function to parse/process data
                parseQuoteData(result)
            }
    }


    private fun parseQuoteData(result: String){
        // Extract the information from JSON data

        // Received data will be in JSON format as shown below
/*
        {
            "quote": "It matters not what someone is born, but what they grow to be.",
            "author": "Master Oogway"
        }

*/

        val data = JSONObject(result)
        val quote = data.getString("quote")
        val author = data.getString("author")
        Log.d(TAG, "Quote : $quote")
        Log.d(TAG, "Author : $author")

        // Display the quote and author
        findViewById<TextView>(R.id.tv_quote).text = quote
        findViewById<TextView>(R.id.tv_author).text = author
    }


    fun downloadFunnyImage(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
            .load("https://yesno.wtf/api")
            .asString()
            .setCallback { e, result ->
                // Write code to process the result
                // e parameter stores the exception if any
                Log.d(TAG, "The received data : $result")

                // Helper function to parse/process data
                parseFunnyImageData(result)
            }
    }


    private fun parseFunnyImageData(result: String){
        // Extract the information from JSON data

        // Received data will be in JSON format as shown below
/*        {
            "answer": "yes",
            "forced": false,
            "image": "https://yesno.wtf/assets/yes/13-c3082a998e7758be8e582276f35d1336.gif"
}*/
        val data = JSONObject(result)
        val img = data.getString("image")
        val yesOrNo = data.getString("answer")
        //Log.d(TAG, "The image url : $img")

        // Display the image using Ion library, alternatively glide library can be used
        val imageView = findViewById<ImageView>(R.id.image_view)
        Ion.with(imageView).load(img)

        // Display the yes/no text as well
        findViewById<TextView>(R.id.tv_yes_no).text = yesOrNo
    }
}