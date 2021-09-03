package com.example.ceban.core.datasource.remote.responses
class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T) = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T) = ApiResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String?, body: T) = ApiResponse(StatusResponse.ERROR, body, msg)
    }
}

enum class StatusResponse {
    SUCCESS, EMPTY, ERROR
}