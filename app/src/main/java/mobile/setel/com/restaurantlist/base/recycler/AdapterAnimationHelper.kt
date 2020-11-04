package mobile.setel.com.restaurantlist.base.recycler

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by hendriksaragih on 5/28/18.
 */
class AdapterAnimationHelper {

    private var mScrollListener: RecyclerView.OnScrollListener? = null

    fun attachToRecyclerView(recyclerView: RecyclerView?) {
        if (recyclerView == null || recyclerView.adapter == null || recyclerView.adapter !is CoreRecyclerAdapter<*, *>) {
            return
        }

        val adapter = recyclerView.adapter as CoreRecyclerAdapter<*, *>
        mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    adapter.setAnimationShouldBeEnabled(false)
                } else {
                    adapter.setAnimationShouldBeEnabled(true)
                }
            }
        }

        // Add scroll listener to handle adapter animation
        recyclerView.addOnScrollListener(mScrollListener!!)
    }

    /** Detach the animator from the [RecyclerView]  */
    fun detachFromRecyclerView(recyclerView: RecyclerView) {
        recyclerView.removeOnScrollListener(mScrollListener!!)
    }
}