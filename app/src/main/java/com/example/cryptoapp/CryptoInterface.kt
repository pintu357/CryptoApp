package info.gopaisa.cryptoapp


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.wazirx.com/sapi/v1/tickers/"
interface CryptoInterface {

    @GET("24hr")
    fun getCryptoStocks() : Call<ArrayList<StockPrice>>


}
object StockService {
    val stockInstance: CryptoInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build()

        stockInstance = retrofit.create(CryptoInterface::class.java)
    }
}