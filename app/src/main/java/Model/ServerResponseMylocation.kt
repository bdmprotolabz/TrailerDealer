package Model

import com.google.gson.annotations.SerializedName

class ServerResponseMylocation {

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
        @SerializedName("totel")
        var totel: String? = null
        @SerializedName("location_title")
        var location_title: String? = null
        @SerializedName("location")
        var location: String? = null
        @SerializedName("id")
        var id: String? = null

        override fun toString(): String {
            return "ClassPojo [image = $image, totel = $totel, location_title = $location_title, location = $location]"
        }
    }

}