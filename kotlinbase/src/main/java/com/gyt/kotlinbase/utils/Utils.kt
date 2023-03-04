package com.gyt.kotlinbase.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.gyt.kotlinbase.BaseApplication


val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

@JvmOverloads
fun toast(string: String,duration:Int = Toast.LENGTH_LONG){
    Toast.makeText(BaseApplication.currentApplication,string,duration).show()
}