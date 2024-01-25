package com.example.dquotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var showMessageButton: Button
    private lateinit var messageTextView: TextView
    private lateinit var databaseReference: DatabaseReference
    private var messageIndex: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        databaseReference = FirebaseDatabase.getInstance().getReference("messages")

        // Initialize views
        showMessageButton = findViewById(R.id.messageTextView)
        messageTextView = findViewById(R.id.showMessageButton)

        // Set click listener for the button
        showMessageButton.setOnClickListener {
            showMessageFromFirebase()
        }
    }

    private fun showMessageFromFirebase() {
        // Read data from Firebase
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages = mutableListOf<String>()

                // Retrieve all messages from Firebase
                for (childSnapshot in dataSnapshot.children) {
                    val message = childSnapshot.getValue(String::class.java)
                    message?.let { messages.add(it) }
                }

                // Display the next message in the list
                if (messages.isNotEmpty()) {
                    messageIndex = (messageIndex + 1) % messages.size
                    val nextMessage = messages[messageIndex]
                    messageTextView.text = nextMessage
                } else {
                    messageTextView.text = "No messages found"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle failed to read value
                messageTextView.text = "Failed to read value"
            }
        })
    }
}





