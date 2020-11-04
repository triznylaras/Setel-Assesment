package mobile.setel.com.restaurantlist.presenter//package mobile.setel.com.restaurantlist.presenter
//
//import android.content.Context
//import mobile.setel.com.restaurantlist.api.APIService
//import mobile.setel.com.restaurantlist.model.RestaurantListData
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class RestaurantListPresenter(val context: Context) {
//
//    private val apiService = APIService
//
//    private val restaurantView: RestaurantPresenter? = null
//
//    fun getRestaurantList() {
//        apiService.getAPIInterface()
//            .getRestaurantList()
//            .enqueue(object : Callback<RestaurantListData> {
//                override fun onResponse(call: Call<RestaurantListData>?,
//                                        response: Response<RestaurantListData>) {
//                    val data: RestaurantListData? = response.body()
//                    if (data != null) {
//                        restaurantView?.onSuccess(data)
//                    }
//                }
//
//                override fun onFailure(call: Call<RestaurantListData>, t: Throwable) {
//                    try {
//                        throw InterruptedException("Something went wrong!")
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace()
//                    }
//                }
//            })
//    }
//}