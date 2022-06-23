package com.example.cryptoapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import info.gopaisa.cryptoapp.RecyclerAdapter
import info.gopaisa.cryptoapp.StockPrice
import info.gopaisa.cryptoapp.StockService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefereshLayout)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getCryptoStock()


        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            getCryptoStock()
        })
    }

    private fun getCryptoStock() {
        swipeRefreshLayout.isRefreshing = true
        val stocks = StockService.stockInstance.getCryptoStocks()
        stocks.enqueue(object : Callback<ArrayList<StockPrice>> {
            override fun onResponse(call: Call<ArrayList<StockPrice>>, response: Response<ArrayList<StockPrice>>) {
                val arrayList : ArrayList<StockPrice>? = response.body()
                recyclerView.adapter = arrayList?.let { RecyclerAdapter(it) }
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<ArrayList<StockPrice>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Server Error!", Toast.LENGTH_LONG).show()
            }


        })
    }
}