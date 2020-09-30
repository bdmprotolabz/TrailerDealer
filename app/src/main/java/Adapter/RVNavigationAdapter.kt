package Adapter

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.e.thetrailerdelaler.NavigationActivity
import com.e.thetrailerdelaler.NotificationActivity
import com.e.thetrailerdelaler.R

class RVNavigationAdapter(
    val context: Context,
    var navImage: TypedArray,
    var titles: MutableList<String>,
    var drawerLayout: DrawerLayout?
) :RecyclerView.Adapter<RVNavigationAdapter.RVHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVNavigationAdapter.RVHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_navigation_adapter,parent, false)
        return RVNavigationAdapter.RVHolder(view)
    }

    override fun getItemCount(): Int {

        return titles.size
    }

    override fun onBindViewHolder(holder: RVNavigationAdapter.RVHolder, position: Int) {
        holder.img_nav_icon.setImageResource(navImage.getResourceId(position, 0))
        holder.txt_nav_title.setText(titles[position])

        holder.ll_layout.setOnClickListener {
            drawerLayout?.closeDrawers()

            when(position){
                0->{
                    var intent = Intent(context, NavigationActivity::class.java)
                    intent.putExtra("type", "my location")
                    context.startActivity(intent)
                }
//                1->{
//                    var intent = Intent(context, NavigationActivity::class.java)
//                    intent.putExtra("type", "membership")
//                    context.startActivity(intent)
//                }
                1->{
                  /// share intent data implementing
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type="text/plain"
                   // shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.whatsapp&hl=en");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "The Trailer app link is cumming soon");
                    context.startActivity(Intent.createChooser(shareIntent,"Share via"))
                }
                2->{
                    var intent = Intent(context, NavigationActivity::class.java)
                    intent.putExtra("type", "Products")
                    intent.putExtra("id","")
                    context.startActivity(intent)
                }
                3->{
                    var intent = Intent(context, NavigationActivity::class.java)
                    intent.putExtra("type", "Account settings")
                    context.startActivity(intent)
                }
                4->{
                    var intent = Intent(context, NavigationActivity::class.java)
                    intent.putExtra("type", "Manage ads")
                    context.startActivity(intent)
                }
                5 -> {
                    var intent = Intent(context, NotificationActivity::class.java)
//                    intent.putExtra("type", "Notification")
//                    intent.putExtra("id", "")
//                    intent.putExtra("help", "")
//                    intent.putExtra("position", "100")
                    context.startActivity(intent)
                }

            }
        }

    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var img_nav_icon=itemView.findViewById(R.id.img_nav_icon)as ImageView
        var txt_nav_title = itemView.findViewById(R.id.txt_nav_title) as TextView
        var ll_layout=itemView.findViewById(R.id.ll_layout)as LinearLayout
    }

}