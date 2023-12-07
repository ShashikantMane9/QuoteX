package com.example.quotex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var currencyViewModel: CurrencyViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    private val countryTv : TextView
        get() = findViewById(R.id.country)

    private val currencyTv : TextView
        get() = findViewById(R.id.currency)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        currencyViewModel = ViewModelProvider(this,CurrencyViewModelFactory(application)).get(CurrencyViewModel::class.java)

        setQuote(mainViewModel.getQuote())

        setCurrency(currencyViewModel.getCurrency())
    }

    private fun setCurrency(currency: Currency) {
        countryTv.text = currency.country
        currencyTv.text = currency.currency_name
    }


    fun setQuote(quote : Quote){
        quoteText.text = quote.quote
        quoteAuthor .text = quote.author
    }

    fun previousQuote(view: View) {
        setQuote(mainViewModel.previousQuote())
        setCurrency(currencyViewModel.prevCountry())
    }
    fun nextQuote(view: View) {
        setQuote(mainViewModel.nextQuote())
        setCurrency(currencyViewModel.nextCountry())
    }



    fun onShare(view: View) {
        var intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().quote)
        intent.putExtra(Intent.EXTRA_TEXT,currencyViewModel.getCurrency().currency_name)
        startActivity(intent)
    }


}