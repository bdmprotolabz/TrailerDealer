package Model

import com.google.gson.annotations.SerializedName

class ServerResponseHome {
    @SerializedName("code")
    private var code: String? = null
    @SerializedName("data")
    private var data: Array<Data?>?=null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Array<Data?>? {
        return data
    }

    fun setData(data: Array<Data?>) {
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
        @SerializedName("review")
        var review: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("location")
        var location: String? = null

        override fun toString(): String {
            return "ClassPojo [image = $image, review = $review, name = $name, location = $location]"
        }
    }


}