package com.nyayadhish.droidgenesiskotlin.lib.interfaces

import android.view.View

/**
 * OnRecyclerClickListener is used to determine click events in RecyclerView.
 *
 * @author Nikhil Nyayadhish
 */
interface OnRecyclerClickListener {

    /**
     * @param where    Based on EnumClicks identify what item was clicked
     * @param view     view of the item clicked
     * @param position current item position which was clicked
     */
    fun onRecyclerClick(where: EnumClicks, view: View, vararg position: Int)
}