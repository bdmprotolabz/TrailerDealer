package com.e.thetrailerdelaler

import Model.ServerResponeLogin
import Model.ServerResponseOtp
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mukesh.OnOtpCompletionListener
import com.mukesh.OtpView
import kotlinx.android.synthetic.main.activity_otpverify.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OtpverifyActivity : AppCompatActivity(),View.OnClickListener {

    var otpView: OtpView? = null

    var btn_verify: Button? = null
    var txt_resend: TextView? = null; var txt_timer:TextView? = null
    var phone_number:String? = ""; var otp_number:String? = ""; var device_id:String? = ""; var device_type:String? = "1"
    var appUtils: AppUtils? = null
    var constants: Constants?=null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    var token = ""
    var device_types = "a"

    companion object {
        const val CHANNEL_ID = "Trailer_Driver"
        private const val CHANNEL_NAME = "Trailer_Driver"
        private const val CHANNEL_DESC = "Trailer_Driver"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverify)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        BindUI()
        appUtils = AppUtils(applicationContext)
        constants= Constants()
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                //hiding the progressbar
//                progressbar.visibility = View.INVISIBLE

                if (!task.isSuccessful) {
                    //displaying the error if the task is unsuccessful
//                    textViewToken.text = task.exception?.message

                    //stopping the further execution
                    return@OnCompleteListener
                }

                //Getting the token if everything is fine
                token = task.result?.token!!

//                ShowMessage("" + token)
//                textViewMessage.text = "Your FCM Token is:"
//                textViewToken.text = token

            })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESC
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)


        phone_number = intent.getStringExtra("number")

        otpView!!.setOtpCompletionListener(object :OnOtpCompletionListener
        {
            override fun onOtpCompleted(otp: String?) {
               Log.e("opt",otp)
                otp_number=otp;
            }

        })
        btn_verify?.setOnClickListener(this)
        txt_resend?.setOnClickListener(this)

        //////////set timer
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txt_resend!!.isEnabled = false
                txt_timer!!.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                txt_resend!!.isEnabled = true
                txt_timer!!.visibility = View.GONE
                txt_resend!!.text = "Didn't receive a text message? Resend code"
            }
        }.start()


    }


    private fun BindUI() {
        otpView=findViewById(R.id.otp_view);
        btn_verify = findViewById(R.id.btn_verify)
        txt_resend = findViewById(R.id.txt_resend)
        txt_timer = findViewById(R.id.txt_timer)

    }
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_verify ->{
             if (!otp_number.isNullOrEmpty()){

                    if (appUtils!!.isInternetConnected()) {
                        Hit_Otpverfyapi()
                    } else {
                        ShowMessage("Please check your Internet connection")
                    }
             }else{
                    ShowMessage("Please enter otp code")

              }
            }

            R.id.txt_resend -> {
                if (appUtils!!.isInternetConnected()) {
                    Hit_resendcode(phone_number.toString())
                } else {
                    ShowMessage("Please check your Internet connection")
                }

            }

        }

    }

    private fun Hit_Otpverfyapi() {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient:RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.verify_otp(phone_number, otp_number,device_types,token, constants!!.accesstoken)
        call!!.enqueue(object : retrofit2.Callback<ServerResponseOtp?> {

            override fun onResponse(call: Call<ServerResponseOtp?>, response: Response<ServerResponseOtp?>) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                    editor = preference!!.edit()
                    editor!!.putString("name",data!!.getData()!!.name);
                    editor!!.putString("email",data!!.getData()!!.email);
                    editor!!.putString("contact", data!!.getData()!!.contact)
                    editor!!.putString("loginid", data!!.getData()!!.id)
                    editor!!.putString("image", data!!.getData()!!.driverImg)
                    editor!!.putString("device_id", data!!.getData()!!.device_token)
                    editor!!.putString("device_type", data!!.getData()!!.devicetype)
                    editor!!.commit()

                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finishAffinity()

                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseOtp?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }
        })

    }

    private fun Hit_resendcode(phoneNumber: String) {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient:RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.login(phoneNumber,constants!!.accesstoken,device_types,token)

        call!!.enqueue(object : Callback<ServerResponeLogin?> {

            override fun onResponse(call: Call<ServerResponeLogin?>, response: Response<ServerResponeLogin?>) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                    ShowMessage(data?.getData()?.otp.toString())
//                    Log.e("success", data?.getData()?.otp)
                    //////////set timer
                    //////////set timer
                    object : CountDownTimer(60000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            txt_timer!!.visibility = View.VISIBLE
                            txt_resend!!.isEnabled = false
                            txt_resend!!.text = "Didn't receive a text message? Resend code"
                            txt_timer!!.text = (millisUntilFinished / 1000).toString()
                        }

                        override fun onFinish() {
                            txt_timer!!.visibility = View.GONE
                            txt_resend!!.isEnabled = true
                            txt_resend!!.text = "Didn't receive a text message? Resend code"
                        }
                    }.start()

                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponeLogin?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
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


