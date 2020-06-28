package com.nyayadhish.droidgenesiskotlin.lib.tag

import android.view.GestureDetector
import android.view.GestureDetector.OnDoubleTapListener
import android.view.MotionEvent

/**
 *
 *
 * @author Aditi Shirsat
 */

open interface TagGestureListener : GestureDetector.OnGestureListener, OnDoubleTapListener {
    override fun onDown(motionEvent: MotionEvent?): Boolean
    override fun onSingleTapConfirmed(motionEvent: MotionEvent?): Boolean
    override fun onSingleTapUp(motionEvent: MotionEvent?): Boolean
    override fun onShowPress(motionEvent: MotionEvent?)
    override fun onDoubleTap(motionEvent: MotionEvent?): Boolean
    override fun onDoubleTapEvent(motionEvent: MotionEvent?): Boolean
    override fun onLongPress(motionEvent: MotionEvent?)
    override fun onScroll(
        motionEvent1: MotionEvent?,
        motionEvent2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean

    override fun onFling(
        motionEvent1: MotionEvent?,
        motionEvent2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean
}
