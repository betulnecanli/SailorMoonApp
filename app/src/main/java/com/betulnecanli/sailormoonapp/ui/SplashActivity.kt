package com.betulnecanli.sailormoonapp.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.betulnecanli.sailormoonapp.R
import com.betulnecanli.sailormoonapp.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    private lateinit var binding : ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.root.background = getDrawable(R.drawable.sa_dark)

            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.root.background = getDrawable(R.drawable.sa)

            }
        }
        supportActionBar?.hide()
        activityScope.launch {
            delay(3000)

            var intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}