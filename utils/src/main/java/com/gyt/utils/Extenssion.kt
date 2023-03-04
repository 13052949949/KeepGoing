package com.gyt.utils

import android.view.View
import android.widget.TextView

/*
*  设置view为不可点击状态
*/
fun View.setDisable() {
    isClickable = false
    alpha = 0.3f
}

/*
*  设置view为可点击状态
*/
fun View.setEnable() {
    isClickable = true
    alpha = 1.0f
}

/*
* 设置view为不可见状态
*/
fun TextView.gone() {
    visibility = View.GONE;
}

/*
* 设置view为不可见状态
*/
fun View.invisible() {
    visibility = View.INVISIBLE;
}

/*
*  设置view为可见状态
*/
fun View.visible() {
    visibility = View.VISIBLE;
}