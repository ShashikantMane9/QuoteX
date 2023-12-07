package com.example.quotex

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class CurrencyViewModel(val context:Context): ViewModel(){
    private var currencyList : Array<Currency> = emptyArray()
    private var index = 0

    init{
        currencyList = loadCurrencyData()
    }

    private fun loadCurrencyData() : Array<Currency>{

        val currencyStream = context.assets.open("currency.json")

        val size = currencyStream.available()

        val buffer = ByteArray(size)

        currencyStream.read(buffer)

        currencyStream.close()

        val json = String(buffer,Charsets.UTF_8)

        val gson = Gson()

        return gson.fromJson(json,Array<Currency>::class.java)
    }

    fun getCurrency() = currencyList[index]

    fun nextCountry() = currencyList[++index]

    fun prevCountry() = currencyList[--index]
}