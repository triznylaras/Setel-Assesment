package mobile.setel.com.restaurantlist.adapter

import android.content.Context
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobile.setel.com.restaurantlist.R
import mobile.setel.com.restaurantlist.base.recycler.CoreRecyclerAdapter
import mobile.setel.com.restaurantlist.model.RestaurantData
import net.cachapa.expandablelayout.ExpandableLayout
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


class AdapterRestaurantList(context: Context, private var currentDateTime: Int)
    : CoreRecyclerAdapter<RestaurantData, AdapterRestaurantList.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRestaurantList.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return if (viewType == ITEM) getViewHolder(parent, inflater)
        else{
            val v2 = inflater.inflate(R.layout.item_progress, parent, false)
            LoadingVH(v2)
        }
    }

    private inner class LoadingVH(itemView: View) : AdapterRestaurantList.ViewHolder(itemView)

    private fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater): AdapterRestaurantList.ViewHolder {
        val v1 = inflater.inflate(R.layout.item_restaurant_list, parent, false)
        return ViewHolder(v1)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mData.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun setContent(content: ArrayList<RestaurantData>) {
        mData.clear()
        mData.addAll(content)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AdapterRestaurantList.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM){
            (holder as ViewHolder).bindView(mData[position])
            animateViewHolder(holder, holder.adapterPosition)
        }
    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(data: RestaurantData) {
            val name = itemView.findViewById<TextView>(R.id.tv_restaurant_name)
            val status = itemView.findViewById<TextView>(R.id.tv_status)
            val hours = itemView.findViewById<TextView>(R.id.tv_operating_hours)
            val expandLayout = itemView.findViewById<ExpandableLayout>(R.id.expand_layout)
            val icon = itemView.findViewById<ImageView>(R.id.ic_expand)

            val dayOfWeek: ArrayList<String>? = null

            dayOfWeek?.add("Sun")
            dayOfWeek?.add("Mon")
            dayOfWeek?.add("Tue")
            dayOfWeek?.add("Wed")
            dayOfWeek?.add("Thu")
            dayOfWeek?.add("Fri")
            dayOfWeek?.add("Sat")

            var splitDayDate = data.operatingHours?.split("-")

//            if (splitDayDate[0] && splitDayDate[1])

            name.text = data.restaurantName
            if (data.operatingHours!!.contains("/")) {
                data.operatingHours?.replace("/", "\n")
                hours.text = data.operatingHours
            } else {
                hours.text = data.operatingHours
            }

            val cal: Calendar = Calendar.getInstance()
            cal.setTimeInMillis(currentDateTime * 1000L)
            val currentDate: String = DateFormat.format("EE, d MMM yyyy", cal).toString()
            val currentTime: String = DateFormat.format("hh:mm aa", cal).toString()

            if (currentTime == "11:59 PM") {
                status.text = "Closed"
            }

            if (currentDate != data.operatingHours?.substring(0, 2)) {
                status.text = "Closed"
            }

            itemView.setOnClickListener {
                if (expandLayout.isExpanded) {
//                    icon.setImageResource(R.drawable.ic_arrow_down)
                    expandLayout.collapse()
                } else {
//                    icon.setImageResource(R.drawable.ic_arrow_top)
                    expandLayout.expand()
                }
            }
        }
    }
}
