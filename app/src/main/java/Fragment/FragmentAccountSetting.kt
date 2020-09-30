package Fragment

import Model.ServerResponseLogout
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.thetrailerdelaler.LoginActivity
import com.e.thetrailerdelaler.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAccountSetting : Fragment(), View.OnClickListener {
    var rl_signout: RelativeLayout? = null
    var txt_name: TextView? = null;
    var txt_number: TextView? = null;
    var txt_version: TextView? = null;
    var appUtils: AppUtils? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var constants: Constants? = null
    var login_id = ""
    var help: RelativeLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_account_settings, container, false)
        BindUI(view)
        appUtils = activity?.let { AppUtils(it) }
        constants = Constants()
        preference = activity?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!
        var name: String? = preference!!.getString("name", "")!!
        var last_name: String = preference!!.getString("last_name", "")!!
        var contact: String? = preference!!.getString("contact", "")!!
        txt_name?.setText(name + " " + last_name)
        txt_number?.setText(contact)
        val manager = context!!.packageManager
        val info = manager.getPackageInfo(context!!.packageName, 0)
        val version = info.versionName
        txt_version?.setText("App version " + version)

        rl_signout?.setOnClickListener(this)
        help!!.setOnClickListener(this)
        return view
    }

    private fun BindUI(view: View) {
        rl_signout = view.findViewById(R.id.rl_signout)
        txt_name = view.findViewById(R.id.txt_name)
        txt_number = view.findViewById(R.id.txt_number)
        txt_version = view.findViewById(R.id.txt_version)
        help=view.findViewById(R.id.help)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.rl_signout -> {
                if (appUtils!!.isInternetConnected()) {
                    Hit_logoutapi()
                } else {
                    ShowMessage("Please check your Internet connection")
                }

            }R.id.help->{

            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts("mailto", "thetrailerapp@gmail.com", null)
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text")
            context!!.startActivity(Intent.createChooser(emailIntent, null))
        }
        }
    }

    private fun Hit_logoutapi() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.logout(login_id, constants!!.accesstoken)
        call?.enqueue(object : Callback<ServerResponseLogout?> {

            override fun onResponse(
                call: Call<ServerResponseLogout?>,
                response: Response<ServerResponseLogout?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    // ShowMessage("Your account successfully logout ")

                    editor = preference!!.edit()
                    editor!!.clear()
                    editor!!.commit()
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    ShowMessage(data?.getMessage().toString())
                }

            }

            override fun onFailure(call: Call<ServerResponseLogout?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }
        })
    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(activity, sms, Toast.LENGTH_LONG).show()
    }
}