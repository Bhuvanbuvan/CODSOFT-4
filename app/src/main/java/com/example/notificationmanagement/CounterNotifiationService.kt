package com.example.notificationmanagement

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class CounterNotifiationService(
    private val context: Context
) {

    private var notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counter :Int){
        val activityIntent=Intent(context,MainActivity::class.java)
        val activityPendingIntent=PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val incrementIntent=PendingIntent.getBroadcast(
            context,
            2,
            Intent(context,CounterNotificationRecever::class.java),
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notifiction =NotificationCompat.Builder(
            context, Counter_Channel_Id
        ).setSmallIcon(R.drawable.baseline_sunny_24)
            .setContentTitle("Increment Counter")
            .setContentText("The Counter Value:  $counter")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.baseline_sunny_24,
                "Increment",
                incrementIntent
            ).build()

        notificationManager.notify(
            1,notifiction
        )
    }
    companion object{
        const val Counter_Channel_Id="Counter_Channel"
    }
}