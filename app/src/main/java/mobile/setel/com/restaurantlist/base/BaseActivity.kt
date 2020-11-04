package mobile.setel.com.restaurantlist.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    /**
     * The layout res for the current activity.
     * @return the layout resource ID, return a null to make the activity not set a content view.
     */
    abstract val viewRes: Int?

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (viewRes != null) setContentView(viewRes!!)
    }
}
