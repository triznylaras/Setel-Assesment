package mobile.setel.com.restaurantlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobile.setel.com.restaurantlist.R
import mobile.setel.com.restaurantlist.model.RestaurantListData
import net.cachapa.expandablelayout.ExpandableLayout


class AdapterRestaurantList(private val mData: RestaurantListData,
                            private val listener: ItemClicked)
    : RecyclerView.Adapter<AdapterRestaurantList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRestaurantList.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_restaurant_list, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.restaurantList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView()
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView() {
            val data = mData.restaurantList!!.get(adapterPosition)

            val name = itemView.findViewById<TextView>(R.id.tv_restaurant_name)
            val status = itemView.findViewById<TextView>(R.id.tv_status)
            val hours = itemView.findViewById<TextView>(R.id.tv_operating_hours)
            val expandLayout = itemView.findViewById<ExpandableLayout>(R.id.expand_layout)
            val ll = itemView.findViewById<LinearLayout>(R.id.ll)
            val icon = itemView.findViewById<ImageView>(R.id.ic_expand)

            name.text = data.restaurantName
            hours.text = data.operatingHours
            status.text = "Open"

            itemView.setOnClickListener {
//                listener.onItemClicked(data.restaurantName!!)
//                expandLayout.isExpanded = true

                if (expandLayout.isExpanded) {
//                    icon.setImageResource(R.drawable.ic_arrow_down)
                    expandLayout.collapse()
                } else {
//                    icon.setImageResource(R.drawable.ic_arrow_top)
                    expandLayout.expand()

                    data.isClicked = true
                }
            }
        }
    }

    interface ItemClicked{
        fun onItemClicked(name : String)
    }
}
