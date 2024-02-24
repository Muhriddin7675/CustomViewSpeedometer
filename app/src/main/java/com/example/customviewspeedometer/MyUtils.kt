package com.example.customviewspeedometer

import android.content.res.Resources
import android.util.TypedValue

fun Int.toPx(): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).toInt()