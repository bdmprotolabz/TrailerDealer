package Adapter

import Model.ServerResponseAdList
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.thetrailerdelaler.R
import de.hdodenhof.circleimageview.CircleImageView

class RVAdsList(
    var context: Context?,
    var data: List<ServerResponseAdList.Datum?>?
) : RecyclerView.Adapter<RVAdsList.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var circularImg:CircleImageView=view.findViewById(R.id.img_ad)
        var txt_ad_name:TextView=view.findViewById(R.id.txt_ad_name)
        var txt_number_ofView:TextView=view.findViewById(R.id.txt_number_ofView)
        var txt_number_ofViewleft:TextView=view.findViewById(R.id.txt_number_ofViewleft)
        var txt_url:TextView=view.findViewById(R.id.txt_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdsList.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_ads, null, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
      return data!!.size
    }

    override fun onBindViewHolder(holder: RVAdsList.ViewHolder, position: Int) {


        context?.let { Glide.with(it).load(data!!.get(position)?.image).into(holder.circularImg) }
        holder.txt_ad_name.setText(data!!.get(position)?.adTitle)
        holder.txt_number_ofView.setText(data!!.get(position)?.totalClick)
        holder.txt_number_ofViewleft.setText(data!!.get(position)?.pendingClieck)
        holder.txt_url.setText(data!!.get(position)?.description)

    }
}