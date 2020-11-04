package mobile.setel.com.restaurantlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.format.DateFormat
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import mobile.setel.com.restaurantlist.adapter.AdapterRestaurantList
import mobile.setel.com.restaurantlist.base.BaseActivity
import mobile.setel.com.restaurantlist.base.recycler.AdapterAnimationHelper
import mobile.setel.com.restaurantlist.contract.RestaurantListContract
import mobile.setel.com.restaurantlist.model.RestaurantListData
import mobile.setel.com.restaurantlist.presenter.RestaurantPresenter
import mobile.setel.com.restaurantlist.utils.Helpers
import mobile.setel.com.restaurantlist.utils.PaginationScrollListener
import java.util.*


class MainActivity : BaseActivity(),
    RestaurantListContract.View {

    private lateinit var adapter: AdapterRestaurantList
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvCurrentDate: TextView

    private var isLoad = false
    private var isLast = false
    private val limitData = 10
    private var currentPage = 1
    private var totalPages = 0

    private var presenter: RestaurantListContract.Presenter = RestaurantPresenter(this)
    private var loading : MaterialDialog? = null

    override val viewRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RestaurantPresenter(this)
        presenter.getRestaurantList()
        loading = Helpers.getMaterialProgressDialog(this)
    }

    private fun resetData(){
        isLoad = false
        isLast = false
        currentPage = 1
        totalPages = 0
    }

    override fun onSuccess(data: RestaurantListData) {
        tvCurrentDate = findViewById(R.id.tv_current_date)
        val cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(data.time!! * 1000L)
        val date: String = DateFormat.format("EE, d MMM yyyy", cal).toString()
        tvCurrentDate.text = date

        adapter = AdapterRestaurantList(this, data.time!!)
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView = findViewById(R.id.rv_restaurant_list)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
        data.restaurantList?.let { adapter.setContent(it) }
        if (data.restaurantList!!.size > 0 && data.restaurantList!!.size == limitData) {
            adapter.addLoadingFooter()
        }
        else isLast = true
        recyclerView.adapter = adapter
        AdapterAnimationHelper().attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun totalPageCount() = getTotalPage()

            override fun isLastPage() = getIsLast()

            override fun isLoading() = getIsLoad()

            override fun loadMoreItems() {
                isLoad = true
                currentPage += 1

                Handler().postDelayed({ presenter.getRestaurantList() }, 1000)
            }
        })
    }

    private fun getIsLoad(): Boolean{
        return isLoad
    }

    private fun getIsLast(): Boolean{
        return isLast
    }

    private fun getTotalPage(): Int{
        return totalPages
    }

    override fun onFailed(message: String) {
        Helpers.showError(this, message)
    }

    override fun showLoading() {
        loading?.show()
    }

    override fun dismissLoading() {
        loading?.dismiss()
    }

    override fun showMessage(message: String) {
        Helpers.showAlert(this, message, true)
    }

    companion object {
        /**
         * Launch this activity.
         * @param context the context
         */
        fun launchIntent(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}