package Utils

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage!!.notification != null) {
            var click_event:String= remoteMessage.notification!!.clickAction!!
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body

            NotificationHelper.displayNotification(applicationContext, title!!, body!!,click_event)
        }
    }
}