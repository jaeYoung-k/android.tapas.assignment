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