package com.e.thetrailerdelaler

import Utils.Constants
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    // var appUtils: AppUtils? = null
    var preference: SharedPreferences? = null
    var constants: Constants? = null
    var login_id = ""
    var videoView: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //  appUtils = AppUtils(applicationContext)
        constants = Constants()
        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        Handler().postDelayed({
            if (login_id.equals("", ignoreCase = true)) {
                intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 1200)



//        videoView = findViewById(R.id.video);
//        val video_url = "android.resource://" + getPackageName() + "/" + R.raw.wwx
//        val videoUri = Uri.parse(video_url);
//
//        videoView?.setVideoURI(videoUri)
//        videoView?.requestFocus()
//        videoView?.setZOrderOnTop(true);
//        videoView?.start()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
