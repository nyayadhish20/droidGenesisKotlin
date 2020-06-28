package com.nyayadhish.droidgenesiskotlin.lib.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.nyayadhish.droidgenesiskotlin.BuildConfig
import com.nyayadhish.droidgenesiskotlin.lib.base.BaseFragment
import com.nyayadhish.droidgenesiskotlin.lib.base.BasePresenter
import com.nyayadhish.droidgenesiskotlin.lib.base.BaseView
import com.nyayadhish.droidgenesiskotlin.lib.application.MainApplication
import com.nyayadhish.droidgenesiskotlin.lib.dependencyinjection.APIComponent
import com.nyayadhish.droidgenesiskotlin.lib.utils.*
import com.nyayadhish.droidgenesiskotlin.R

import java.io.File
import java.util.*


/**
 * Created by Nikhil Nyayadhish
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    companion object {
        lateinit var mDestination: File
        const val REQUEST_CAMERA_PERMISSION = 101
        const val REQUEST_GALLERY_PERMISSION = 102
        const val REQUEST_CAMERA_IMAGE = 1
        const val REQUEST_GALLERY_IMAGE = 2
        const val REQUEST_CAMERA_VIDEO = 3
        const val REQUEST_GALLERY_VIDEO = 4
    }

    private lateinit var apiComponent: APIComponent
    private lateinit var mLoadingDialog: Dialog
    private lateinit var mLoadingView: View
    private var rootview: View? = null
    private var toolbar: TextView? = null
    private lateinit var mDialog: MaterialDialog
    private lateinit var menuItemLayout: LinearLayout
    private var mCustomPopupDialog: MaterialDialog? = null
    private var mLoginPopupDialog: MaterialDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        apiComponent = (application as MainApplication).getAPIComponent()

        rootview = findViewById(R.id.rootview)
        if (rootview == null)
            showDebugToast("rootview not set.")

        setPresenter()
        init()
        setObservers()
        setListener()
    }

    override fun onResume() {
        super.onResume()
        localisation()
    }

    override fun onRestart() {
        super.onRestart()
        localisation()
    }

    abstract fun setPresenter()


    abstract fun getPresenter(): BasePresenter?

    /**
     * abstract function to get layout id
     */
    @LayoutRes
    abstract fun getLayout(): Int

    /**
     * abstract function for Listener(ClickListener)
     */
    abstract fun setListener()

    /**
     * abstract function for set Observers on live data
     */
    abstract fun setObservers()

    /**
     * Abstract function for initializing.
     */
    abstract fun init()

    override fun showDebugToast(message: String) {
        if (BuildConfig.DEBUG) {
            //            Toast.makeText(this, getClass().getSimpleName() + "  " + message, Toast.LENGTH_LONG).show();
            //Toast.makeText(this,  message, Toast.LENGTH_LONG).show();

            Log.i("DEBUG", message)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showSnackbar(message: String) {
        val snack = Snackbar.make(this.rootview!!, message, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(
            ContextCompat.getColor(
                this.context(true),
                R.color.colorPrimary
            )
        )
        val textView: TextView =
            snack.view.findViewById(com.google.android.material.R.id.snackbar_text)
        snack.show()

        Utility.vibrate()
    }

    protected fun showSnackBarWithAction(
        message: String,
        buttonText: String,
        mListener: View.OnClickListener
    ) {
        val snack = Snackbar.make(this.rootview!!, message, Snackbar.LENGTH_INDEFINITE)
        snack.setAction(buttonText) {
            mListener.onClick(it)
        }
        snack.show()
    }


    override fun showMaterialProgress() {

        mLoadingDialog = Dialog(this)
        mLoadingDialog.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mLoadingView =
            (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.loadingview,
                null,
                false
            )
        (mLoadingView as LottieAnimationView).scale = 0.3f
        mLoadingDialog.setContentView(mLoadingView)
        mLoadingDialog.setCancelable(false)
        mLoadingDialog.setOnKeyListener { _: DialogInterface?, keyCode: Int, _: KeyEvent? ->

            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mLoadingDialog.dismiss()
                //finish();
            }
            true
        }

        mLoadingDialog.setCancelable(false)
        mLoadingDialog.show()
    }

    override fun hideMaterialProgress() {
        if (::mLoadingDialog.isInitialized)
            mLoadingDialog.dismiss()
    }

    //todo make Builder pattern for error messages
    override fun showSuccessOrErrorMessage(
        dialogTitle: String,
        dialogMessage: String,
        positiveText: String,
        negativeText: String, @Nullable positiveCallback: View.OnClickListener?,
        @Nullable negativeCallBack: View.OnClickListener?
    ) {
        showCustomDialog(dialogTitle, dialogMessage, positiveText, positiveCallback)
    }

    fun showCustomDialog(
        dialogTitle: String?,
        dialogDescription: String?,
        buttonName: String,
        callback: View.OnClickListener?
    ) {

    }

    fun showLoginDialog() {

    }


    fun addFragmentFromBottomNavigation(fragment: BaseFragment, extras: Bundle?) {
        if (extras != null)
            fragment.arguments = extras

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    fun setUpToolbar(
        title: String = "",
        @DrawableRes centerIcon: Int,
        @DrawableRes leftOptionIcon: Int,
        @DrawableRes rightOptionIcon: Int
    ) {

    }

    open fun onToolbarTitleClick() {

    }

    open fun onRightOptionsItemClick() {

    }

    open fun onLeftOptionsItemClick() {

    }


    /**
     * Display Choose Image from Camera or Gallery bottom sheet to user.
     */
    fun showBottomSheetOfChooseImageFrom(activity: Activity) {

        /*val mBottomSheetDialog =
            BottomSheetDialog(activity, R.style.SheetDialog)
        val sheetView =
            View.inflate(activity.applicationContext, R.layout.bottomsheet_choose_image_from, null)

        sheetView.tv_camera.setOnClickListener {
            mBottomSheetDialog.dismiss()
            openCamera(activity)
        }
        sheetView.tv_gallery.setOnClickListener {
            mBottomSheetDialog.dismiss()
            openGallery(activity)
        }
        sheetView.setOnClickListener { mBottomSheetDialog.dismiss() }

        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()*/
    }

    /**
     * Open camera to take photo.
     */
    fun openCamera(activity: Activity) {
        if (Utility.checkPermissions(
                activity,
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CAMERA_PERMISSION
            )
        ) {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            mDestination =
                File(
                    activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    System.currentTimeMillis().toString() + ".jpg"
                )
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mDestination))
            startActivityForResult(takePicture, REQUEST_CAMERA_IMAGE)
        }
    }

    /**
     * Open gallery to choose photo.
     */
    fun openGallery(activity: Activity) {
        if (Utility.checkPermissions(
                activity,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_GALLERY_PERMISSION
            )
        ) {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE)
        }
    }

    //change language of system
    open fun localisation() {

        val locale = Locale("en0")
        Locale.setDefault(locale)
        val res = resources
        val config =
            Configuration(res.configuration)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        if ("en" == Constants.LANGUAGE_AREBIC) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        } else {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }

    //todo Set up this method while implementation.
    override fun getLocalErrorMsg(key: String): String {
        return ""
    }

    //todo Set up this method while implementation.
    override fun getServerErrorMsg(errorCode: String): String {
        return ""
    }

    override fun getAPIComponent(): APIComponent {
        return apiComponent
    }

    override fun onForceLogout() {
        /*  showSuccessOrErrorMessage(Constants.WARNING
              , "You have been logged out!"
              , "Login"
              , ""
              , View.OnClickListener {
                  Utility.removePreference(Constants.PREF_USER_DATA)
                  startActivity(Intent(this, ActivityLogin::class.java))
                  finishAffinity()
              }
              ,null)*/
    }

    override fun onNoNetworkFoud() {

    }

    override fun onServerError() {

    }


    override fun onStop() {
        super.onStop()
        if (getPresenter() != null)
            getPresenter()?.onStop()
    }

    override fun onPause() {
        super.onPause()
        if (getPresenter() != null)
            getPresenter()?.onPause()
    }
}