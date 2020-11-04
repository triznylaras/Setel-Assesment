package mobile.setel.com.restaurantlist.presenter

import mobile.setel.com.restaurantlist.contract.RestaurantListContract
import mobile.setel.com.restaurantlist.model.RestaurantListData
import mobile.setel.com.restaurantlist.networking.HttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantPresenter(private val view: RestaurantListContract.View) : RestaurantListContract.Presenter {

    override fun getRestaurantList() {
        view.showLoading()

        HttpClient.getInstance().getApi()!!.getRestaurantList()
            .enqueue(object : Callback<RestaurantListData> {
                override fun onResponse(call: Call<RestaurantListData>?,
                                        response: Response<RestaurantListData>) {
                    val data: RestaurantListData? = response.body()
                    view.dismissLoading()
                    if (data != null) {
                        view.onSuccess(data)
                    }
                }

                override fun onFailure(call: Call<RestaurantListData>, t: Throwable) {
                    view.dismissLoading()
                    t.message?.let { view.onFailed(it) }
                }
            })
    }
}