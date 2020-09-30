package Utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.e.thetrailerdelaler.*

object NotificationHelper {
    @SuppressLint("ResourceType")
    fun displayNotification(
        context: Context,
        title: String,
        body: String,
        clickEvent: String
    ) {

        val intent = Intent(clickEvent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

//        intent.putExtra("type", "Notification")
//        intent.putExtra("id", "")
//        intent.putExtra("help", "")
//        intent.putExtra("position", "100")
        val pendingIntent = PendingIntent.getActivity(
            context,
            100,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val mBuilder = NotificationCompat.Builder(context, LoginActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setColor(ContextCompat.getColor(context,R.color.green))
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(Notification.DEFAULT_VIBRATE.toLong()))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val mNotificationMgr = NotificationManagerCompat.from(context)
        mNotificationMgr.notify(1, mBuilder.build())

    }
}
