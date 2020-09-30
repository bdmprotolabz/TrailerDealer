package Adapter

import Model.ServerResponseMylocation
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.thetrailerdelaler.AllReviewActivity
import com.e.thetrailerdelaler.R
import de.hdodenhof.circleimageview.CircleImageView

class RVMyLocationAdapter(
    var activity: FragmentActivity?,
    var data: Array<ServerResponseMylocation.Data?>?
) :RecyclerView.Adapter<RVMyLocationAdapter.RVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_mylocation_adapter,parent, false)
         return RVHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.txt_title.text=data?.get(position)?.location_title
        holder.txt_location.text="Location: "+data?.get(position)?.location
        holder.txt_review.text=data?.get(position)?.totel+"  reviews"

        var image:String= data?.get(position)?.image.toString()
        if (!image.equals("",true)){
            holder.img_location?.let { activity?.let { it1 -> Glide.with(it1).load(image).placeholder(R.mipmap.default_user).into(it) } }
        }
        holder.rl_viewreview.setOnClickListener {
            var intent=Intent(activity,AllReviewActivity::class.java)
            intent.putExtra("location_id",data?.get(position)?.id)
            activity?.startActivity(intent)
        }
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_location=itemView.findViewById<CircleImageView>(R.id.img_location)
        var txt_title=itemView.findViewById<TextView>(R.id.txt_title)
        var txt_location=itemView.findViewById<TextView>(R.id.txt_location)
        var txt_review=itemView.findViewById<TextView>(R.id.txt_review)
        var rl_viewreview=itemView.findViewById<RelativeLayout>(R.id.rl_viewreview)

    }
}