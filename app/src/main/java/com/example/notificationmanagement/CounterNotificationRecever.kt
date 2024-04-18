package com.example.notificationmanagement

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CounterNotificationRecever:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val  service=CounterNotifiationService(context)
        service.showNotification(++Counter.value)
    }
}