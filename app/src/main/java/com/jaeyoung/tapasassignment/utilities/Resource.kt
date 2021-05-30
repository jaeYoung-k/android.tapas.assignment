package com.jaeyoung.tapasassignment.utilities

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String? = null,
    val message_id: Int? = null
){
    companion object{

        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String, data:T?): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(msg_id:Int, data:T?): Resource<T>{
            return Resource(Status.ERROR, data, message_id = msg_id)
        }

        fun <T> loading(data:T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }

    }
}