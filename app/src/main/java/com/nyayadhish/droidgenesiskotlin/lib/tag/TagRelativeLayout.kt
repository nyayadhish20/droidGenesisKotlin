package com.nyayadhish.droidgenesiskotlin.lib.tag

/*
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.RelativeLayout
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.nyayadhish.droidgenesiskotlin.R
import com.nyayadhish.droidgenesiskotlin.model.TagUser
import com.nyayadhish.droidgenesiskotlin.model.TagUserData
import com.nyayadhish.droidgenesiskotlin.model.UserListData
import com.nyayadhish.droidgenesiskotlin.module.userplaceprofile.userprofile.ActivityUserProfile
import kotlinx.android.synthetic.main.row_tag_media.view.*
import kotlinx.android.synthetic.main.tag_view.view.*


*/
/**
 *
 *
 * @author Aditi Shirsat
 *//*


class TagRelativeLayout : RelativeLayout {

    private lateinit var mContext: Context

    private lateinit var mGestureDetector: GestureDetector
    private var mTaggedImageEvent: TaggedImageEvent? = null
    private lateinit var mVPTagPeople: ViewPager2


    private var mTagPeopleList: ArrayList<TagUser> = arrayListOf()

    private var isDragging = false
    private var maxLeft = 0f
    private var maxRight = 0f
    private var dX = 0f
    private var maxTop = 0f
    private var maxBottom = 0f
    private var dY = 0f
    private var isInitialized = false
    private var rawX: Int = 0
    private var rawY: Int = 0

    constructor(context: Context?) : super(context) {
        if (context != null) {
            mContext = context
        }
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        if (context != null) {
            mContext = context
        }
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        if (context != null) {
            mContext = context
        }
        init()
    }

    private val mTagOnTouchListener by lazy {
        OnTouchListener { v, event ->
            mGestureDetector.onTouchEvent(
                event
            )
        }
    }

    private val mTagGestureListener: TagGestureListener = object : TagGestureListener {
        override fun onDown(motionEvent: MotionEvent?): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(motionEvent: MotionEvent?): Boolean {
            if (tag_rootView.isAttachedToWindow) {
                val x: Float =
                    motionEvent?.x ?: 0 * 100 / tag_rootView.measuredWidth.toFloat() //left
                val y: Float =
                    motionEvent?.y ?: 0 * 100 / tag_rootView.measuredHeight.toFloat() //top

                mTaggedImageEvent?.singleTapConfirmedAndRootIsInTouch(x, y)

                when (motionEvent?.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                    }
                }
            }
            return false
        }

        override fun onSingleTapUp(motionEvent: MotionEvent?): Boolean {
            return true
        }

        override fun onShowPress(motionEvent: MotionEvent?) {}

        override fun onDoubleTap(motionEvent: MotionEvent?): Boolean {
            return true
        }

        override fun onDoubleTapEvent(motionEvent: MotionEvent?): Boolean {
            return true
        }

        override fun onLongPress(motionEvent: MotionEvent?) {}

        override fun onScroll(
            motionEvent1: MotionEvent?,
            motionEvent2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return true
        }

        override fun onFling(
            motionEvent1: MotionEvent?,
            motionEvent2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return true
        }
    }

    fun setImageToBeTaggedEvent(taggedImageEvent: TaggedImageEvent?) {
        if (mTaggedImageEvent == null) {
            mTaggedImageEvent = taggedImageEvent
        }
    }

    fun init() {
        tag_rootView.setOnTouchListener(mTagOnTouchListener)

        mGestureDetector = GestureDetector(tag_rootView.context, mTagGestureListener)
    }

    fun addTag(
        xCoordinate: Float,
        yCoordinate: Float,
        userData: UserListData,
        imageNumber: String,
        videoNumber: String
    ) {
        val tagView = View.inflate(mContext, R.layout.tag_view, null)

        tagView.tv_tag_people_name.text = userData.name

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        tagView.measure(0, 0)

        val measureWidth = tagView.measuredWidth / 2

        layoutParams.setMargins(-measureWidth, 0, 0, 0)

        tagView.layoutParams = layoutParams

        tagView.translationX = xCoordinate
        tagView.translationY = yCoordinate

        tag_rootView.addView(tagView)

        val tagUser = TagUser(
            tagView,
            TagUserData(
                tag_rootView.measuredHeight.toString(),
                imageNumber,
                tag_rootView.measuredWidth.toString(),
                userData,
                videoNumber,
                xCoordinate.toString(),
                yCoordinate.toString()
            )
        )

        mTagPeopleList.add(tagUser)

        tagView.iv_remove_tag.setOnClickListener {
            mTagPeopleList.remove(tagUser)
            tag_rootView.removeView(tagView)
        }

        */
