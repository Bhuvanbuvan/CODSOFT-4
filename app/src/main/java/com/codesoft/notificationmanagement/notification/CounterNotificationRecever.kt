package com.codesoft.notificationmanagement.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.codesoft.notificationmanagement.datamodel.Counter

class CounterNotificationRecever:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val  service= CounterNotifiationService(context)
        service.showNotification(++Counter.value)
    }
}