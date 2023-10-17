package uz.turgunboyevjurabek.coroutinesexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

        Handler(Looper.getMainLooper()).postDelayed({
            fetchValyute()
        },1200)

    }

    private fun fetchValyute() {
        GlobalScope.launch {
            val apiService= ApiClient.getApiService()
            val users=apiService.getClients()

            withContext(Dispatchers.Main){
                binding.tht.text= users.toString()
                binding.anim.visibility=View.GONE
            }

        }
    }
}