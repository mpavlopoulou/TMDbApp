package gr.mpav.tmdbapp.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

// ------------ General UI Utils ------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
fun toDP(context: Context, size: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size.toFloat(), context.resources.displayMetrics).toInt()
}

fun toSP(context: Context, size: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        size.toFloat(),
        context.resources.displayMetrics).toInt()
}

fun dip2px(context: Context, dpValue: Float): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics).toInt()
}

fun sp2px(context: Context, spValue: Float): Float {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics).toInt().toFloat()
}

fun hideKeyboard(context: Context, currentFocus: View?) {
    if (currentFocus != null) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

fun isNetworkConnected(context: Context): Boolean
{
    return try {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.activeNetworkInfo != null
    } catch (ex: Exception) {
        // In case of Security exception return true to show generic error message
        true
    }
}