/* tagView.setOnClickListener {
         }*//*


        tagView.setOnTouchListener(OnTouchListener { v, event ->

            if (isDragging) {
                val width = tagView.measuredWidth
                val height = tagView.measuredHeight
                val bounds = FloatArray(4)
                // LEFT
                bounds[0] = event.rawX + dX
                if (bounds[0] < maxLeft) {
                    tagView.iv_arrow_up.visibility = View.VISIBLE
                    tagView.iv_arrow_down.visibility = View.GONE
                    bounds[0] = maxLeft
                }
                // RIGHT
                bounds[2] = bounds[0] + width
                if (bounds[2] > maxRight) {
                    tagView.iv_arrow_up.visibility = View.VISIBLE
                    tagView.iv_arrow_down.visibility = View.GONE
                    bounds[2] = maxRight
                    bounds[0] = bounds[2] - width
                }
                // TOP
                bounds[1] = event.rawY + dY
                if (bounds[1] < maxTop) {
                    tagView.iv_arrow_up.visibility = View.VISIBLE
                    tagView.iv_arrow_down.visibility = View.GONE
                    bounds[1] = maxTop
                }
                // BOTTOM
                bounds[3] = bounds[1] + height
                if (bounds[3] > maxBottom) {
                    tagView.iv_arrow_up.visibility = View.GONE
                    tagView.iv_arrow_down.visibility = View.VISIBLE
                    bounds[3] = maxBottom
                    bounds[1] = bounds[3] - height
                } else {
                    tagView.iv_arrow_up.visibility = View.VISIBLE
                    tagView.iv_arrow_down.visibility = View.GONE
                }
                when (event.action) {
                    MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                        onDragFinish()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        tagView.animate().x(bounds[0]).y(bounds[1]).setDuration(0)
                            .start()
                        mTagPeopleList = getListOfTagsToBeTagged() ?: arrayListOf()
                    }
                }
                return@OnTouchListener true
            } else {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (::mVPTagPeople.isInitialized)
                            mVPTagPeople.isUserInputEnabled = false
                        if (!isInitialized) {
                            updateBounds()
                            isDragging = true
                        }
                        dX = v.x - event.rawX
                        dY = v.y - event.rawY

                        rawX = event.rawX.toInt()
                        rawY = event.rawY.toInt()
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        var y = event.rawY.toInt()
                        var x = event.rawX.toInt()
                        val diffY = rawY - y
                        val diffX = rawX - x
                        if (diffX in 0..2 && diffY in 0..2) {
                            mContext.startActivity(
                                Intent(mContext, ActivityUserProfile::class.java).putExtra(
                                    ActivityUserProfile.EXTRA_USER_ID,
                                    userData.userId.toString()
                                )
                            )
                        } else {
                            isDragging = true
                        }
                    }
                }
            }
            false
        })
    }

    private fun onDragFinish() {
        dX = 0f
        dY = 0f
        isDragging = false
        if (::mVPTagPeople.isInitialized)
            mVPTagPeople.isUserInputEnabled = true
    }

    private fun updateBounds() {
        updateViewBounds()
        updateParentBounds()
        isInitialized = true
    }

    private fun updateViewBounds() {
        dX = 0f
        dY = 0f
    }

    private fun updateParentBounds() {
        maxLeft = 0f
        maxRight = maxLeft + tag_rootView.measuredWidth
        maxTop = 0f
        maxBottom = maxTop + tag_rootView.measuredHeight
    }

    val mTagListLiveData: MutableLiveData<ArrayList<TagUser>> = MutableLiveData()

    fun getListOfTagsToBeTagged(): ArrayList<TagUser>? {
        val tagsToBeTagged: ArrayList<TagUser> = arrayListOf()
        if (mTagPeopleList.isNotEmpty()) {
            for (tag in mTagPeopleList) {
                val xCo: Float = tag.tagView.x + (tag.tagView.measuredWidth / 2)
                val yCo: Float = tag.tagView.y

                tagsToBeTagged.add(
                    TagUser(
                        tag.tagView,
                        TagUserData(
                            tag.tagUserData.imageHeight,
                            tag.tagUserData.imageNumber,
                            tag.tagUserData.imageWidth,
                            tag.tagUserData.userData,
                            tag.tagUserData.videoNumber,
                            xCo.toString(),
                            yCo.toString()
                        )
                    )
                )
            }
        }
        mTagListLiveData.postValue(tagsToBeTagged)

        return tagsToBeTagged
    }

    fun setViewPager(vpTagPeople: ViewPager2) {
        mVPTagPeople = vpTagPeople
    }
}

interface TaggedImageEvent {
    fun singleTapConfirmedAndRootIsInTouch(x: Float, y: Float)
    fun onDoubleTap(e: MotionEvent?): Boolean
    fun onDoubleTapEvent(e: MotionEvent?): Boolean
    fun onLongPress(e: MotionEvent?)
}*/
