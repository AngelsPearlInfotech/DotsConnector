package com.api.dotsconnector

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.DisplayMetrics
import android.view.WindowManager


/**
 * Created by sabbir on 3/24/2017.
 */

object Accessibility {
    lateinit var mDisplay: WindowManager
    var width: Int = 0
    var height: Int = 0


    fun isConnectedToInternet(_context: Context): Boolean {
        val connectivity = _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
        }
        return false
    }

    fun get_screen_width(context: Context): Int {
        mDisplay = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager

        width = mDisplay.defaultDisplay.width
        return width
    }

    fun get_screen_height(context: Context): Int {
        mDisplay = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager

        height = mDisplay.defaultDisplay.height
        return height
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun pxToDp(context: Context, px: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }


}
