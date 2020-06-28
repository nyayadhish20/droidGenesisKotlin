package com.nyayadhish.droidgenesiskotlin.lib.custombottomsheet

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nyayadhish.droidgenesiskotlin.R
import com.nyayadhish.droidgenesiskotlin.lib.utils.show
import kotlinx.android.synthetic.main.bottomsheet_item_layout.view.*
import kotlinx.android.synthetic.main.textview_bottomsheet_title.view.*

/**
 * Created by Nikhil Nyayadhish
 */

open class CustomBottomSheet() {
    private lateinit var mContext: Context
    private val mList = ArrayList<String>()

    constructor(context: Context, labels: ArrayList<String>) : this() {
        this.mContext = context
        this.mList.addAll(labels)
    }

    interface CustomBottomSheetListener {
        fun onItemClicked(position: Int)
    }

    class Builder(private val mContext: Context) : CustomBottomSheet() {
        private var title: String = ""
        private var mList = arrayListOf<String>()
        private lateinit var mListener: CustomBottomSheetListener
        private val bottomSheetDialog: BottomSheetDialog =
            BottomSheetDialog(mContext, R.style.SheetDialog)

        fun setItem(item: String): Builder {
            mList.add(item)
            return this
        }

        fun build(): CustomBottomSheet {
            return CustomBottomSheet(mContext, mList)
        }

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        private fun hide() {
            bottomSheetDialog.dismiss()
        }

        fun setListener(listener: CustomBottomSheetListener): Builder {
            mListener = listener
            return this
        }

        fun show(): Builder {
            val bottomSheetView = View.inflate(mContext, R.layout.textview_bottomsheet_title, null)
            val bottomSheetParent =
                bottomSheetView.bottomsheet_parent as LinearLayout
            val bottomSheetItems =
                bottomSheetParent.bottomsheet_items as LinearLayout
            if (title != "") {
                bottomSheetParent.tv_bs_title.show()
                bottomSheetParent.tv_bs_title.text = title
            }
            bottomSheetItems.removeAllViews()
            bottomSheetView.tv_cancel.setOnClickListener {
                hide()
            }
            if (mList.size > 0)
                for (i in mList.indices) {
                    val itemView = View.inflate(mContext, R.layout.bottomsheet_item_layout, null)
                    val mLinear = itemView.rootview
                    val itemTitle = mLinear.tv_title
                    itemTitle.text = mList[i]
                    bottomSheetItems.addView(itemView)
                    itemView.setOnClickListener {
                        hide()
                        mListener.onItemClicked(i)
                    }
                }
            bottomSheetDialog.setContentView(bottomSheetParent)
            bottomSheetDialog.show()
            return this
        }
    }
}