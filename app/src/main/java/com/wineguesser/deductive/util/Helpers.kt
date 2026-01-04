package com.wineguesser.deductive.util

import android.content.Context
import android.widget.Toast

object Helpers {

    const val CONCLUSION_PARCEL = "CONCLUSION_PARCEL"

    fun castKey(key: String): Int {
        return Integer.parseInt(key)
    }

    fun castChecked(checkedInt: Int): Boolean {
        return checkedInt == 1
    }

    fun castChecked(checkedBoolean: Boolean): Int {
        val checked = 1
        val notChecked = 0
        return if (checkedBoolean) {
            checked
        } else {
            notChecked
        }
    }

    fun parseEntryValue(value: Any): Int {
        return Integer.parseInt(value.toString())
    }

    fun parseChecked(value: Any): Boolean {
        return castChecked(parseEntryValue(value))
    }

    fun makeToastShort(context: Context, resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}
