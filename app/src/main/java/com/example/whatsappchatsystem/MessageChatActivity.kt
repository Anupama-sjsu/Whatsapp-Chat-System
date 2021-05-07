package com.example.whatsappchatsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_message_chat.*

class MessageChatActivity : AppCompatActivity() {

    var userIdVisit: String = ""
    var firebaseUser: FirebaseUser? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)

        intent = intent
        //here
        userIdVisit = intent.getStringExtra("visit_id").toString()
        firebaseUser = FirebaseAuth.getInstance().currentUser


        send_message_btn.setOnClickListener{

            val message = text_message.text.toString()
            if (message == "")
            {
                Toast.makeText( this@MessageChatActivity, "Please type a message before sending...", Toast.LENGTH_LONG).show()
            }else
            {
                sendMessageToUser(firebaseUser!!.uid, userIdVisit, message)
            }
        }
    }

    private fun sendMessageToUser(senderId: String, receiverId: String, message: String)
    {
        val reference = FirebaseDatabase.getInstance().reference
        val messageKey = reference.push().key

        val messageHashMap = HashMap<String, Any>()
        messageHashMap["sender"] = senderId
        messageHashMap["message"] = message
        messageHashMap["receiver"] = receiverId
        messageHashMap["isseen"] = false
        messageHashMap["url"] = ""
        messageHashMap["messageId"] = messageKey!!
        reference.child("Chats")
            .child(messageKey!!)
            .setValue(messageHashMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    val chatListReference = FirebaseDatabase.getInstance()
                        .reference
                        .child("ChatList")

                    chatListReference.child("id").setValue(firebaseUser!!.uid)
                    val reference = FirebaseDatabase.getInstance().reference
                        .child("Users").child(firebaseUser!!.uid)


                }
            }

    }
}

















