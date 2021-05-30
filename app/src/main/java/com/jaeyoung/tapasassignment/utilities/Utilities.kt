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

fun convertCreatorListToString(creators: List<CreatorModel>): String {
    val size = creators.size
    return when {
        size > 2 -> "${creators[0].display_name.trim()} and $size more"
        size > 1 -> "${creators[0].display_name.trim()} and ${creators[1].display_name.trim()}"
        size > 0 -> creators[0].display_name.trim()
        else -> ""
    }
}

fun convertDate(date: String, format: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(date) ?: return ""
    return SimpleDateFormat(format, Locale.getDefault()).format(input) ?: ""
}