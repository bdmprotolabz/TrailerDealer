package Fragment

import Adapter.RVProductAdapter
import Model.ServerResponseMyProducts
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

class FragmentProducts:Fragment() {
    var rv_product: RecyclerView?=null
    var txt_no_data: TextView?=null
    var adapter: RVProductAdapter?=null
    var animation: Animation? = null
    var constants: Constants?=null
    var appUtils: AppUtils? = null
    var preference: SharedPreferences? = null
    var login_id=""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_products, container, false)
        BindUI(view)
        constants= Constants()
        appUtils = activity?.let { AppUtils(it) }
        preference =activity?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        return view
    }

    private fun BindUI(view: View) {
        rv_product=view.findViewById(R.id.rv_product)
        txt_no_data=view.findViewById(R.id.txt_no_data)
        rv_product?.setLayoutManager(LinearLayoutManager(activity))
    }

    override fun onResume() {
        super.onResume()
        if (appUtils!!.isInternetConnected()) {
            Hitmy_productApi()
        } else {
            ShowMessage("Please check your Internet connection")
        }
    }

    private fun Hitmy_productApi() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.get_product(login_id,constants!!.accesstoken)

        call?.enqueue(object :Callback<ServerResponseMyProducts?>{

            override fun onResponse(call: Call<ServerResponseMyProducts?>, response: Response<ServerResponseMyProducts?>) {
                val data=response.body()
                appUtils!!.dialog!!.dismiss()
                if(data?.getCode().equals("200",true)){
                    if (data?.getData()?.size==0){
                        txt_no_data?.setVisibility(View.VISIBLE)
                        rv_product?.setVisibility(View.GONE)
                    }else{
                        txt_no_data?.setVisibility(View.GONE)
                        rv_product?.setVisibility(View.VISIBLE)
                        animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down)
                        rv_product?.setAnimation(animation)
                        adapter =RVProductAdapter(activity,data?.getData())
                        rv_product?.setAdapter(adapter)
                    }
                }else{
                    ShowMessage(data!!.getMessage().toString())
                    txt_no_data?.setVisibility(View.VISIBLE)
                    rv_product?.setVisibility(View.GONE)
                }
            }
            override fun onFailure(call: Call<ServerResponseMyProducts?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                rv_product?.setVisibility(View.GONE)
            }

        })

    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(activity, sms, Toast.LENGTH_LONG).show()
    }
}