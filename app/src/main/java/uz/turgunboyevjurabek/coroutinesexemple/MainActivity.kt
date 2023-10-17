package uz.turgunboyevjurabek.coroutinesexemple

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.coroutinesexemple.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.coroutinesexemple.network.ApiClient
import uz.turgunboyevjurabek.coroutinesexemple.network.ApiService

class MainActivity : AppCompatActivity() {
private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (isNetworkAvailable(this)){
            try {
                Handler(Looper.getMainLooper()).postDelayed({
                    fetchValyute()
                },1200)

            }catch (e:Exception){
                Toast.makeText(this, "404 not found", Toast.LENGTH_SHORT).show()
            }
        }else{
            binding.tht.setTextSize(25f)
            binding.tht.setTextColor(Color.RED)
            binding.tht.text="Iltimos enternet ga ulaning, va ilovaga qayta kiring! 🤢"
            binding.anim.visibility=View.GONE
        }

    }

    private fun fetchValyute() {
        val apiService= ApiClient.getApiService()
        GlobalScope.launch {
            coroutineScope {
                try {
                    val users=async { apiService.getClients() }
                    val getUsers=users.await()

                    withContext(Dispatchers.Main){
                        binding.tht.text= getUsers.toString()
                        binding.anim.visibility=View.GONE
                    }
                }catch (e:Exception){
                    Log.d("Allambalo", e.message.toString())
                }


            }

        }
    }

}
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
