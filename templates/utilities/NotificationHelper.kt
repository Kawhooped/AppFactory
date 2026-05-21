package com.appfactory.utilities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannels()
    }

    /**
     * Create notification channels for different notification types
     */
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // High priority channel for important alerts
            val highPriorityChannel = NotificationChannel(
                CHANNEL_HIGH_PRIORITY,
                "Important Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for important alerts and updates"
            }

            // Default channel for normal notifications
            val defaultChannel = NotificationChannel(
                CHANNEL_DEFAULT,
                "General Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "General app notifications"
            }

            // Low priority channel for background updates
            val lowPriorityChannel = NotificationChannel(
                CHANNEL_LOW_PRIORITY,
                "Background Updates",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Background sync and updates"
            }

            notificationManager.createNotificationChannels(
                listOf(highPriorityChannel, defaultChannel, lowPriorityChannel)
            )
        }
    }

    /**
     * Send a simple notification
     */
    fun sendNotification(
        notificationId: Int,
        title: String,
        message: String,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT
    ) {
        val channelId = when (priority) {
            NotificationCompat.PRIORITY_HIGH -> CHANNEL_HIGH_PRIORITY
            NotificationCompat.PRIORITY_LOW -> CHANNEL_LOW_PRIORITY
            else -> CHANNEL_DEFAULT
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(priority)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    /**
     * Send a progress notification
     */
    fun showProgressNotification(
        notificationId: Int,
        title: String,
        message: String,
        progress: Int,
        isIndeterminate: Boolean = false
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setProgress(100, progress, isIndeterminate)
            .setOngoing(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    /**
     * Cancel a notification
     */
    fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    /**
     * Cancel all notifications
     */
    fun cancelAllNotifications() {
        notificationManager.cancelAll()
    }

    companion object {
        const val CHANNEL_HIGH_PRIORITY = "high_priority_channel"
        const val CHANNEL_DEFAULT = "default_channel"
        const val CHANNEL_LOW_PRIORITY = "low_priority_channel"
    }
}
