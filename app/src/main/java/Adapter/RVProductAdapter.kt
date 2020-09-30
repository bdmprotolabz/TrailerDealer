package Adapter

import Model.ServerResponseMyProducts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.thetrailerdelaler.R
import de.hdodenhof.circleimageview.CircleImageView

class RVProductAdapter(
    var activity: FragmentActivity?,
    var data: Array<ServerResponseMyProducts.Data?>?
) : RecyclerView.Adapter<RVProductAdapter.RVholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVholder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_products_adapter, null, false)
        return RVholder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RVholder, position: Int) {
        holder.txt_product_name.text = data?.get(position)?.product_name
        holder.txt_busines.text = data?.get(position)?.url
        var image: String = data?.get(position)?.img.toString()
        holder.txt_price.setText(data!!.get(position)?.price+"USD")
        if (!image.equals("", true)) {
            holder.img_product?.let {
                activity?.let { it1 ->
                    Glide.with(it1).load(image).placeholder(R.mipmap.default_user).into(it)
                }
            }
        }
        holder.txt_view.setOnClickListener {

        }

    }

    class RVholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_product = itemView.findViewById<CircleImageView>(R.id.img_product)
        var txt_product_name = itemView.findViewById<TextView>(R.id.txt_product_name)
        var txt_busines = itemView.findViewById<TextView>(R.id.txt_busines)
        var txt_view = itemView.findViewById<TextView>(R.id.txt_view)
        var txt_price = itemView.findViewById<TextView>(R.id.txt_price)
    }
}