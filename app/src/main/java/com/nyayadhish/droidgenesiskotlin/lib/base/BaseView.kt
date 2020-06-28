package com.nyayadhish.droidgenesiskotlin.lib.base

import android.view.View
import androidx.annotation.Nullable
import com.nyayadhish.droidgenesiskotlin.lib.dependencyinjection.APIComponent

/**
 * Created by Nikhil Nyayadish on 06 Aug 2019 at 16:25.
 */
interface BaseView {
    fun showDebugToast(message: String)
    fun showMaterialProgress()
    fun hideMaterialProgress()

    fun getAPIComponent(): APIComponent

    fun showToast(message: String)

    fun onNoNetworkFoud()

    fun onForceLogout()

    fun onServerError()

    fun showSuccessOrErrorMessage(
        dialogTitle: String,
        dialogMessage: String,
        positiveText: String,
        negativeText: String, @Nullable positiveCallback: View.OnClickListener?,
        @Nullable negativeCallBack: View.OnClickListener?
    )

    fun getLocalErrorMsg(key:String): String

    fun getServerErrorMsg(errorCode: String): String

}