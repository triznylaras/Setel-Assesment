package mobile.setel.com.restaurantlist.base.recycler

import android.view.animation.Animation

/**
 * Created by hendriksaragih on 5/28/18.
 */
interface RecyclerAdapterAnimation {

    fun setAdapterAnimation(position: Int, lastPosition: Int): Animation
}