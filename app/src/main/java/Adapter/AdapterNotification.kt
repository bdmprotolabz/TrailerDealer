package Adapter

import Model.ServerResponseGetNotificationList
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.R

class AdapterNotification(
    var context: Context?,
    var data: List<ServerResponseGetNotificationList.Datum?>?
) : RecyclerView.Adapter<AdapterNotification.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txt_nottText: TextView = view.findViewById(R.id.txt_nottText)
        var txtNotiTime = view.findViewById<TextView>(R.id.txt_Notitime)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterNotification.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_notification, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: AdapterNotification.ViewHolder, position: Int) {
        holder.txtNotiTime.setText(data!!.get(position)!!.createdAt)
        holder.txt_nottText.setText(data!!.get(position)!!.notiMessage)

    }
}