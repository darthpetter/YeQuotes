package com.example.petterjose


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getRetroFit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.kanye.rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getQuote(v: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetroFit().create(APIService::class.java).getYeQuote("").execute()
            val response = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    var quote=response?.quote
                    tvQuote.setText("$quote")
                    println("$quote")
                }else{
                    showError()
                }
            }
        }
    }
    private fun showError(){
        Toast.makeText(this,"Error en la obtención de la frase", Toast.LENGTH_SHORT).show()
    }

}