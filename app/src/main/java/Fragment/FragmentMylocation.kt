package Fragment

import Adapter.RVMyLocationAdapter
import Model.ServerResponseMylocation
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
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMylocation : Fragment() {
    var rv_mylocation:RecyclerView?=null
    var txt_no_data:TextView?=null
    var adapter:RVMyLocationAdapter?=null
    var animation: Animation? = null
    var preference: SharedPreferences? = null
    var appUtils: AppUtils? = null
    var constants: Constants?=null
    var login_id = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_mylocation, container, false)
        BindUI(view)
        //(activity as NavigationActivity?)!!.setTitle("Hello World!")
        constants= Constants()
        appUtils = activity?.let { AppUtils(it) }
        preference = activity?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        return view
    }

    private fun BindUI(view: View) {
        rv_mylocation=view.findViewById(R.id.rv_mylocation)
        txt_no_data=view.findViewById(R.id.txt_no_data)
        rv_mylocation?.setLayoutManager(LinearLayoutManager(activity))

    }

    override fun onResume() {
        super.onResume()
        if (appUtils!!.isInternetConnected()) {
            HitGet_MylocationApi()
        } else {
            ShowMessage("Please check your Internet connection")
        }
    }

    private fun HitGet_MylocationApi() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.my_location_list(login_id,constants!!.accesstoken)

        call?.enqueue(object : Callback<ServerResponseMylocation?>{

            override fun onResponse(call: Call<ServerResponseMylocation?>, response: Response<ServerResponseMylocation?>) {
                val data=response.body()
                appUtils!!.dialog!!.dismiss()
               if(data?.getCode().equals("200",true)){
                   if (data?.getData()?.size==0){
                       txt_no_data?.setVisibility(View.VISIBLE)
                       rv_mylocation?.setVisibility(View.GONE)
                   }else{
                       txt_no_data?.setVisibility(View.GONE)
                       rv_mylocation?.setVisibility(View.VISIBLE)
                       animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down)
                       rv_mylocation?.setAnimation(animation)
                       adapter = RVMyLocationAdapter(activity,data?.getData())
                       rv_mylocation?.setAdapter(adapter)
                   }
               }else{
                   ShowMessage(data!!.getMessage().toString())
                   txt_no_data?.setVisibility(View.VISIBLE)
                   rv_mylocation?.setVisibility(View.GONE)
               }
            }
            override fun onFailure(call: Call<ServerResponseMylocation?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                rv_mylocation?.setVisibility(View.GONE)
            }

        })

    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(activity, sms, Toast.LENGTH_LONG).show()
    }
}