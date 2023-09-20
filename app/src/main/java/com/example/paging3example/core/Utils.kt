package com.example.paging3example.core

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    @SuppressLint("SimpleDateFormat")
    fun decodeDateString(dateString: String, pattern: String): String? {
        val inputDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        val pressDate = inputDateFormat.parse(dateString)
        return inputDateFormat.format(pressDate ?: "")
    }
}