package com.nyayadhish.droidgenesiskotlin.lib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.widget.EditText
import android.widget.RemoteViews.RemoteView

@SuppressLint("AppCompatCustomView")
@RemoteView
class AppEditText : EditText {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun getText(): Editable {
        val s = super.getText()
        val temp = s.toString()
        s.toString().replace("", temp)
        return s
    }
}