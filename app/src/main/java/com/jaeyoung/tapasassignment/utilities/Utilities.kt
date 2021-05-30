package com.jaeyoung.tapasassignment.utilities

import android.content.Context
import android.view.View
import com.jaeyoung.tapasassignment.data.CreatorModel
import com.jaeyoung.tapasassignment.listeners.OnThrottleClickListener
import java.text.SimpleDateFormat
import java.util.*

fun View.onThrottleClick(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener))
}

fun Context.getStatusBarHeight(): Int {
    val id = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (id <= 0) return 0

    return resources.getDimensionPixelSize(id)
}

fun convertCount(count: Int): String {
    if (count < 1000) return "$count"
    val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
    return String.format(
        "%.1f %c",
        count / Math.pow(1000.0, exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}