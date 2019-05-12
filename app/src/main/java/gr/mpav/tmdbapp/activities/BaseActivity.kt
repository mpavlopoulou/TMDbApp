package gr.mpav.tmdbapp.activities

import android.annotation.SuppressLint
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import gr.mpav.tmdbapp.R
import gr.mpav.tmdbapp.utils.general.dip2px
import gr.mpav.tmdbapp.utils.general.isNetworkConnected
import gr.mpav.tmdbapp.utils.general.sp2px

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity()
{
    protected lateinit var mProgressView: FrameLayout
    protected lateinit var mCoordinatorLayout: CoordinatorLayout

    protected open fun setUpViews(){
        mProgressView = findViewById(R.id.progress_view)
        mCoordinatorLayout = findViewById(R.id.coordinator_layout)
    }

    protected fun showProgressView(){
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        mProgressView.visibility = View.VISIBLE
    }

    protected fun hideProgressView(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        mProgressView.visibility = View.GONE
    }

    protected fun handleServiceExternalFailure(){
        hideProgressView()
        val errorMessage: String = if (isNetworkConnected(this))
            resources.getString(R.string.generic_error_message)
        else
            resources.getString(R.string.no_internet_connection_message)
        showSnackbar(errorMessage)
    }

    fun showSnackbar(message: String, backgroundColor: Int = ContextCompat.getColor(this, android.R.color.holo_red_dark), textColor: Int = ContextCompat.getColor(this,android.R.color.white))
    {
        val snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(backgroundColor)
        val snackbarTextView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        snackbarTextView.setTextSize(COMPLEX_UNIT_PX, sp2px(this, 13F))
        snackbarTextView.maxLines = 20
        snackbarTextView.setTextColor(textColor)
        snackbarTextView.setPadding(0,
            dip2px(this, 20F), 0,
            dip2px(this, 20F)
        )
        snackbar.show()
    }

}