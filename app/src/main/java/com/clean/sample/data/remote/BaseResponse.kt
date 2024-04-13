package com.clean.sample.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Identifier

class BaseResponse<T> {
    @SerializedName("statusCode")
    @Expose
    var statusCode = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: T? = null

}
