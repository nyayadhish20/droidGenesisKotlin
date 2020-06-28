package com.nyayadhish.droidgenesiskotlin.lib.base

/**
 * Created by Nikhil Nyayadish on 06 Aug 2019 at 16:25.
 */
interface BasePresenter {
    fun start()
    fun onDestroy()
    fun onPause()
    fun onStop()
}