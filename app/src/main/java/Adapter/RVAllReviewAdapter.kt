package Adapter

import Model.ServerResponseAllReview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.AllReviewActivity
import com.e.thetrailerdelaler.R

class RVAllReviewAdapter(
   var allReviewActivity: AllReviewActivity,
   var data: Array<ServerResponseAllReview.Data?>?
) :RecyclerView.Adapter<RVAllReviewAdapter.RVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_allreview_adapter,parent, false)
        return RVHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.txt_driver_name.text=data?.get(position)?.name
        holder.txt_review.text=data?.get(position)?.review
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_driver_name=itemView.findViewById<TextView>(R.id.txt_driver_name)
        var txt_review=itemView.findViewById<TextView>(R.id.txt_review)

    }
}