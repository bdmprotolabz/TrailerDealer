package com.e.thetrailerdelaler

import Adapter.RVAllReviewAdapter
import Model.ServerResponseAllReview
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_navigation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllReviewActivity : AppCompatActivity() {
    var preference: SharedPreferences? = null
    var constants: Constants?=null
    var appUtils: AppUtils? = null
    var login_id = "";var driver_id="";var location_id="";var title=""
    var txt_no_data:TextView?=null
    var rv_allreview:RecyclerView?=null
    var adapter:RVAllReviewAdapter?=null
    var animation: Animation? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_review)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        BindUI()
        constants= Constants()
        appUtils = AppUtils(applicationContext)
        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!
        location_id=intent.getStringExtra("location_id")

        if (appUtils!!.isInternetConnected()) {
            Hit_AllReviwApi()
        } else {
            ShowMessage("Please check your Internet connection")
        }

    }

    private fun BindUI() {
        txt_no_data=findViewById(R.id.txt_no_data)
        rv_allreview=findViewById(R.id.rv_allreview)
        rv_allreview?.setLayoutManager(LinearLayoutManager(this))

    }
    private fun Hit_AllReviwApi() {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.reviews(login_id,location_id,constants!!.accesstoken)
        call?.enqueue(object :Callback<ServerResponseAllReview?>{

            override fun onResponse(call: Call<ServerResponseAllReview?>, response: Response<ServerResponseAllReview?>) {
                val data=response.body()
                appUtils!!.dialog!!.dismiss()
                if(data?.getCode().equals("200",true)){
                    if (data?.getData()?.size==0){
                        txt_no_data?.setVisibility(View.VISIBLE)
                        rv_allreview?.setVisibility(View.GONE)
                    }else{
                        txt_no_data?.setVisibility(View.GONE)
                        rv_allreview?.setVisibility(View.VISIBLE)
                        animation = AnimationUtils.loadAnimation(this@AllReviewActivity, R.anim.item_animation_fall_down)
                        rv_allreview?.setAnimation(animation)
                        adapter = RVAllReviewAdapter(this@AllReviewActivity,data?.getData())
                        rv_allreview?.setAdapter(adapter)
                    }
                }else{
                    ShowMessage(data!!.getMessage().toString())
                    txt_no_data?.setVisibility(View.VISIBLE)
                    rv_allreview?.setVisibility(View.GONE)
                }
            }
            override fun onFailure(call: Call<ServerResponseAllReview?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                rv_allreview?.setVisibility(View.GONE)
            }
        })

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

    private fun ShowMessage(sms: String) {
        Toast.makeText(applicationContext, sms, Toast.LENGTH_LONG).show()
    }
}
