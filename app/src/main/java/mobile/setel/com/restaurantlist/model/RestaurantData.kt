package mobile.setel.com.restaurantlist.model

import com.google.gson.annotations.SerializedName

class RestaurantData() {
    @SerializedName("name")
    var restaurantName: String? = null

    @SerializedName("operatingHours")
    var operatingHours: String? = null

    @SerializedName("status")
    var openStatus: String? = null

    var isClicked : Boolean = false

    constructor(restaurantName: String, openStatus: String, operatingHours: String) : this() {
        this.restaurantName = restaurantName
        this.openStatus = openStatus
        this.operatingHours = operatingHours
    }
}