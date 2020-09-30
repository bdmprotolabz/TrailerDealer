package com.e.thetrailerdelaler

import Model.ServerResponeLogin
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.rilixtech.CountryCodePicker
import kotlinx.android.synthetic.main.activity_sign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    var ccp:CountryCodePicker? = null
    var btn_signup: Button? = null
    var edt_number: EditText? = null
    var txt_signin: TextView?=null
    var mCode = "";var number=""
    var appUtils: AppUtils? = null
    var constants: Constants?=null



    var token = ""
    var device_type = "a"

    companion object {
        const val CHANNEL_ID = "Trailer_Driver"
        private const val CHANNEL_NAME = "Trailer_Driver"
        private const val CHANNEL_DESC = "Trailer_Driver"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        BindUI()
        initCountryPicker()
        initView()
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

        btn_signup?.setOnClickListener(View.OnClickListener {
            number = edt_number!!.text.toString().trim { it <= ' ' }
            if(number.isNotEmpty()){
                if (appUtils!!.isInternetConnected()) {
                    var  phone_number = mCode + number
                    Hit_signUpapi(phone_number)
                } else {
                    ShowMessage("Please check your Internet connection")
                }
            }else{
                ShowMessage("Please enter phone number")
            }
     })

    }


    private fun initView() {
        val ss = SpannableString(resources.getString(R.string.already_have_an_account_login))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) { //startActivity(SignUpActivity.createIntent(LoginActivity.this).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finishAffinity()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@SignUpActivity, R.color.color_red)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 24, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        txt_signin!!.text = ss
        txt_signin!!.movementMethod = LinkMovementMethod.getInstance()
        txt_signin!!.highlightColor = Color.TRANSPARENT
    }
    private fun initCountryPicker() {
        val manager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        mCode = manager.simCountryIso.toUpperCase()
        if (mCode.equals("", ignoreCase = true)) {
            ccp?.setDefaultCountryUsingNameCode("US")
            ccp?.resetToDefaultCountry()
        } else {
            ccp?.setDefaultCountryUsingNameCode(mCode)
            ccp?.resetToDefaultCountry()
        }
        mCode = ccp?.getSelectedCountryCodeWithPlus()!!
        ccp?.setOnCountryChangeListener(CountryCodePicker.OnCountryChangeListener {
            mCode = ccp?.getSelectedCountryCodeWithPlus()!!
        })
    }

    private fun BindUI() {
        btn_signup=findViewById(R.id.btn_signup)
        edt_number=findViewById(R.id.edt_number)
        txt_signin=findViewById(R.id.txt_signin)
        ccp=findViewById(R.id.ccp)

    }
    private fun Hit_signUpapi(phoneNumber: String) {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient:RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.signup(phoneNumber,constants!!.accesstoken,device_type,token)

        call!!.enqueue(object : Callback<ServerResponeLogin?> {

            override fun onResponse(call: Call<ServerResponeLogin?>, response: Response<ServerResponeLogin?>) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                    ShowMessage(data?.getData()?.otp.toString())
                    Log.e("success", data?.getData()?.otp)
                    val intent = Intent(applicationContext, OtpverifyActivity::class.java)
                    intent.putExtra("number", phoneNumber)
                    startActivity(intent)

                } else {
                    ShowMessage(data?.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponeLogin?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }
        })

    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(applicationContext, sms, Toast.LENGTH_LONG).show()
    }

}
