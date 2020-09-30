package Model

import com.google.gson.annotations.SerializedName


class ServerResponseUpadeImage {
    @SerializedName("code")
    private var code: String? = null
    @SerializedName("data")
    private var data: Data? = null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
    class Data {
        @SerializedName("image")
        var image: String? = null

        override fun toString(): String {
            return "ClassPojo [image = $image]"
        }
    }


}