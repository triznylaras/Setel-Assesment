package mobile.setel.com.restaurantlist.networking

import mobile.setel.com.restaurantlist.model.RestaurantListData
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @GET("homework/")
    fun getRestaurantList(): Call<RestaurantListData>
}