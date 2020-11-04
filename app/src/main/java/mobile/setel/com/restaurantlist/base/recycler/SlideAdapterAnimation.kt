package mobile.setel.com.restaurantlist.base.recycler

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import mobile.setel.com.restaurantlist.R


/**
 * Created by hendriksaragih on 5/28/18.
 */
class SlideAdapterAnimation(private val mContext: Context) : RecyclerAdapterAnimation {

    override fun setAdapterAnimation(position: Int, lastPosition: Int): Animation {
        return AnimationUtils.loadAnimation(
                mContext,
                if (position > lastPosition)
                    R.anim.recycler_adapter_top_bottom
                else
                    R.anim.recycler_adapter_bottom_top)
    }
}
