package com.clean.sample.data.remote

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Identifier

data class ApiFailureError (val statusCode:Int=0,var status: Status, var message:String){
    //Add Failure Response model if needed
//    private val data: ResponseData? = null
    private val type: String? = null
    var apiType = 0
}
enum class Status {
    CONNECTION_FAILED,
    NO_INTERNET,
    UNKNOWN_ERROR,
    EMPTY_DATA,
    SESSION_EXPIRED
}