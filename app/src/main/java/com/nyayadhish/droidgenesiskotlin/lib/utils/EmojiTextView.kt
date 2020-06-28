package com.nyayadhish.droidgenesiskotlin.lib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.RemoteViews
import android.widget.TextView
import org.apache.commons.lang3.StringEscapeUtils

@SuppressLint("AppCompatCustomView")
@RemoteViews.RemoteView
private class EmojiTextView : TextView {
    override fun setText(text: CharSequence?, type: BufferType?) {
        setText(StringEscapeUtils.unescapeJava(text.toString()), type)
    }
    constructor(context: Context) : super(context){
        text = text
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        text = text
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        text = text
    }

}