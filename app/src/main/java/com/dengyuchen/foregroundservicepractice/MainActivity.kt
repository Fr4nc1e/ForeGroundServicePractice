package com.dengyuchen.foregroundservicepractice

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.dengyuchen.foregroundservicepractice.service.Actions
import com.dengyuchen.foregroundservicepractice.service.ForegroundService
import com.dengyuchen.foregroundservicepractice.ui.theme.ForeGroundServicePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0,
            )
        }

        setContent {
            ForeGroundServicePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = {
                            Intent(applicationContext, ForegroundService::class.java).also {
                                it.action = Actions.START.name
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    startForegroundService(it)
                                } else {
                                    startService(it)
                                }
                            }
                        }) {
                            Text(text = "Start")
                        }

                        Button(onClick = {
                            Intent(applicationContext, ForegroundService::class.java).also {
                                it.action = Actions.STOP.name
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    startForegroundService(it)
                                } else {
                                    startService(it)
                                }
                            }
                        }) {
                            Text(text = "Stop")
                        }
                    }
                }
            }
        }
    }
}
