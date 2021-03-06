package com.example.whatsappchatsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappchatsystem.AdapterClasses.UserAdapter
import com.example.whatsappchatsystem.Fragments.ModelClasses.Chatlist
import com.example.whatsappchatsystem.Fragments.ModelClasses.Users
import com.example.whatsappchatsystem.Notifications.Token
import com.example.whatsappchatsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase


class ChatsFragment : Fragment() {

    private var userAdapter: UserAdapter? = null
    private var mUsers: List<Users>? = null
    private var usersChatList: List<Chatlist>? = null
    private var firebaseUser: FirebaseUser? = null

    lateinit var recycler_view_chatlist: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_chats, container, false)

        recycler_view_chatlist = view.findViewById(R.id.recycler_view_chatlist)
        recycler_view_chatlist.setHasFixedSize(true)
        recycler_view_chatlist.layoutManager = LinearLayoutManager(context)

        firebaseUser = FirebaseAuth.getInstance().currentUser

        usersChatList = ArrayList()

        val ref = FirebaseDatabase.getInstance().reference.child("ChatList").child(firebaseUser!!.uid)
        ref!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot)
            {

                (usersChatList as ArrayList).clear()
                for (dataSnapshot in p0.children)
                {
                    val chatlist = dataSnapshot.getValue(Chatlist::class.java)
                    (usersChatList as ArrayList).add(chatlist!!)
                }

                retrieveChatList()

            }

            override fun onCancelled(p0: DatabaseError)
            {

            }
        })

        updateToken(FirebaseInstanceId.getInstance().token)

        return view
    }

    private fun updateToken(token: String?)
    {
        val ref = FirebaseDatabase.getInstance().reference.child("Tokens")
        val token1 =  Token(token!!)
        ref.child(firebaseUser!!.uid).setValue(token1)




    }

    private fun retrieveChatList()
    {
        mUsers = ArrayList()

        val ref = FirebaseDatabase.getInstance().reference.child("Users")
        ref!!.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot)
            {
                (mUsers as ArrayList).clear()

                for (dataSnapshot in p0.children)
                {
                    val user = dataSnapshot.getValue(Users::class.java)

                    for (eachChatList in usersChatList!!)
                    {
                        if (user!!.getUID().equals(eachChatList.getId()))
                        {

                                (mUsers as ArrayList).add(user!!)

                        }
                    }

                }

                userAdapter = UserAdapter(context!!, (mUsers as ArrayList), true)

                recycler_view_chatlist.adapter = userAdapter
            }

            override fun onCancelled(p0: DatabaseError)
            {

            }


        })


    }

}