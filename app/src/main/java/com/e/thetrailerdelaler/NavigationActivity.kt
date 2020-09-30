package com.e.thetrailerdelaler

import Fragment.*
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {
    var rl_add: RelativeLayout? = null
    var toolbar_title: TextView? = null
    var toolbar_img: ImageView? = null

    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        BindUI()

        type = intent.getStringExtra("type")

        when (type) {
            "my location" -> {
                toolbar_title!!.text = "My location"
//                rl_add!!.setVisibility(View.VISIBLE)
                toolbar_img!!.setImageResource(R.drawable.ic_add)
                rl_add!!.setBackgroundResource(R.drawable.red_circle)


                var fragment = FragmentMylocation()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "membership" -> {
                toolbar_title!!.text = "Get membership"
                rl_add!!.setVisibility(View.GONE)
                var fragment = FragmentMembership()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "Account settings" -> {
                toolbar_title!!.text = "Account settings"
                rl_add!!.setVisibility(View.GONE)
                var fragment = FragmentAccountSetting()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "profile" -> {
                toolbar_title!!.text = "Profile"
                rl_add!!.setVisibility(View.GONE)
                var fragment = FragmentProfile()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "Products" -> {
                toolbar_title!!.text = "Products"
                toolbar_img!!.setImageResource(R.drawable.ic_add)
                rl_add!!.setBackgroundResource(R.drawable.red_circle)
                var fragment = FragmentProducts()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

            }
            "Manage ads" -> {
                toolbar_title!!.text = "Manage Ads"
                toolbar_img!!.setImageResource(R.drawable.ic_add)
                rl_add!!.setBackgroundResource(R.drawable.red_circle)
                var fragment = FragmentManagaeAdds(rl_add!!)
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "add ads" -> {
                toolbar_title!!.text = "Add Ads"
                rl_add!!.setVisibility(View.GONE)
                var fragment = FragmentAddAds()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            }
            "Notification" -> {
                txt_CleanAll.visibility = View.VISIBLE
                toolbar_img!!.visibility = View.GONE
                toolbar_title!!.text = "Notification"
                var fragment = FragmentNotification(txt_CleanAll!!)
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

            }
        }

        rl_add?.setOnClickListener {
            when (type) {
                "my location" -> {
                    var intent = Intent(applicationContext, AddLocationActivity::class.java)
                    startActivity(intent)
                }
                "Products" -> {
                    var intent = Intent(applicationContext, AddProductActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    /*fun setTitle(title: String?) {
        toolbar_title!!.text = title
    }*/
    private fun BindUI() {
        rl_add = findViewById(R.id.rl_add)
        toolbar_title = findViewById(R.id.toolbar_title)
        toolbar_img = findViewById(R.id.toolbar_img)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            // overridePendingTransition(R.anim.slide_from_top,R.anim.slide_in_top);
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
