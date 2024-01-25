package com.example.dquotes
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Handle the received message
        remoteMessage.notification?.let {
            val messageBody = it.body
            Log.d(TAG, "Message received: $messageBody")

            // TODO: Display the message in your app (e.g., update UI or show notification)
            // For simplicity, you can use a broadcast or EventBus to communicate with your activity
        }
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingService"
    }
}
