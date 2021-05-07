package com.example.whatsappchatsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_message_chat.*

class MessageChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)

        send_message_btn.setOnClickListener{

            val message = text_message.text.toString()
            if (message == "")
            {

            }else
            {
                
            }
        }
    }
}