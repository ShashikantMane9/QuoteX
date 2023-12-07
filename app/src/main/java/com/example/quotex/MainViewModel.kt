package com.example.quotex

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList : Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets() : Array<Quote>{
        //read file
        val inputStream  = context.assets.open("quote.json")

        //get size of file
        val size :Int = inputStream.available()

        //initialize buffer of size
        val buffer = ByteArray(size)

        //read inputStream AND store in buffer
        inputStream.read(buffer)

        inputStream.close()

        //convert byte to UTF-8 format
        val json = String(buffer,Charsets.UTF_8)


        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)

    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]


}