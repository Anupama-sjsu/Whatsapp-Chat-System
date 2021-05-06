package com.example.whatsappchatsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsappchatsystem.AdapterClasses.UserAdapter
import com.example.whatsappchatsystem.Fragments.ModelClasses.Users
import com.example.whatsappchatsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var userAdapter: UserAdapter? = null
    private var mUsers: List<Users>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        mUsers = ArrayList()
        retrieveAllUsers()


        return view
    }

    private fun retrieveAllUsers() {
        var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers = FirebaseDatabase.getInstance().reference.child("Users")

        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                (mUsers as ArrayList<Users>).clear()
                for (snapshot in p0.children) {
                    val user: Users? = p0.getValue(Users::class.java)
                    if (!(user!!.getUID()).equals(firebaseUserID)) {
                        (mUsers as ArrayList<Users>).add(user)

                    }
                }
                userAdapter = UserAdapter(context!!, mUsers!!, false)
            }


            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }

}