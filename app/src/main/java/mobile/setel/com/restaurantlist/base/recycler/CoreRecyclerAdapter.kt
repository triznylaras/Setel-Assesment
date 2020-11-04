package mobile.setel.com.restaurantlist.base.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mobile.setel.com.restaurantlist.model.RestaurantListData

/**
 * Created by hendriksaragih on 5/28/18.
 */
abstract class CoreRecyclerAdapter<DATA_TYPE, VH : RecyclerView.ViewHolder>(
        val context: Context) : RecyclerView.Adapter<VH>() {

    protected val ITEM = 0
    protected val LOADING = 1
    protected var isLoadingAdded = false

    val mData: MutableList<DATA_TYPE>
    var isAnimationAllowed = false
    private var isAnimationShouldBeEnabled = false
    private var mLastPosition = -1
    private val mRecyclerAdapterAnimation: RecyclerAdapterAnimation?

    var data: List<DATA_TYPE>
        get() = mData
        set(data) {
            mData.clear()
            mData.addAll(data)
        }

    init {
        mData = ArrayList()

        mRecyclerAdapterAnimation = initRecyclerAdapterAnimation()
        if (mRecyclerAdapterAnimation != null) isAnimationAllowed = true
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemId(position: Int): Long {
        return if (!data.isEmpty() && position < data.size) {
            data[position]!!.hashCode().toLong()
        } else if (position == data.size) {
            -1
        } else {
            -2
        }
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        onViewHolderAttached()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
        onViewHolderDetached()
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        onViewHolderRecycled()
    }

    open fun clear() {
        mData.clear()
    }

    fun enableAnimation() {
        isAnimationAllowed = true
    }

    fun disableAnimation() {
        isAnimationAllowed = false
    }

    fun setAnimationShouldBeEnabled(isAnimationShouldBeEnabled: Boolean) {
        this.isAnimationShouldBeEnabled = isAnimationShouldBeEnabled
    }

    fun animateViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isAnimationAllowed) return
        if (!isAnimationShouldBeEnabled || mRecyclerAdapterAnimation == null) return

        val animation = mRecyclerAdapterAnimation.setAdapterAnimation(position, mLastPosition)
//        if (animation != null) holder.itemView.startAnimation(animation)
        holder.itemView.startAnimation(animation)
        mLastPosition = position
    }

    fun inflateViewHolder(parent: ViewGroup, layoutRes: Int): View {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(layoutRes, parent, false)
    }

    protected fun onViewHolderAttached() {
        // Stub!
    }

    protected fun onViewHolderDetached() {
        // Stub!
    }

    protected fun onViewHolderRecycled() {
        // Stub!
    }

    protected open fun initRecyclerAdapterAnimation(): RecyclerAdapterAnimation? {
        return SlideAdapterAnimation(context)
    }

    fun getItem(position: Int): DATA_TYPE? {
        return mData[position]
    }


    fun add(r: DATA_TYPE) {
        mData.add(r)
        notifyItemInserted(mData.size - 1)
    }

    fun addAll(moveResults: List<DATA_TYPE>) {
        for (result in moveResults) {
            add(result)
        }
    }

    fun remove(r: DATA_TYPE?) {
        val position = mData.indexOf(r)
        if (position > -1) {
            mData.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
    }

    fun addLoadingFooter(r: DATA_TYPE) {
        isLoadingAdded = true
        add(r)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = mData.size - 1
        val result = getItem(position)

        if (result != null) {
            mData.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}