package mobile.setel.com.restaurantlist

import android.os.Bundle
import android.os.Handler
import mobile.setel.com.restaurantlist.base.BaseActivity

class SplashScreenActivity : BaseActivity() {

    override val viewRes = R.layout.activity_splash_screen

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 1000 //1 seconds
    private val SPLASH_DELAY_NO_REFRESH: Long = 3000 //3 seconds

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            MainActivity.launchIntent(this)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the Handler
        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY_NO_REFRESH)
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}