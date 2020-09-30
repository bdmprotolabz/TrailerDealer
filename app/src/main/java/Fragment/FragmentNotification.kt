package Fragment

import Adapter.AdapterNotification
import Model.ServerResponseClearNotification
import Model.ServerResponseGetNotificationList
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentNotification(var txtCleanall: TextView) : Fragment() {

    var recyclerViewNotification: RecyclerView? = null
    var adapterNotification: AdapterNotification? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var constants: Constants? = null
    var login_id = "";
    var appUtils: AppUtils? = null
    var no: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_notification, container, false)

        no = view.findViewById(R.id.no)
        constants = Constants()
        appUtils = AppUtils(context!!)
        appUtils!!.showDialog(context)

        preference = context!!.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)


        login_id = preference!!.getString("loginid", "")!!

        recyclerViewNotification = view.findViewById(R.id.recycler_Notification)
        recyclerViewNotification?.setLayoutManager(LinearLayoutManager(activity))
//        ShowMessage("" + login_id)


        if (appUtils!!.isInternetConnected()) {
            Hit_getNotigicationList()
        } else {
            ShowMessage("No internet connection")
        }

        txtCleanall.setOnClickListener(View.OnClickListener {
            Hit_ClearNotigicationList()

        })

        return view
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
                        adapterNotification = AdapterNotification(context, data!!.getData())
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
        Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show()
    }

}