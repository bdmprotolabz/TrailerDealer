package Adapter

import Model.ServerResponseMebership
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.R

class RVMemberShipAdapter(
    var activity: FragmentActivity?,
    var data: List<ServerResponseMebership.Data?>?,
    var clickPay: ClickPay?
) : RecyclerView.Adapter<RVMemberShipAdapter.RVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_membership_adapter, parent, false)
        return RVHolder(view)
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.txt_type.text = data?.get(position)?.type.toString() + " Subscription"
        holder.txt_price.text = "Price : $ " + data?.get(position)?.price.toString()

        var price = data!!.get(position)?.price
        holder.btn_pay.setOnClickListener {
            clickPay!!.onClickPay(position, price)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }


    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_type = itemView.findViewById<TextView>(R.id.txt_type)
        var txt_price = itemView.findViewById<TextView>(R.id.txt_price)
        var btn_pay = itemView.findViewById<Button>(R.id.btn_pay)

    }

    interface ClickPay {
        fun onClickPay(position: Int, price: String?)

    }

}