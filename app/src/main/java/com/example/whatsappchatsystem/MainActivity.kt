package com.example.whatsappchatsystem

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.whatsappchatsystem.Fragments.ChatsFragment
import com.example.whatsappchatsystem.Fragments.ModelClasses.Chat
import com.example.whatsappchatsystem.Fragments.ModelClasses.Chatlist
import com.example.whatsappchatsystem.Fragments.ModelClasses.Users
import com.example.whatsappchatsystem.Fragments.SearchFragment
import com.example.whatsappchatsystem.Fragments.SettingsFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.username_register

class MainActivity : AppCompatActivity() {


    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))

        //Fetch the user details from the DB
        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
//
//        viewPagerAdapter.addFragment(ChatsFragment(), title = "Chats")
//        viewPagerAdapter.addFragment(SearchFragment(), title = "Search")
//        viewPagerAdapter.addFragment(SettingsFragment(), title = "Settings")
//
//        viewPager.adapter = viewPagerAdapter
//        tabLayout.setupWithViewPager(viewPager)

        val ref = FirebaseDatabase.getInstance().reference.child("Chats")

        ref!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot)
            {
                val ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
                var countUnreadMessages = 0

                for (dataSnapshot in p0.children)
                {
                    val chat = dataSnapshot.getValue(Chat::class.java)
                    if (chat!!.getReceiver().equals(firebaseUser!!.uid) && !chat.isIsSeen())
                    {
                        countUnreadMessages += 1

                    }
                }

                if (countUnreadMessages == 0)
                {
                    viewPagerAdapter.addFragment(ChatsFragment(), title = "Chats")

                }
                else
                {
                    viewPagerAdapter.addFragment(ChatsFragment(), title = "($countUnreadMessages) Chats")
                }

                viewPagerAdapter.addFragment(SearchFragment(), title = "Search")
                viewPagerAdapter.addFragment(SettingsFragment(), title = "Settings")

                viewPager.adapter = viewPagerAdapter
                tabLayout.setupWithViewPager(viewPager)

            }

            override fun onCancelled(p0: DatabaseError)
            {

            }

        })


        //Display the username and profile picture
        refUsers!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {

                    val user: Users? = snapshot.getValue(Users::class.java)

                    username_main.text = user!!.getUserName()
                    Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(profile_image)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

                return true
            }
        }
        return false

    }


    internal class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager)
    {
        private val fragments:ArrayList<Fragment>
        private val titles:ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String){
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(i: Int): CharSequence? {
            return titles[i]
        }

    }
}