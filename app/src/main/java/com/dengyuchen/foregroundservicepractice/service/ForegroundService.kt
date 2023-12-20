package com.dengyuchen.foregroundservicepractice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.dengyuchen.foregroundservicepractice.R
import com.dengyuchen.foregroundservicepractice.util.Constants

class ForegroundService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.name -> {
                start()
            }
            Actions.STOP.name -> {
                stopSelf()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("This is a foreground service")
            .setContentText("test test test")
            .build()

        startForeground(1, notification)
    }
}

enum class Actions {
    START,
    STOP,
}
