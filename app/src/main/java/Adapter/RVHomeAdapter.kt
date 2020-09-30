package Adapter

import Model.ServerResponseHome
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.thetrailerdelaler.HomeActivity
import com.e.thetrailerdelaler.R
import de.hdodenhof.circleimageview.CircleImageView

class RVHomeAdapter(var homeActivity: HomeActivity, var  data: Array<ServerResponseHome.Data?>?) : RecyclerView.Adapter<RVHomeAdapter.RVHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_adapter_home,parent, false)
        return RVHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.txt_driver_name.text=data?.get(position)?.name
        holder.txt_visit.text="Visited :"+data?.get(position)?.review+" times"
        holder.txt_location.text="Location :"+data?.get(position)?.location
        var image:String= data?.get(position)?.image.toString()
        if (!image.equals("",true)){
            holder.img_user_image?.let { Glide.with(homeActivity).load(image).placeholder(R.mipmap.default_user).into(it) }
        }

    }
    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_user_image=itemView.findViewById<CircleImageView>(R.id.img_user_image)
        var txt_driver_name=itemView.findViewById<TextView>(R.id.txt_driver_name)
        var txt_visit=itemView.findViewById<TextView>(R.id.txt_visit)
        var txt_location=itemView.findViewById<TextView>(R.id.txt_location)


    }
}