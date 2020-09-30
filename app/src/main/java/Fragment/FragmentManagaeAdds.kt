package Fragment

import Adapter.RVAdsList
import Model.ServerResponseAdList
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.NavigationActivity
import com.e.thetrailerdelaler.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentManagaeAdds(var rlAdd: RelativeLayout) : Fragment() {
    var recyclerView: RecyclerView? = null
    var constants: Constants? = null
    var appUtils: AppUtils? = null
    var preference: SharedPreferences? = null
    var login_id = ""
    var txt_no_data: TextView? = null
    var animation: Animation? = null
    var rvAdsList:RVAdsList?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_managae_adds, container, false)

        constants = Constants()
        appUtils = activity?.let { AppUtils(it) }
        preference = activity?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!
        txt_no_data = view.findViewById(R.id.txt_no_data)
        recyclerView = view.findViewById(R.id.rv_ads)
        recyclerView?.setLayoutManager(LinearLayoutManager(activity))

        Hitmy_Ads()

        rlAdd.setOnClickListener(View.OnClickListener {

            var intent = Intent(context, NavigationActivity::class.java)
            intent.putExtra("type", "add ads")
            startActivity(intent)

        })



        return view
    }

    private fun Hitmy_Ads() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.get_AdList(constants!!.accesstoken, login_id)

        call?.enqueue(object : Callback<ServerResponseAdList?> {

            override fun onResponse(
                call: Call<ServerResponseAdList?>,
                response: Response<ServerResponseAdList?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    if (data?.getData()?.size == 0) {
                        txt_no_data?.setVisibility(View.VISIBLE)
                        recyclerView?.setVisibility(View.GONE)
                    } else {
                        txt_no_data?.setVisibility(View.GONE)
                        recyclerView?.setVisibility(View.VISIBLE)
                        animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down)
                        recyclerView?.setAnimation(animation)
                        rvAdsList = RVAdsList(context,data!!.getData())
                        recyclerView?.setAdapter(rvAdsList)
                    }
                } else {
                    ShowMessage(data!!.getMessage().toString())
                    txt_no_data?.setVisibility(View.VISIBLE)
                    recyclerView?.setVisibility(View.GONE)
                }
            }

            override fun onFailure(call: Call<ServerResponseAdList?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                recyclerView?.setVisibility(View.GONE)
            }

        })

    }

    private fun ShowMessage(s: String) {
        Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show()
    }

}