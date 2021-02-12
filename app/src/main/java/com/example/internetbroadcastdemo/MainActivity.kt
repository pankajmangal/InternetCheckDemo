package com.example.internetbroadcastdemo

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), NetworkChangeCallback {

    private var networkChangeReceiver: NetworkChangeReceiver? = null
    lateinit var tvInternetCheck:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkChangeReceiver = NetworkChangeReceiver(this)
        tvInternetCheck = findViewById(R.id.tvInternetCheck)
    }

    override fun onPause() {
        super.onPause()
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver)
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onNetworkChanged(status: Boolean) {
        Log.e("MainActivity", "Status: $status")

        if(status){
            tvInternetCheck.text = "Internet is available"
        }else{
            tvInternetCheck.text = "Internet is not available"
        }
    }
}