package Model

import com.google.gson.annotations.SerializedName

class ServerResponseLogout {
    @SerializedName("code")
    private var code: String? = null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }


}