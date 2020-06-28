package com.nyayadhish.droidgenesiskotlin.lib.base

import com.google.gson.annotations.SerializedName

/**
 * BaseData
 *
 * @author Aditi Shirsat
 */

abstract class BaseData(
    @SerializedName("Message", alternate = ["message"]) val Message: String? = "",
    @SerializedName("Status", alternate = ["status"]) val Status: String? = "",
    val errorCode: String? = ""
)

data class SuccessData(val demo: String? = "") : BaseData()