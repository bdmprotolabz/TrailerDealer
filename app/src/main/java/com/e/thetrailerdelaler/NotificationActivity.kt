package com.e.thetrailerdelaler


import Adapter.AdapterNotification
import Model.ServerResponseClearNotification
import Model.ServerResponseGetNotificationList
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_navigation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : AppCompatActivity() {
    var recyclerViewNotification: RecyclerView? = null
    var adapterNotification: AdapterNotification? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var constants: Constants? = null
    var login_id = "";
    var appUtils: AppUtils? = null
    var no: TextView? = null
    var txt_CleanAll: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))


        })
        no = findViewById(R.id.no)
        txt_CleanAll=findViewById(R.id.txt_CleanAll)
        constants = Constants()
        appUtils = AppUtils(this)
        appUtils!!.showDialog(this)

        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)


        login_id = preference!!.getString("loginid", "")!!

        recyclerViewNotification = findViewById(R.id.recycler_Notification)
        recyclerViewNotification?.setLayoutManager(LinearLayoutManager(this))
//        ShowMessage("" + login_id)


        if (appUtils!!.isInternetConnected()) {
            Hit_getNotigicationList()
        } else {
            ShowMessage("No internet connection")
        }

        txt_CleanAll!!.setOnClickListener(View.OnClickListener {
            Hit_ClearNotigicationList()

        })

    }

    private fun Hit_getNotigicationList() {

        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.hit_getNotifications(constants!!.accesstoken, login_id)
        call?.enqueue(object : Callback<ServerResponseGetNotificationList?> {

            override fun onResponse(
                call: Call<ServerResponseGetNotificationList?>,
                response: Response<ServerResponseGetNotificationList?>
            ) {
                val data: ServerResponseGetNotificationList? = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {

//                    ShowMessage(""+data!!.getData()?.size)
                    if (data!!.getData()?.size != 0) {
                        no!!.visibility = View.GONE
                        recyclerViewNotification!!.visibility = View.VISIBLE
                        adapterNotification =
                            AdapterNotification(this@NotificationActivity, data!!.getData())
                        recyclerViewNotification!!.adapter = adapterNotification

                    } else {
                        no!!.visibility = View.VISIBLE
                        recyclerViewNotification!!.visibility = View.GONE
                    }
//


                } else {
                    appUtils!!.dialog!!.dismiss()

//                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseGetNotificationList?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })
    }

    private fun Hit_ClearNotigicationList() {

        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.hit_ClearNotifications(constants!!.accesstoken, login_id)
        call?.enqueue(object : Callback<ServerResponseClearNotification?> {

            override fun onResponse(
                call: Call<ServerResponseClearNotification?>,
                response: Response<ServerResponseClearNotification?>
            ) {
                val data: ServerResponseClearNotification? = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {

                    Hit_getNotigicationList()


                } else {
                    appUtils!!.dialog!!.dismiss()

//                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseClearNotification?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })
    }

    private fun ShowMessage(s: String) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show()
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