package mobile.setel.com.restaurantlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobile.setel.com.restaurantlist.model.RestaurantListData
import mobile.setel.com.restaurantlist.adapter.AdapterRestaurantList
import mobile.setel.com.restaurantlist.model.RestaurantData

class MainActivity : AppCompatActivity(), AdapterRestaurantList.ItemClicked {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val mRestaurantList = RestaurantListData()
    private val mRestaurantData = ArrayList<RestaurantData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterRestaurantList(RestaurantListData(
            mRestaurantData, 1604386422), this)

        recyclerView = findViewById<RecyclerView>(R.id.rv_restaurant_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun initData(){
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
        mRestaurantData.add(RestaurantData("Bariuma Ramen",
            "Open", "Mon-Sun 11:30 am - 9:00 pm"))
    }

    override fun onItemClicked(name: String) {

    }
}