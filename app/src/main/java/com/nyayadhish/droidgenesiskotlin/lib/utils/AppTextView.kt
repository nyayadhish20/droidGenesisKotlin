package com.nyayadhish.droidgenesiskotlin.lib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RemoteViews.RemoteView
import android.widget.TextView
import org.apache.commons.lang3.StringEscapeUtils

/**
 * @author Nikhil Nyayadhish
 * This is Custom textview to show emoji.
 * if Imoji not present it will reflect the normal text.
 */
@SuppressLint("AppCompatCustomView")
@RemoteView
class AppTextView : TextView {
    constructor(context: Context?) : super(context) {
        text = text
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        text = text
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        text = text
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        text = text
    }

    override fun setText(text: CharSequence, type: BufferType) {
        if (text != null) {
            super.setText(StringEscapeUtils.unescapeJava(text.toString()), type)
        }
    }
}