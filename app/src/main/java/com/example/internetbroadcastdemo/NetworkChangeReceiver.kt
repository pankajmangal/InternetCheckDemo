package com.example.internetbroadcastdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log


class NetworkChangeReceiver(private val callback: NetworkChangeCallback) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val status = isNetworkAvailable(context)
        showLog("" + status)

        callback.onNetworkChanged(status)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = cm.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        } catch (e: NullPointerException) {
            showLog(e.localizedMessage)
            false
        }
    }

    private fun showLog(message: String) {
        Log.e("NetworkChangeReceiver", "" + message)
    }
}