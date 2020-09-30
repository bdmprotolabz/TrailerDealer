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
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.rilixtech.CountryCodePicker
import com.rilixtech.CountryCodePicker.OnCountryChangeListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    var btn_proceed: Button? = null
    var edt_number: EditText? = null
    var rl_facebook: RelativeLayout? = null
    var rl_gmail: RelativeLayout? = null
    var txt_signup: TextView? = null
    var ccp_number: CountryCodePicker? = null
    var mCode = "";
    var number = "";
    var appUtils: AppUtils? = null
    var constants: Constants? = null


    var token = ""
    var device_type = "a"

    companion object {
        const val CHANNEL_ID = "Trailer_Driver"
        private const val CHANNEL_NAME = "Trailer_Driver"
        private const val CHANNEL_DESC = "Trailer_Driver"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        BindUI()
        initCountryPicker()
        initView()
        appUtils = AppUtils(applicationContext)
        constants = Constants()

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

                Log.e("token", token)
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

        btn_proceed?.setOnClickListener(this)
        rl_facebook?.setOnClickListener(this)
        rl_gmail?.setOnClickListener(this)

    }

    private fun BindUI() {
        btn_proceed = findViewById(R.id.btn_proceed)
        edt_number = findViewById(R.id.edt_number)
        rl_facebook = findViewById(R.id.rl_facebook)
        rl_gmail = findViewById(R.id.rl_gmail)
        txt_signup = findViewById(R.id.txt_signup)
        ccp_number = findViewById(R.id.ccp_number)

    }

    private fun initView() {
        val ss = SpannableString(resources.getString(R.string.don_t_have_an_account_sign_up))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) { //startActivity(SignUpActivity.createIntent(LoginActivity.this).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                startActivity(Intent(applicationContext, SignUpActivity::class.java))
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@LoginActivity, R.color.color_red)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        txt_signup!!.text = ss
        txt_signup!!.movementMethod = LinkMovementMethod.getInstance()
        txt_signup!!.highlightColor = Color.TRANSPARENT
    }

    private fun initCountryPicker() {
        val manager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        mCode = manager.simCountryIso.toUpperCase()
        if (mCode.equals("", ignoreCase = true)) {
            ccp_number?.setDefaultCountryUsingNameCode("US")
            ccp_number?.resetToDefaultCountry()
        } else {
            ccp_number?.setDefaultCountryUsingNameCode(mCode)
            ccp_number?.resetToDefaultCountry()
        }
        mCode = ccp_number?.getSelectedCountryCodeWithPlus()!!
        ccp_number?.setOnCountryChangeListener(OnCountryChangeListener {
            mCode = ccp_number?.getSelectedCountryCodeWithPlus()!!
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_proceed -> {
                number = edt_number!!.text.toString().trim { it <= ' ' }
                if (number.isNotEmpty()) {
                    if (appUtils!!.isInternetConnected()) {
                        var phone_number = mCode + number
                        Hit_logingapi(phone_number)
                    } else {
                        ShowMessage("Please check your Internet connection")
                    }

                } else {
                    ShowMessage("Please enter phone number")
                }

                //  intent = Intent(applicationContext, OtpverifyActivity::class.java)
                //  startActivity(intent)
            }

            R.id.rl_facebook -> {


            }
            R.id.rl_gmail -> {


            }
        }

    }

    private fun Hit_logingapi(phoneNumber: String) {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.login(phoneNumber, constants!!.accesstoken, device_type, token)

        call!!.enqueue(object : Callback<ServerResponeLogin?> {

            override fun onResponse(
                call: Call<ServerResponeLogin?>,
                response: Response<ServerResponeLogin?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    ShowMessage(data?.getData()?.otp.toString())

//                        Log.e("success", data?.getData()?.otp)


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
