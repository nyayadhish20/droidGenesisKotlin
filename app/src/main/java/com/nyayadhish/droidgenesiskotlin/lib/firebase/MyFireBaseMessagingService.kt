package com.nyayadhish.droidgenesiskotlin.lib.firebase

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nyayadhish.droidgenesiskotlin.MainActivity
import com.nyayadhish.droidgenesiskotlin.lib.utils.Constants
import com.nyayadhish.droidgenesiskotlin.lib.utils.debugLog

/**
 * MyFireBaseMessagingService class is used to receive payload from the server and display appropriate notification.
 */

class MyFireBaseMessagingService : FirebaseMessagingService() {
    private val mContext = this

    private var intent: Intent? = null
    private var stackBuilder: TaskStackBuilder? = null
    private var pendingIntent: PendingIntent? = null

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //  Handle FCM messages here.
        //  Check if payload contains a data field.
        debugLog("Notification = $remoteMessage")
        if (remoteMessage.data.isNotEmpty()) {
            debugLog(remoteMessage.notification?.title.toString())
            debugLog(remoteMessage.data.toString())
            createNotifications(remoteMessage)



            /*intent = Intent(mContext, MainActivity::class.java)
            stackBuilder = TaskStackBuilder.create(mContext)
            stackBuilder!!.addParentStack(MainActivity::class.java)
            stackBuilder!!.addNextIntent(intent!!)
            pendingIntent = stackBuilder!!.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            Handler(Looper.getMainLooper()).post {
                Glide.with(MainApplication.instance.getContext())
                    .asBitmap()
                    .load("https://api.androidhive.info/images/sample.jpg")
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {

                        }

                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                        }
                    })
            }
*/
            /*sendNotification(
                remoteMessage.data["title"].toString(),
                remoteMessage.data["body"].toString(),
                pendingIntent
            )*/


        }
    }

    private fun createNotifications(rm: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        sendNotification( rm.notification?.title ?: Constants.DUMMY_TITLE, rm.notification?.body?:""
            , pendingIntent, 0)
    }

    /**
     * Display notification to user.
     *
     * @param title         Notification title
     * @param messageBody   Notification description
     * @param pendingIntent Intent to open specific activity when clicked on notification
     */
    private fun sendNotification(title: String, messageBody: String, pendingIntent: PendingIntent?, reqCode: Int) {

      /*  val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val smallIcon: Int = R.mipmap.ic_notification
        val largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        //  Created notification builder and notified it.
        val mBuilder = NotificationCompat.Builder(applicationContext, getString(R.string.app_name))
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(smallIcon)
            .setLargeIcon(largeIcon)
            .setTicker(title)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notification = mBuilder.build()
        notificationManager.notify(Utility.generateUniqueId(), notification)*/
    }
}