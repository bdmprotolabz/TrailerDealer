package Fragment

import Adapter.RVMemberShipAdapter
import Adapter.RVMemberShipAdapter.ClickPay
import Model.ServerResponseMebership
import Service.Interface
import Utils.AppUtils
import Utils.RetrofitClient
import android.content.Intent
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
import com.e.thetrailerdelaler.PaymentActivity
import com.e.thetrailerdelaler.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMembership : Fragment(), ClickPay {
    var rv_membership: RecyclerView? = null
    var txt_no_data: TextView? = null
    var adapter: RVMemberShipAdapter? = null
    var animation: Animation? = null
    var appUtils: AppUtils? = null
    var clickPay: ClickPay? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_membership, container, false)
        BindUI(view)
        appUtils = activity?.let { AppUtils(it) }

        clickPay = this
        if (appUtils!!.isInternetConnected()) {
            HitGet_membership()
        } else {
            ShowMessage("Please check your Internet connection")
        }

        return view
    }

    private fun HitGet_membership() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.get_membership()
        call?.enqueue(object : Callback<ServerResponseMebership?> {

            override fun onResponse(
                call: Call<ServerResponseMebership?>,
                response: Response<ServerResponseMebership?>
            ) {
                val data: ServerResponseMebership? = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    if (data?.getData()?.size == 0) {
                        txt_no_data?.setVisibility(View.VISIBLE)
                        rv_membership?.setVisibility(View.GONE)

                    } else {
                        txt_no_data?.setVisibility(View.GONE)
                        rv_membership?.setVisibility(View.VISIBLE)
                        animation =
                            AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down)
                        rv_membership?.setAnimation(animation)
                        adapter = RVMemberShipAdapter(activity, data?.getData(),clickPay)
                        rv_membership?.setAdapter(adapter)
                    }

                } else {
                    ShowMessage(data!!.getMessage().toString())
                    txt_no_data?.setVisibility(View.VISIBLE)
                    rv_membership?.setVisibility(View.GONE)

                }
            }

            override fun onFailure(call: Call<ServerResponseMebership?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                rv_membership?.setVisibility(View.GONE)

            }
        })

    }

    private fun BindUI(view: View) {
        rv_membership = view.findViewById(R.id.rv_membership)
        txt_no_data = view.findViewById(R.id.txt_no_data)
        rv_membership?.setLayoutManager(LinearLayoutManager(activity))

    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(activity, sms, Toast.LENGTH_LONG).show()
    }

    override fun onClickPay(position: Int, price: String?) {

        var intent=Intent(context,PaymentActivity::class.java)
        intent.putExtra("price",price)
        startActivity(intent)
    }

}