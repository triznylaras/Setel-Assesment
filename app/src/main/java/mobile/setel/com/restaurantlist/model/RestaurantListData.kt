package mobile.setel.com.restaurantlist.model

import com.google.gson.annotations.SerializedName

class RestaurantListData() {
    @SerializedName("restaurants")
    var restaurantList: List<RestaurantData>? = null

    @SerializedName("timestamp")
    var timestamp: Int? = null

    constructor(restaurantList: List<RestaurantData>, timestamp: Int) : this() {
        this.restaurantList = restaurantList
        this.timestamp = timestamp
    }
}